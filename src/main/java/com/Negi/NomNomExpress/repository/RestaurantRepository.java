package com.Negi.NomNomExpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.Negi.NomNomExpress.Entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	@Query("SELECT res FROM Restaurant res WHERE res.name LIKE %:name%")
	List<Restaurant> findAllByName(String name);
}
