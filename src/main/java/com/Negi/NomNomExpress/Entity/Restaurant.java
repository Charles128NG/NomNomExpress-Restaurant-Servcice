package com.Negi.NomNomExpress.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.Negi.NomNomExpress.DTO.RegisterRestaurantDTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="restaurant_id")
    private Long id;
    
    private String name;
    private String location;
    private String cuisine;
    private Double rating;
    
    private Boolean isAvailable;
    
    @OneToOne(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Menu menu;
    
    public Restaurant(RegisterRestaurantDTO  registerRestaurantDTO) {
    	
    	this.name = registerRestaurantDTO.getName();
    	this.location = registerRestaurantDTO.getLocation();
    	this.cuisine = registerRestaurantDTO.getCuisine();
    	this.isAvailable =  true;
    }
    
    
}
