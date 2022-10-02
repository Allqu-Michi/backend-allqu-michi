package com.auth.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AuthUserDto {
	
    private String name;
    private String first_name;
    private String last_name;
    private String cellphone;
    private String email;
    private String password;
    private Boolean status;
    
}
