package com.Negi.NomNomExpress.service;

import java.util.*;
import com.Negi.NomNomExpress.DTO.AddMenuDTO;
import com.Negi.NomNomExpress.DTO.MenuItemDTO;
import com.Negi.NomNomExpress.DTO.RegisterRestaurantDTO;
import com.Negi.NomNomExpress.DTO.RestaurantDTO;
import com.Negi.NomNomExpress.DTO.RestaurantSearchDTO;
import com.Negi.NomNomExpress.DTO.SearchDishDTO;
import com.Negi.NomNomExpress.Entity.Menu;
import com.Negi.NomNomExpress.Entity.MenuItem;
import com.Negi.NomNomExpress.Entity.Restaurant;

public interface RestaurantService {
	
	public Restaurant registerRestaurant(RegisterRestaurantDTO  registerRestaurantDTO);

	public Menu addMenu(AddMenuDTO addMenuDTO);

	public MenuItem updateMenuItem(MenuItem menuItem);

	public List<RestaurantDTO>searchByRestaurantName(RestaurantSearchDTO restaurantSearchDTO);
	
	public List<MenuItemDTO>searchByDishName(SearchDishDTO searchDishDTO);
	
}
