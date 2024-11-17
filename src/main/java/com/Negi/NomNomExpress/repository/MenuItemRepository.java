package com.Negi.NomNomExpress.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Negi.NomNomExpress.Entity.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
	
	@Query("SELECT menuItem FROM MenuItem menuItem WHERE menuItem.name LIKE %:name%")
	List<MenuItem> findAllByName(String name);
}
