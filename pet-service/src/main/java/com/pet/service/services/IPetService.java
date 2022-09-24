package com.pet.service.services;

import java.util.List;

import com.pet.service.models.Pet;

public interface IPetService {
	public List<Pet> findAll();
	public Pet findById(Long id);
	public Pet save(Pet pet);
	public Pet update(Pet pet, long id);
	public void delete(long id);
}
