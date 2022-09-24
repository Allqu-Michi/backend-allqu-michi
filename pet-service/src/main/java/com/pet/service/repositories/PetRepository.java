package com.pet.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pet.service.models.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {

}
