package com.pet.service.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
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
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getAdopted() {
		return adopted;
	}
	public void setAdopted(Boolean adopted) {
		this.adopted = adopted;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public Long getPet_type_id() {
		return pet_type_id;
	}
	public void setPet_type_id(Long pet_type_id) {
		this.pet_type_id = pet_type_id;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public LocalDateTime getCreate_at() {
		return create_at;
	}
	public void setCreate_at(LocalDateTime create_at) {
		this.create_at = create_at;
	}
	public LocalDateTime getUpdate_at() {
		return update_at;
	}
	public void setUpdate_at(LocalDateTime update_at) {
		this.update_at = update_at;
	}
	
}
