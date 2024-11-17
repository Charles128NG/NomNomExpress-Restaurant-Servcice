package com.Negi.NomNomExpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Negi.NomNomExpress.DTO.AddMenuDTO;
import com.Negi.NomNomExpress.DTO.AddMenuResponseDTO;
import com.Negi.NomNomExpress.DTO.RegisterRestaurantDTO;
import com.Negi.NomNomExpress.DTO.RestaurantSearchDTO;
import com.Negi.NomNomExpress.DTO.SearchDishDTO;
import com.Negi.NomNomExpress.Entity.MenuItem;
import com.Negi.NomNomExpress.exception.RESTException;
import com.Negi.NomNomExpress.service.RestaurantService;

@RestController
public class RestaurantController {
	
	@Autowired
	private RestaurantService service;
	
	@PostMapping("/registerRestaurant")
	public ResponseEntity<?> registerRestaurant(@RequestBody RegisterRestaurantDTO  registerRestaurantDTO)
	throws RESTException{
		try {
			if(registerRestaurantDTO.getName()==null) {
				throw new RESTException("Name can't be null", HttpStatus.BAD_REQUEST);
			}
			
			if(registerRestaurantDTO.getCuisine()==null) {
				throw new RESTException("Cuisine can't be null", HttpStatus.BAD_REQUEST);
			}
			
			if(registerRestaurantDTO.getLocation()==null) {
				throw new RESTException("Location can't be null", HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<>(service.registerRestaurant(registerRestaurantDTO),HttpStatus.OK);
		}
		catch(RESTException e)
		{
			throw new RESTException(e.getMessage(),e.getHttpStatus());
		}
	}
	
	@PostMapping("/addMenu")
	public ResponseEntity<?> addMenu(@RequestBody AddMenuDTO addMenuDTO){
		try {
			if(addMenuDTO.getRestaurantId()==null) {
				throw new RESTException("Restaurant Id can't be null", HttpStatus.BAD_REQUEST);
			}
			
			if(addMenuDTO.getItems()==null) {
				throw new RESTException("Item list can't be null", HttpStatus.BAD_REQUEST);
			}
			AddMenuResponseDTO resp = new AddMenuResponseDTO(service.addMenu(addMenuDTO));
			return new ResponseEntity<>(resp,HttpStatus.OK);
		}
		catch(RESTException e)
		{
			throw new RESTException(e.getMessage(),e.getHttpStatus());
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new RESTException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/updateMenuItem")
	public ResponseEntity<?> updateMenuItem(@RequestBody MenuItem menuItem){
		try {
			if(menuItem.getId()==null) {
				throw new RESTException("Id can't be null", HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<>(service.updateMenuItem(menuItem),HttpStatus.OK);
		}
		catch(RESTException e)
		{
			throw new RESTException(e.getMessage(),e.getHttpStatus());
		}
	}
	
	@PostMapping("/searchRestaurant")
	public ResponseEntity<?> searchByRestaurant(@RequestBody RestaurantSearchDTO restaurantSearchDTO){
		try {
			Object response = service.searchByRestaurantName(restaurantSearchDTO);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}catch(RESTException e) {
			throw new RESTException(e.getMessage(),e.getHttpStatus());
		}
	}
	
	@PostMapping("/searchDish")
	public ResponseEntity<?> searchByDish(@RequestBody SearchDishDTO searchDishDTO){
		try {
			return new ResponseEntity<>(service.searchByDishName(searchDishDTO), HttpStatus.OK);
		}catch(RESTException e) {
			throw new RESTException(e.getMessage(),e.getHttpStatus());
		}
	}
	
	
}
