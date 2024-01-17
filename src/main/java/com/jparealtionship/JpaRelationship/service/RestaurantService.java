package com.jparealtionship.JpaRelationship.service;

import java.util.List;
import java.util.Optional;

import com.jparealtionship.JpaRelationship.entity.Restaurant;

public interface RestaurantService {
	
	public List<Restaurant> getAllRestaurants();

	public Restaurant addRestaurant(Restaurant restaurant);
	
	public Optional<Restaurant> getRestaurantById(Long id);
	
	public boolean deleteRestaurantById(Long id);
	
	public Optional<Restaurant> updateRestaurantById(Long id, Restaurant restaurant);
	
	public Optional<List<Restaurant>> searchRestaurantByNameOrLocation(String name, String location);
	
}
