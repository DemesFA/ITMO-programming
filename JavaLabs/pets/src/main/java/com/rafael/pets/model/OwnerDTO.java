package com.rafael.pets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDTO {
    private long id;
    private String name;
    private LocalDate birthday;
    private List<Long> petsId = new ArrayList<>();
}
