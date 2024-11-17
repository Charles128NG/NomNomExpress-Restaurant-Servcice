package com.Negi.NomNomExpress.DTO;

import java.util.List;

import com.Negi.NomNomExpress.Entity.MenuItem;
import com.Negi.NomNomExpress.Entity.Restaurant;

public class RestaurantDTO {
	
	public String name;
    public String location;
    public String cuisine;
    
    public List<MenuItem> menuItems;
    
	public RestaurantDTO(Restaurant restaurant) {
		this.name = restaurant.getName();
		this.cuisine = restaurant.getCuisine();
		this.location = restaurant.getLocation();
		if(restaurant.getMenu()!= null && restaurant.getMenu().getItems()!=null) {
			this.menuItems = restaurant.getMenu().getItems();
		}
	}

}
