package com.pet.service.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pet.service.models.Pet;
import com.pet.service.repositories.PetRepository;

@Service
public class PetServiceImpl implements IPetService{
	@Autowired
	private PetRepository petRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Pet> findAll() {
		return petRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Pet findById(Long id) {
		return petRepository.findById(id).orElse(null);
	}

	@Override
	public Pet save(Pet pet) {
		pet.setCreate_at(LocalDateTime.now());
		pet.setUpdate_at(LocalDateTime.now());
		return petRepository.save(pet);
	}	

	@Override
	public Pet update(Pet pet, long id) {
		Pet pet_ = petRepository.findById(id).orElse(null);
		
		pet_.setName(pet.getName());
		pet_.setDescription(pet.getDescription());
		pet_.setAdopted(pet.getAdopted());
		pet_.setCellphone(pet.getCellphone());
		pet_.setEmail(pet.getEmail());
		pet_.setAddress(pet.getAddress());
		pet_.setColor(pet.getColor());
		pet_.setImage_url(pet.getImage_url());
		pet_.setPet_type_id(pet.getPet_type_id());
		pet_.setStatus(pet.getStatus());
		pet_.setBreed(pet.getBreed());
		pet_.setUpdate_at(LocalDateTime.now());
		
		return petRepository.save(pet_);
	}	

	@Override
	public void delete(long id) {
    	Pet pet = petRepository.findById(id).orElse(null);
    	petRepository.delete(pet);
	}

}
