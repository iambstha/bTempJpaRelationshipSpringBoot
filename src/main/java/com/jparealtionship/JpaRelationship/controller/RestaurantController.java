package com.jparealtionship.JpaRelationship.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jparealtionship.JpaRelationship.entity.Menu;
import com.jparealtionship.JpaRelationship.entity.MenuItem;
import com.jparealtionship.JpaRelationship.entity.Restaurant;
import com.jparealtionship.JpaRelationship.service.RestaurantService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

	@Autowired
	private final RestaurantService restaurantService;

	@GetMapping
	public ResponseEntity<List<Restaurant>> getAllRestaurants() {
		return ResponseEntity.ok().body(restaurantService.getAllRestaurants());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Restaurant>> getRestaurantById(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(restaurantService.getRestaurantById(id));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Restaurant> addRestaurant(@RequestBody @Valid Restaurant restaurant) {
		Restaurant savedRestaurant = restaurantService.addRestaurant(restaurant);

		if (savedRestaurant.getMenus() != null) {
			for (Menu menu : savedRestaurant.getMenus()) {
				menu.setRestaurant(savedRestaurant);
				if (menu.getMenuItems() != null) {
					for (MenuItem menuItem : menu.getMenuItems()) {
						menuItem.setMenu(menu);
					}
				}
			}
		}

		return ResponseEntity.ok().body(savedRestaurant);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Restaurant> deleteRestaurantById(@PathVariable("id") Long id) {
		boolean deleted = restaurantService.deleteRestaurantById(id);
		if (deleted) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Optional<Restaurant>> updateRestaurantById(@PathVariable("id") Long id,
			@RequestBody Restaurant restaurant) {
		return ResponseEntity.ok().body(restaurantService.updateRestaurantById(id, restaurant));
	}

}
