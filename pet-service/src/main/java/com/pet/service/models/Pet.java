package com.pet.service.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "pet", schema = "public")
public class Pet implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private Boolean adopted;
	private String cellphone;
	private String email;
	private String address;
	private String color;
	
	@Column(name = "image_url")
	private String image_url;
	
	@Column(name = "pet_type_id")
	private Long pet_type_id;
	
	private Boolean status;
	private String breed;
	
	@Column(name = "create_at", nullable = true)
	private LocalDateTime create_at;
	
	@Column(name = "update_at", nullable = true)
	private LocalDateTime update_at;

	@Column(name = "user_id", nullable = false)
	private Long user_id;
}
