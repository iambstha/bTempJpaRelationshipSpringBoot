package com.jparealtionship.JpaRelationship.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jparealtionship.JpaRelationship.entity.Restaurant;
import com.jparealtionship.JpaRelationship.repository.RestaurantRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@Data
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private final RestaurantRepository restaurantRepository;

	@Override
	public List<Restaurant> getAllRestaurants() {
		return restaurantRepository.findAll();
	}

	@Override
	public Restaurant addRestaurant(Restaurant restaurant) {
		return restaurantRepository.save(restaurant);
	}

	@Override
	public Optional<Restaurant> getRestaurantById(Long id) {
		return restaurantRepository.findById(id);
	}

	@Override
	public Optional<Restaurant> updateRestaurantById(Long id, Restaurant restaurant) {
		Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
		if (optionalRestaurant.isPresent()) {
			Restaurant existingRestaurant = optionalRestaurant.get();
			if (restaurant.getName() != null) {

				existingRestaurant.setName(restaurant.getName());
			}
			if (restaurant.getLocation() != null) {
				existingRestaurant.setLocation(restaurant.getLocation());
			}
			if (restaurant.getContactNumber() != null) {
				existingRestaurant.setContactNumber(restaurant.getContactNumber());
			}

			restaurantRepository.save(existingRestaurant);

			return Optional.of(existingRestaurant);
		}
		return Optional.empty();
	}

	@Override
	public boolean deleteRestaurantById(Long id) {
		try {
			Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
			if (optionalRestaurant.isPresent()) {
				restaurantRepository.delete(optionalRestaurant.get());
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Optional<List<Restaurant>> searchRestaurantByNameOrLocation(String name, String location) {
		List<Restaurant> optionalRestaurant = restaurantRepository.searchRestaurant(name, location);
		return Optional.of(optionalRestaurant);
	}

}
