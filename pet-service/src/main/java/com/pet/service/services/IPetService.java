package com.pet.service.services;

import java.util.List;

import com.pet.service.dto.PetDto;
import com.pet.service.models.Pet;

public interface IPetService {
	public List<Pet> findAll();
	public Pet findById(Long id);
	public PetDto save_one(PetDto petDto, Long user_id);
	public List<PetDto> save_multiple(List<PetDto> listPetDto, Long user_id);
	public PetDto update(PetDto petDto, long id);
	public void delete(long id);
}
