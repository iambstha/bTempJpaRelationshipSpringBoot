package com.jparealtionship.JpaRelationship.service;

import java.util.List;
import java.util.Optional;

import com.jparealtionship.JpaRelationship.entity.Menu;

public interface MenuService {

	public List<Menu> getAllMenus();

	public Menu addMenu(Menu menu);

	public Optional<Menu> getMenuById(Long id);

	public boolean deleteMenuById(Long id);

	public Optional<Menu> updateMenuById(Long id, Menu menu);

}
