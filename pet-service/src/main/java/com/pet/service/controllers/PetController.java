package com.pet.service.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pet.service.models.Pet;
import com.pet.service.services.IPetService;

@RestController
@RequestMapping("api/Pet")
public class PetController {
	@Autowired
	private IPetService petService;
	
	@GetMapping()
	public List<Pet> listar(){
		return petService.findAll();
	}
	
	@GetMapping("/{id}")
	public Pet detalle(@PathVariable Long id) {
		return petService.findById(id);
	}
	
	@PostMapping()
	public Pet save(@RequestBody Pet pet_Type) {
		return petService.save(pet_Type);
	}

    @PutMapping("/{id}")
    public ResponseEntity<Pet> update(@PathVariable(value = "id") Long id, @RequestBody Pet pet) {
        final Pet pet_ = petService.update(pet, id);
        return ResponseEntity.ok(pet_);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteApplication(@PathVariable(value = "id") Long id){
        petService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
