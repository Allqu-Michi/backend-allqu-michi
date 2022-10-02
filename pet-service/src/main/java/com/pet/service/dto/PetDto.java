package com.pet.service.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PetDto {
	
	private Long id;
	
	@NotEmpty(message = "El nombre no debe ser vacio o nulo")
	@Size(min = 1,message = "Debe ingresar el nombre")
	private String name;

	@NotEmpty(message = "La descripción no debe ser vacio o nulo")
	@Size(min = 1,message = "Debe ingresar la descripción")
	private String description;
	
	private Boolean adopted; // al ser registrado por un usuario será fals

	@NotEmpty(message = "Su número celular no debe ser vacio o nulo")
	@Size(min = 9,message = "Debe ingresar su número celular")
	private String cellphone;
	
	@NotEmpty(message = "Su email no debe ser vacio o nulo")
	@Email(message = "Debe ingresar su email")
	private String email;
	
	private String address;
	
	private String color;
	
	private String image_url;
	
	private Long pet_type_id; // Debe existir en la tabla pet_type
	
	private Boolean status; // al ser registrado por un usuario será false
	
	private String breed; // Raza no usado
	
}
