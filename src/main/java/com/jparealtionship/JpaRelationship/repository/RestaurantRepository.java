package com.jparealtionship.JpaRelationship.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jparealtionship.JpaRelationship.entity.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

	@Query(value = "SELECT * FROM restaurants r WHERE LOWER(r.\"name\") LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(r.\"location\") LIKE LOWER(CONCAT('%', :location, '%'))", nativeQuery = true)
	public List<Restaurant> searchRestaurant(@Param("name") String name, @Param("location") String location);


}
