package com.pet.service.controllers;

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

import com.pet.service.models.Pet_Type;
import com.pet.service.services.IPetTypeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/pettype")
public class PetTypeController {
	
	@Autowired
	private IPetTypeService petTypeService;
	
	@GetMapping()
	public List<Pet_Type> listar(){
		return petTypeService.findAll();
	}
	
	@GetMapping("/{id}")
	public Pet_Type detalle(@PathVariable Long id) {
		return petTypeService.findById(id);
	}
	
	@PostMapping()
	public Pet_Type save(@RequestBody Pet_Type pet_Type) {
		return petTypeService.save(pet_Type);
	}

    @PutMapping("/{id}")
    public ResponseEntity<Pet_Type> update(@PathVariable(value = "id") Long id, @RequestBody Pet_Type pet_Type) {
        final Pet_Type pet_Type_ = petTypeService.update(pet_Type, id);
        return ResponseEntity.ok(pet_Type_);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteApplication(@PathVariable(value = "id") Long id){
        petTypeService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
