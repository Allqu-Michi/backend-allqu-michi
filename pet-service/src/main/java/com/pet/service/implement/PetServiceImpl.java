package com.pet.service.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pet.service.dto.PetDto;
import com.pet.service.exceptions.ResourceNotFoundException;
import com.pet.service.models.Pet;
import com.pet.service.repositories.PetRepository;
import com.pet.service.repositories.PetTypeRepository;
import com.pet.service.services.IPetService;

@Service
public class PetServiceImpl implements IPetService{
	@Autowired
	private PetRepository petRepository;

	@Autowired
	private PetTypeRepository petTypeRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	@Transactional(readOnly = true)
	public List<Pet> findAll() {
		return petRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Pet findById(Long id) {
		return petRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Mascota", "id", id));
	}

	@Override
	public PetDto save(PetDto petDto) {
		
		Pet pet = mapearEntidad(petDto);	
		
		Long pet_type_id = petDto.getPet_type_id();
		petTypeRepository.findById(pet_type_id)
				.orElseThrow(() -> new ResourceNotFoundException("Tipo de mascota", "id", pet_type_id));
		
		pet.setAdopted(false);
		pet.setStatus(false);
		pet.setCreate_at(LocalDateTime.now());
		pet.setUpdate_at(LocalDateTime.now());
		
		Pet savePet = petRepository.save(pet);
		
		return mapearDTO(savePet);
	}	

	@Override
	public PetDto update(PetDto petDto, long id) {
		Pet pet_ = petRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Mascota", "id", id));
		
		pet_.setName(petDto.getName());
		pet_.setDescription(petDto.getDescription());
		pet_.setAdopted(petDto.getAdopted());
		pet_.setCellphone(petDto.getCellphone());
		pet_.setEmail(petDto.getEmail());
		pet_.setAddress(petDto.getAddress());
		pet_.setColor(petDto.getColor());
		pet_.setImage_url(petDto.getImage_url());
		pet_.setPet_type_id(petDto.getPet_type_id());
		pet_.setStatus(petDto.getStatus());
		pet_.setBreed(petDto.getBreed());
		pet_.setUpdate_at(LocalDateTime.now());
		
		Pet updatePet_ = petRepository.save(pet_);
		
		return mapearDTO(updatePet_);
	}	

	@Override
	public void delete(long id) {
    	Pet pet = petRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Mascota", "id", id));
    	petRepository.delete(pet);
	}
	
	private PetDto mapearDTO(Pet pet) {
		PetDto petDto = modelMapper.map(pet, PetDto.class);
		return petDto;
	}
	
	// Convierte de DTO a Entidad
	private Pet mapearEntidad(PetDto petDto) {
		Pet pet = modelMapper.map(petDto, Pet.class);
		return pet;
	}
}
