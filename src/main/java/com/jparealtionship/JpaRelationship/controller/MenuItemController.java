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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jparealtionship.JpaRelationship.entity.Menu;
import com.jparealtionship.JpaRelationship.entity.MenuItem;
import com.jparealtionship.JpaRelationship.service.MenuItemService;
import com.jparealtionship.JpaRelationship.service.MenuService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/menu-item")
@RequiredArgsConstructor
public class MenuItemController {
	
	@Autowired
	private final MenuItemService menuItemService;
	
	@Autowired 
	private final MenuService menuService;
	
	@GetMapping
	public ResponseEntity<List<MenuItem>> getAllMenuItems(){
		return ResponseEntity.ok(menuItemService.getAllMenuItems());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<MenuItem>> getMenuItemById(@PathVariable("id") Long id){
		return ResponseEntity.ok().body(menuItemService.getMenuItemById(id));
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> addMenuItem(@RequestBody @Valid MenuItem menuItem, @RequestParam("menuId") Long menuId){
		if(menuId != null) {
			
			Optional<Menu> optionalMenu = menuService.getMenuById(menuId);
			if(optionalMenu.isPresent()) {
				Menu menu = optionalMenu.get();
				menuItem.setMenu(menu);
				
				MenuItem savedMenuItem = menuItemService.addMenuItem(menuItem);

				return ResponseEntity.ok().body(savedMenuItem);
			}else {
				return ResponseEntity.notFound().build();
			}
			
		}else {
			return ResponseEntity.badRequest().body("Please provide the menu id!!!.");
		}
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<MenuItem> deleteMenuItemById(@PathVariable("id") Long id) {
		boolean deleted = menuItemService.deleteMenuItemById(id);
		if (deleted) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Optional<MenuItem>> updateMenuItemById(@PathVariable("id") Long id, @RequestBody MenuItem menuItem) {
		return ResponseEntity.ok().body(menuItemService.updateMenuItemById(id, menuItem));
	}
}
