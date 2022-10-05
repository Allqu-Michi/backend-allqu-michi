package com.pet.service.controllers;

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

import com.pet.service.dto.PetDto;
import com.pet.service.models.Pet;
import com.pet.service.services.IPetService;

@RestController
@RequestMapping("api/pet")
public class PetController {
	@Autowired
	private IPetService petService;
	
	@GetMapping()
	public ResponseEntity<List<Pet>> listar(){
		List<Pet> listPet = petService.findAll();
		return new ResponseEntity<>(listPet, HttpStatus.OK); 
	}
	
	@GetMapping("/{id}")
	public  ResponseEntity<Pet> detalle(@PathVariable Long id) {
		Pet pet = petService.findById(id);
		return new ResponseEntity<>(pet, HttpStatus.OK); 
	}
	
	@PostMapping()
	public ResponseEntity<PetDto> PetDto(@Valid @RequestBody PetDto petDto) {
		
		PetDto newPetDto = petService.save(petDto);
		return new ResponseEntity<>(newPetDto, HttpStatus.CREATED);
		
	}
	
    @PutMapping("/{id}")
    public ResponseEntity<PetDto> update(@PathVariable(value = "id") Long id, @Valid @RequestBody PetDto petDto) {
        final PetDto petDto_ = petService.update(petDto, id);
		return new ResponseEntity<>(petDto_, HttpStatus.OK); 
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable(value = "id") Long id){
        petService.delete(id);
		return new ResponseEntity<>("Mascota eliminado con exito",HttpStatus.OK);
    }
    
}
