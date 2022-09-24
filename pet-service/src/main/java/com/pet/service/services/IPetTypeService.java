package com.pet.service.services;

import java.util.List;

import com.pet.service.models.Pet_Type;

public interface IPetTypeService {

	public List<Pet_Type> findAll();
	public Pet_Type findById(Long id);
	public Pet_Type save(Pet_Type pet_Type);
	public Pet_Type update(Pet_Type pet_Type, long id);
	public void delete(long id);
}
