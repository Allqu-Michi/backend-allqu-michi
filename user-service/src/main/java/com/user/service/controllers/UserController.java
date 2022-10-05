package com.user.service.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.dto.UserDto;
import com.user.service.models.User;
import com.user.service.services.IUserService;


@RestController
@RequestMapping("api/user")
public class UserController {
	@Autowired
	private IUserService userService;
	
	@GetMapping()
	public ResponseEntity<List<User>> listar(){
		List<User> listUser = userService.findAll();
		return new ResponseEntity<>(listUser, HttpStatus.OK); 
	}
	
	@GetMapping("/{id}")
	public  ResponseEntity<User> detalle(@PathVariable Long id) {
		User user = userService.findById(id);
		return new ResponseEntity<>(user, HttpStatus.OK); 
	}
	
	@PostMapping()
	public ResponseEntity<UserDto> PetDto(@Valid @RequestBody UserDto userDto) {
		
		UserDto newUserDto = userService.save(userDto);
		if(newUserDto == null)
            return ResponseEntity.badRequest().build();
		return new ResponseEntity<>(newUserDto, HttpStatus.CREATED);
		
	}
	
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable(value = "id") Long id, @Valid @RequestBody UserDto userDto) {
        final UserDto userDto_ = userService.update(userDto, id);
		if(userDto_ == null)
            return ResponseEntity.badRequest().build();
		return new ResponseEntity<>(userDto_, HttpStatus.OK); 
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable(value = "id") Long id){
    	userService.delete(id);
		return new ResponseEntity<>("Usuario eliminado con exito",HttpStatus.OK);
    }
    
}