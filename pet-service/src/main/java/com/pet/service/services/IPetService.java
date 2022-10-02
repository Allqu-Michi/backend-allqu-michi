package com.pet.service.services;

import java.util.List;

import com.pet.service.dto.PetDto;
import com.pet.service.models.Pet;

public interface IPetService {
	public List<Pet> findAll();
	public Pet findById(Long id);
	public PetDto save(PetDto petDto);
	public PetDto update(PetDto petDto, long id);
	public void delete(long id);
}
