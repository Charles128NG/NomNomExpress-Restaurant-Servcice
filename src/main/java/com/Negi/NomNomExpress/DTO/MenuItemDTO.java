package com.Negi.NomNomExpress.DTO;

import com.Negi.NomNomExpress.Entity.MenuItem;
import com.Negi.NomNomExpress.Entity.Restaurant;

public class MenuItemDTO {
	
	public Long id;
    public String name;
    public Double price;
    public String category;
    
    public Long restId;
    public String restName;
    
    public MenuItemDTO(MenuItem menuItem) {
    	this.id = menuItem.getId();
    	this.name = menuItem.getName();
    	this.price = menuItem.getPrice();
    	this.category = menuItem.getCategory();
    	this.restId = menuItem.getMenu().getRestaurant().getId();
    	this.restName = menuItem.getMenu().getRestaurant().getName();
    }
}
