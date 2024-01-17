package com.jparealtionship.JpaRelationship.service;

import java.util.List;
import java.util.Optional;

import com.jparealtionship.JpaRelationship.entity.MenuItem;

public interface MenuItemService {

	public List<MenuItem> getAllMenuItems();

	public MenuItem addMenuItem(MenuItem menuItem);

	public Optional<MenuItem> getMenuItemById(Long id);

	public boolean deleteMenuItemById(Long id);

	public Optional<MenuItem> updateMenuItemById(Long id, MenuItem menuItem);

}
