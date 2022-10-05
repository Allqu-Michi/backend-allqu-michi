package com.pet.service.implement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pet.service.dto.PetDto;
import com.pet.service.exceptions.ResourceNotFoundException;
import com.pet.service.models.Pet;
import com.pet.service.models.Pet_Image;
import com.pet.service.repositories.PetImageRepository;
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
	private PetImageRepository petImageRepository;
	
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
	public PetDto save_one(PetDto petDto, Long user_id) {

		Pet petValid = valid_pet(petDto, user_id);
		Pet savePet = petRepository.save(petValid);
		
		List<String> List_images_url = save_images(petDto, savePet.getId());
		
		PetDto newPetDto = mapearDTO(savePet);
		newPetDto.setList_images_url(List_images_url);
		
		return newPetDto;
	}

	@Override
	public List<PetDto> save_multiple(List<PetDto> listPetDto, Long user_id) {
		
		List<Pet> listPetValid = new ArrayList<Pet>();
		
		listPetDto.forEach((petDto)->{
			Pet petValid = valid_pet(petDto, user_id);
			listPetValid.add(petValid);
		});

		List<PetDto> listPetDtoValid = new ArrayList<PetDto>();
		int index = 0;
		
		for(Pet pet : listPetValid) {

			Pet savePet = petRepository.save(pet);
			
			List<String> List_images_url = save_images(listPetDto.get(index), savePet.getId());
			PetDto newPetDto = mapearDTO(savePet);
			newPetDto.setList_images_url(List_images_url);
			
			listPetDtoValid.add(newPetDto);
			
			index++;
		}
		
		return listPetDtoValid;
	}
	
	public Pet valid_pet(PetDto petDto, Long user_id) {
		
		Pet pet = mapearEntidad(petDto);	
		
		Long pet_type_id = petDto.getPet_type_id();
		petTypeRepository.findById(pet_type_id)
				.orElseThrow(() -> new ResourceNotFoundException("Tipo de mascota", "id", pet_type_id));
		
		pet.setAdopted(false);
		pet.setStatus(false);
		pet.setUser_id(user_id);
		pet.setCreate_at(LocalDateTime.now());
		pet.setUpdate_at(LocalDateTime.now());
		
		return pet;
	}
	
	public List<String> save_images(PetDto petDto, Long pet_id) {
		
		List<Pet_Image> listPetImageData = new ArrayList<Pet_Image>();
		List<String> List_images_url = petDto.getList_images_url();
		List_images_url.forEach( (image_url) -> {
			
			Pet_Image savePetImageData = new Pet_Image();
			savePetImageData.setPetId(pet_id);
			savePetImageData.setImage_url(image_url);
			listPetImageData.add(savePetImageData);
			
		});
		
		if(listPetImageData.size() > 0) {
			petImageRepository.saveAll(listPetImageData);
		}
		
		return List_images_url;
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

		List<Pet_Image> list_pet_image_exist = petImageRepository.findByPetId(id);
		if(list_pet_image_exist.size() > 0) {
			petImageRepository.deleteAll(list_pet_image_exist);
		}
		
		List<Pet_Image> listPetImageData = new ArrayList<Pet_Image>();
		List<String> List_images_url = petDto.getList_images_url();
		List_images_url.forEach( (image_url) -> {
			
			Pet_Image savePetImageData = new Pet_Image();
			savePetImageData.setPetId(updatePet_.getId());
			savePetImageData.setImage_url(image_url);
			listPetImageData.add(savePetImageData);
			
		});
		
		if(listPetImageData.size() > 0) {
			petImageRepository.saveAll(listPetImageData);
		}
		
		PetDto newPetDto = mapearDTO(updatePet_);
		newPetDto.setList_images_url(List_images_url);
		
		return mapearDTO(updatePet_);
	}	

	@Override
	public void delete(long id) {
    	Pet pet = petRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Mascota", "id", id));
    	
    	List<Pet_Image> list_pet_image_exist = petImageRepository.findByPetId(id);
		if(list_pet_image_exist.size() > 0) {
			petImageRepository.deleteAll(list_pet_image_exist);
		}
		
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
