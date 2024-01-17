package com.jparealtionship.JpaRelationship.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu_items")
public class MenuItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Name cannot be null.")
	private String name;

	@Column(nullable = true)
	private String description;

	@NotNull(message = "Price cannot be null.")
	@Column(updatable = true)
	private Double price;

	@Column(nullable = true)
	private String dietaryInfo;

	@ManyToOne
	@JoinColumn(name = "menu_id")
	@JsonIgnore
	private Menu menu;

}
