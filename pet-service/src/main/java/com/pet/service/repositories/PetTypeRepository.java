package com.pet.service.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.pet.service.models.Pet_Type;

public interface PetTypeRepository extends JpaRepository<Pet_Type, Long>{

}
