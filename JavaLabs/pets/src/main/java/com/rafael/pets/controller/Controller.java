package com.rafael.pets.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafael.pets.model.OwnerDTO;
import com.rafael.pets.model.Pet;
import com.rafael.pets.model.PetDTO;
import com.rafael.pets.repository.PetRepository;
import com.rafael.pets.service.OwnerService;
import com.rafael.pets.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class Controller {
    private final OwnerService ownerService;
    private final PetService petService;


    @Autowired
    public Controller(OwnerService ownerService, PetService petService) {
        this.ownerService = ownerService;
        this.petService = petService;
    }


    @PostMapping("/pets")
    public void savePet(@RequestBody PetDTO pet) {
        petService.save(pet);
    }

    @PostMapping("/pets/add_friend")
    public void addFriend(@RequestBody JsonNode body) {
        petService.addBothFriends(body.get("id").asLong(), body.get("id2").asLong());
    }

    @GetMapping("/pets")
    public List<PetDTO> getPets() {
        return petService.getAll();
    }

    @PutMapping("/pets")
    public void updatePet(@RequestParam(value = "id") long id, @RequestBody PetDTO pet) {
        pet.setId(id);
        petService.updateById(pet);
    }

    @DeleteMapping("/pets")
    public void deletePet(@RequestParam(value = "id") long id) {
        petService.deleteById(id);
    }

    @GetMapping("/pets/color")
    public List<PetDTO> getPetsByColor(@RequestParam(value = "color", defaultValue = "GREY") Pet.Color color, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return petService.getByColor(color, page, size);
    }

    @GetMapping("/pets/breed")
    public List<PetDTO> getPetsByBreed(@RequestParam(value = "breed") String breed, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return petService.getByBreed(breed, page, size);
    }


    @PostMapping("/owners")
    public void saveOwner(@RequestBody OwnerDTO owner) {
        ownerService.save(owner);
    }

    @GetMapping("/owners")
    public List<OwnerDTO> getOwners() {
        return ownerService.getAll();
    }

    @PutMapping("/owners")
    public void updateOwner(@RequestParam(value = "id") long id, @RequestBody OwnerDTO owner) {
        owner.setId(id);
        ownerService.updateById(owner);
    }

    @DeleteMapping("/owners")
    public void deleteOwner(@RequestParam(value = "id") long id) {
        ownerService.deleteById(id);
    }

}
