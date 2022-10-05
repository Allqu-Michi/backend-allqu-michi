package com.user.service.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDto {
	
	@NotEmpty(message = "El nombre no debe ser vacio o nulo")
	@Size(min = 1,message = "Debe ingresar el nombre")
    private String name;
	
    private String first_name;
    private String last_name;

	@NotEmpty(message = "Su número celular no debe ser vacio o nulo")
	@Size(min = 9,message = "Debe ingresar su número celular")
    private String cellphone;
	
	@NotEmpty(message = "Su email no debe ser vacio o nulo")
	@Email(message = "Debe ingresar su email")
    private String email;

	
	@NotEmpty(message = "Su contraseña no debe ser vacio o nulo")
    private String password;
    
}

