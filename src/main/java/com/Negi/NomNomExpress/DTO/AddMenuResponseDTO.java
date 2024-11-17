package com.Negi.NomNomExpress.DTO;

import java.util.List;
import java.util.stream.Collectors;

import com.Negi.NomNomExpress.Entity.Menu;
import com.Negi.NomNomExpress.Entity.MenuItem;
import com.Negi.NomNomExpress.Entity.Restaurant;

import lombok.Data;

@Data
public class AddMenuResponseDTO {
	private Long id;
	private RestaurantDTO restaurant;
	private List<MenuItemDTO> items;
	
	public AddMenuResponseDTO(Menu menu) {
		this.id = menu.getId();
		
		this.restaurant = new RestaurantDTO(menu.getRestaurant());
		
		this.items  = menu.getItems().stream().map(MenuItemDTO::new).collect(Collectors.toList());
	}
}
