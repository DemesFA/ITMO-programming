package com.rafael.security.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDTO {
    private long id;
    private String name;
    private LocalDate birthday;
    private String breed;
    private Pet.Color color;
    private Long ownerId;
    private List<Long> friendsId = new ArrayList<>();
}