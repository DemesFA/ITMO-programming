package com.rafael.pets.service;

import com.rafael.pets.model.Pet;
import com.rafael.pets.model.PetDTO;

import java.util.List;

public interface PetService {
    void save(PetDTO pet);

    List<PetDTO> getAll();

    void updateById(PetDTO pet);

    void deleteById(long id);

    void addBothFriends(long id1, long id2);


    List<PetDTO> getByColor(Pet.Color color, int page, int size);

    List<PetDTO> getByBreed(String breed, int page, int size);
}
