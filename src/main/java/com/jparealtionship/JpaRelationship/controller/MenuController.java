package com.jparealtionship.JpaRelationship.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jparealtionship.JpaRelationship.entity.Menu;
import com.jparealtionship.JpaRelationship.entity.MenuItem;
import com.jparealtionship.JpaRelationship.entity.Restaurant;
import com.jparealtionship.JpaRelationship.service.MenuService;
import com.jparealtionship.JpaRelationship.service.RestaurantService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/menu")
@RequiredArgsConstructor
public class MenuController {

	@Autowired
	private final MenuService menuService;

	@Autowired
	private final RestaurantService restaurantService;

	@GetMapping
	public ResponseEntity<List<Menu>> getAllMenus() {
		return ResponseEntity.ok().body(menuService.getAllMenus());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Menu>> getMenuById(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(menuService.getMenuById(id));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> addMenu(@RequestBody @Valid Menu menu, @RequestParam("restaurantId") Long restaurantId) {
	    if (restaurantId != null) {
	        Optional<Restaurant> optionalRestaurant = restaurantService.getRestaurantById(restaurantId);

	        if (optionalRestaurant.isPresent()) {
	            Restaurant restaurant = optionalRestaurant.get();
	            menu.setRestaurant(restaurant);

	            Menu savedMenu = menuService.addMenu(menu);

	            if (savedMenu.getMenuItems() != null) {
	                for (MenuItem menuItem : savedMenu.getMenuItems()) {
	                    menuItem.setMenu(savedMenu);
	                }
	            }

	            List<Menu> restaurantMenus = restaurant.getMenus();
	            if (restaurantMenus == null) {
	                restaurantMenus = new ArrayList<>();
	            }
	            restaurantMenus.add(savedMenu);
	            restaurant.setMenus(restaurantMenus);

	            return ResponseEntity.ok().body(savedMenu);
	        } else {
	            System.out.println("Restaurant with the given restaurant id not found.");
	            return ResponseEntity.notFound().build();
	        }
	    } else {
	        return ResponseEntity.badRequest().body("Please provide the restaurant id!!!.");
	    }
	}


	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Menu> deleteMenuById(@PathVariable("id") Long id) {
		boolean deleted = menuService.deleteMenuById(id);
		if (deleted) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Optional<Menu>> updateMenuById(@PathVariable("id") Long id, @RequestBody Menu menu) {
		return ResponseEntity.ok().body(menuService.updateMenuById(id, menu));
	}

}
