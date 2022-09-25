package com.pet.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pet.service.models.Pet_Type;
import com.pet.service.repositories.PetTypeRepository;

import java.util.List;

@Service
public class PetTypeServiceImpl implements IPetTypeService{

	@Autowired
	private PetTypeRepository petTypeRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Pet_Type> findAll() {
		return petTypeRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Pet_Type findById(Long id) {
		return petTypeRepository.findById(id).orElse(null);
	}

	@Override
	public Pet_Type save(Pet_Type pet_Type) {
		return petTypeRepository.save(pet_Type);
	}	

	@Override
	public Pet_Type update(Pet_Type pet_Type, long id) {
    	Pet_Type pet_Type_ = petTypeRepository.findById(id).orElse(null);
    	pet_Type_.setDescription(pet_Type.getDescription());
    	pet_Type_.setState(pet_Type.getState());
		return petTypeRepository.save(pet_Type_);
	}	

	@Override
	public void delete(long id) {
    	Pet_Type pet_Type_ = petTypeRepository.findById(id).orElse(null);
		petTypeRepository.delete(pet_Type_);
	}

}
