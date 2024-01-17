package com.jparealtionship.JpaRelationship.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jparealtionship.JpaRelationship.entity.Menu;
import com.jparealtionship.JpaRelationship.repository.MenuRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@Data
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

	@Autowired
	private final MenuRepository menuRepository;

	@Override
	public List<Menu> getAllMenus() {
		return menuRepository.findAll();
	}

	@Override
	public Menu addMenu(Menu menu) {
		return menuRepository.save(menu);
	}

	@Override
	public Optional<Menu> getMenuById(Long id) {
		return menuRepository.findById(id);
	}

	@Override
	public boolean deleteMenuById(Long id) {
		try {
			Optional<Menu> optionalMenu = menuRepository.findById(id);
			if (optionalMenu.isPresent()) {
				menuRepository.delete(optionalMenu.get());
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
	public Optional<Menu> updateMenuById(Long id, Menu menu) {
		Optional<Menu> optionalMenu = menuRepository.findById(id);
		if (optionalMenu.isPresent()) {
			Menu existingMenu = optionalMenu.get();
			if (menu.getId() != null) {
				existingMenu.setId(menu.getId());
			}
			if (menu.getMenuItems() != null) {
				existingMenu.setMenuItems(menu.getMenuItems());
			}

			Menu updatedMenu = menuRepository.save(existingMenu);
			return Optional.ofNullable(updatedMenu);

		}
		return Optional.empty();
	}

}
