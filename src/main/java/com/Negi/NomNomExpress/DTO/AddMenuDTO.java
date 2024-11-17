package com.Negi.NomNomExpress.DTO;

import java.util.List;

import com.Negi.NomNomExpress.Entity.MenuItem;

import lombok.Data;

@Data
public class AddMenuDTO {
	private Long restaurantId;
	private List<MenuItem> items;
}
