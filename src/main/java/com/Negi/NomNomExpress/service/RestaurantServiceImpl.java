package com.Negi.NomNomExpress.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Negi.NomNomExpress.DTO.AddMenuDTO;
import com.Negi.NomNomExpress.DTO.MenuItemDTO;
import com.Negi.NomNomExpress.DTO.OrderDTO;
import com.Negi.NomNomExpress.DTO.RegisterRestaurantDTO;
import com.Negi.NomNomExpress.DTO.RestaurantDTO;
import com.Negi.NomNomExpress.DTO.RestaurantSearchDTO;
import com.Negi.NomNomExpress.DTO.SearchDishDTO;
import com.Negi.NomNomExpress.Entity.Menu;
import com.Negi.NomNomExpress.Entity.MenuItem;
import com.Negi.NomNomExpress.Entity.Restaurant;
import com.Negi.NomNomExpress.exception.RESTException;
import com.Negi.NomNomExpress.kafka.KafkaProducerService;
import com.Negi.NomNomExpress.repository.MenuItemRepository;
import com.Negi.NomNomExpress.repository.MenuRepository;
import com.Negi.NomNomExpress.repository.RestaurantRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RestaurantServiceImpl implements RestaurantService{
	
	@Autowired
	private RestaurantRepository resRepo;
	
	@Autowired
	private MenuRepository menuRepo;
	
	@Autowired
	private MenuItemRepository menuItemRepo;
	
	@Autowired
	private KafkaProducerService kafkaService;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public Restaurant registerRestaurant(RegisterRestaurantDTO registerRestaurantDTO) throws RESTException{
		try {
			return resRepo.save(new Restaurant(registerRestaurantDTO));
		}catch(Exception e) {
			throw new RESTException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Menu addMenu(AddMenuDTO addMenuDTO) throws RESTException{
		try {
			Restaurant rest = resRepo.findById(addMenuDTO.getRestaurantId()).get();
			Menu menu = rest.getMenu();
			if(menu == null) {
				Menu newMenu = new Menu();
				newMenu.setRestaurant(rest);
				List<MenuItem> items = addMenuDTO.getItems();
				items.forEach(obj -> obj.setMenu(newMenu));
				newMenu.setItems(items);
				return menuRepo.save(newMenu);
			}else {
//				Menu menu = new Menu();
//				menu.setRestaurant(rest);
				List<MenuItem> items = addMenuDTO.getItems();
				items.forEach(obj -> obj.setMenu(menu));
				menu.getItems().addAll(items);
				return menuRepo.save(menu);
				//return menu;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new RESTException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public MenuItem updateMenuItem(MenuItem menuItem) {
		try {
			
			MenuItem object = menuItemRepo.findById(menuItem.getId()).get();
			if(menuItem.getCategory()!=null) {
				object.setCategory(menuItem.getCategory());
			}
			if(menuItem.getName()!=null) {
				object.setName(menuItem.getName());
			}
			if(menuItem.getPrice()!=null) {
				object.setPrice(menuItem.getPrice());
			}
			return menuItemRepo.save(object);
			
		}catch(Exception e) {
			throw new RESTException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<RestaurantDTO> searchByRestaurantName(RestaurantSearchDTO restaurantSearchDTO) throws RESTException {
		try {
			 List<Restaurant> ans = resRepo.findAllByName(restaurantSearchDTO.getRestaurantName());
			 List<RestaurantDTO> results = ans.stream().map(RestaurantDTO::new).collect(Collectors.toList());
			return results;
		}
		catch(Exception e) {
			throw new RESTException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<MenuItemDTO> searchByDishName(SearchDishDTO searchDishDTO) {
		try {
			 List<MenuItem> ans = menuItemRepo.findAllByName(searchDishDTO.getDishName());
			 List<MenuItemDTO> results = ans.stream().map(MenuItemDTO::new).collect(Collectors.toList());
			return results;
		}
		catch(Exception e) {
			throw new RESTException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public OrderDTO prepareFood(OrderDTO orderDTO) throws RESTException {
		try {
			orderDTO.setOrderStatus("prepared");
			kafkaService.sendMessage(objectMapper.writeValueAsString(orderDTO));
			return orderDTO;
		}catch(Exception e) {
			throw new RESTException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	

}
