package com.rafael.security.controller;

import com.fasterxml.jackson.databind.JsonNode;

import com.rafael.security.model.PetDTO;
import com.rafael.security.model.Pet;
import com.rafael.security.service.AuthService;
import com.rafael.security.service.OwnerService;
import com.rafael.security.service.PetService;
import com.rafael.security.service.UserInfoDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pets")
public class PetController {
    private final OwnerService ownerService;
    private final PetService petService;

    private final AuthService authService;


    @Autowired
    public PetController(OwnerService ownerService, PetService petService, AuthService authService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.authService = authService;
    }


    private PetDTO checkPetOwnership(long petId, Long requestingOwnerId) {
        List<PetDTO> allPets = petService.getAll();
        PetDTO pet = allPets.stream()
                .filter(p -> p.getId() == petId)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found"));

        if (!pet.getOwnerId().equals(requestingOwnerId)) {
            throw new AccessDeniedException("You do not have permission to modify this pet");
        }

        return pet;
    }


    @PostMapping
    public void savePet(@RequestBody PetDTO pet) {
        if (authService.checkAuth(pet.getOwnerId())) {
            petService.save(pet);
        }
    }

    @PostMapping("/add_friend")
    public void addFriend(@RequestBody JsonNode body) {
        petService.addBothFriends(body.get("id").asLong(), body.get("id2").asLong());
    }

    @GetMapping
    public List<PetDTO> getPets() {
        return petService.getAll();
    }

    @PutMapping
    public void updatePet(@RequestParam(value = "id") long id, @RequestBody PetDTO pet) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserInfoDetails userDetails = (UserInfoDetails) auth.getPrincipal();
        PetDTO existingPet = checkPetOwnership(id, userDetails.getOwnerId());

        pet.setId(id);
        if (pet.getOwnerId() != null && !pet.getOwnerId().equals(existingPet.getOwnerId())) {
            authService.checkAuth(pet.getOwnerId());
        }

        petService.updateById(pet);
    }

    @DeleteMapping
    public void deletePet(@RequestParam(value = "id") long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserInfoDetails userDetails = (UserInfoDetails) auth.getPrincipal();

        checkPetOwnership(id, userDetails.getOwnerId());
        petService.deleteById(id);
    }

    @GetMapping("/color")
    public List<PetDTO> getPetsByColor(@RequestParam(value = "color", defaultValue = "GREY") Pet.Color color, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return petService.getByColor(color, page, size);
    }

    @GetMapping("/breed")
    public List<PetDTO> getPetsByBreed(@RequestParam(value = "breed") String breed, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return petService.getByBreed(breed, page, size);
    }


}