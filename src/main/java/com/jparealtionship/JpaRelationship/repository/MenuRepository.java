package com.jparealtionship.JpaRelationship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jparealtionship.JpaRelationship.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

}