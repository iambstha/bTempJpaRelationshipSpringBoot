package com.jparealtionship.JpaRelationship.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jparealtionship.JpaRelationship.entity.MenuItem;
import com.jparealtionship.JpaRelationship.repository.MenuItemRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class MenuItemServiceImpl implements MenuItemService {

	@Autowired
	private final MenuItemRepository menuItemRepository;

	@Override
	public List<MenuItem> getAllMenuItems() {
		return menuItemRepository.findAll();
	}

	@Override
	public MenuItem addMenuItem(MenuItem menuItem) {
		return menuItemRepository.save(menuItem);
	}

	@Override
	public Optional<MenuItem> getMenuItemById(Long id) {
		return menuItemRepository.findById(id);
	}

	@Override
	public boolean deleteMenuItemById(Long id) {
		Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(id);
		try {
			if (optionalMenuItem.isPresent()) {
				menuItemRepository.delete(optionalMenuItem.get());
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
	public Optional<MenuItem> updateMenuItemById(Long id, MenuItem menuItem) {
		Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(id);
		if (optionalMenuItem.isPresent()) {
			MenuItem existingMenuItem = optionalMenuItem.get();
			if (menuItem.getId() != null) {
				existingMenuItem.setId(menuItem.getId());
			}
			if (menuItem.getName() != null) {
				existingMenuItem.setName(menuItem.getName());
			}
			if (menuItem.getDescription() != null) {
				existingMenuItem.setDescription(menuItem.getDescription());
			}
			if (menuItem.getPrice() != null) {
				existingMenuItem.setPrice(menuItem.getPrice());
			}
			if (menuItem.getDietaryInfo() != null) {
				existingMenuItem.setDietaryInfo(menuItem.getDietaryInfo());
			}

			menuItemRepository.save(existingMenuItem);

		}
		return Optional.empty();
	}

}
