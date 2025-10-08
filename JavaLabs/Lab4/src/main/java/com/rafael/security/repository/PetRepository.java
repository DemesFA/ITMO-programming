package com.rafael.security.repository;


import com.rafael.security.model.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends CrudRepository<Pet, Long> {
    Page<Pet> findByColor(Pet.Color color, Pageable pageable);

    Page<Pet> findByBreed(String breed, Pageable pageable);

}