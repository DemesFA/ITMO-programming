package com.rafael.pets.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "pets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {

    public enum Color {
        WHITE, GREY, BLACK, GINGER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "breed")
    private String breed;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "color")
    private Color color;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Owner owner;

    @ManyToMany
    @JoinTable(
            name = "pet_friends",
            joinColumns = @JoinColumn(name = "pet_id"),
            inverseJoinColumns = @JoinColumn(name = "pet2_id")
    )
    private List<Pet> friends = new ArrayList<>();


    public void addFriend(Pet pet) {
        friends.add(pet);
        pet.friends.add(this);
    }
}