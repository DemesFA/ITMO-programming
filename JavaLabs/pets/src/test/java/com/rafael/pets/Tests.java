package com.rafael.pets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafael.pets.controller.Controller;
import com.rafael.pets.model.OwnerDTO;
import com.rafael.pets.model.Pet;
import com.rafael.pets.model.PetDTO;
import com.rafael.pets.repository.PetRepository;
import com.rafael.pets.service.OwnerService;
import com.rafael.pets.service.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class Tests {

    private MockMvc mockMvc;

    @Mock
    private PetService petService;

    @Mock
    private OwnerService ownerService;

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private Controller controller;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    public void testSavePet() throws Exception {
        PetDTO petDTO = new PetDTO();
        petDTO.setName("TestPet");
        petDTO.setBreed("TestBreed");
        petDTO.setColor(Pet.Color.GREY);

        mockMvc.perform(post("/api/v1/pets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(petDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPets() throws Exception {
        List<PetDTO> pets = Arrays.asList(
            new PetDTO(),
            new PetDTO()
        );
        when(petService.getAll()).thenReturn(pets);

        mockMvc.perform(get("/api/v1/pets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testUpdatePet() throws Exception {
        PetDTO petDTO = new PetDTO();
        petDTO.setName("UpdatedPet");
        petDTO.setBreed("UpdatedBreed");
        petDTO.setColor(Pet.Color.GREY);

        mockMvc.perform(put("/api/v1/pets")
                .param("id", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(petDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePet() throws Exception {
        mockMvc.perform(delete("/api/v1/pets")
                .param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSaveOwner() throws Exception {
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setName("TestOwner");

        mockMvc.perform(post("/api/v1/owners")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ownerDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetOwners() throws Exception {
        List<OwnerDTO> owners = Arrays.asList(
            new OwnerDTO(),
            new OwnerDTO()
        );
        when(ownerService.getAll()).thenReturn(owners);

        mockMvc.perform(get("/api/v1/owners"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }
} 