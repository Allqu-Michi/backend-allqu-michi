package com.pet.service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pet.service.models.Pet_Image;

public interface PetImageRepository extends JpaRepository<Pet_Image, Long>{
	public List<Pet_Image> findByPetId(Long petId);
}