package com.rafael.pets.integration;

import com.rafael.pets.database.DataBase;
import com.rafael.pets.database.UnitOfWork;
import com.rafael.pets.model.Owner;
import com.rafael.pets.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
public class HibernateIntegrationTest {

    static DataBase database = new DataBase();
    UnitOfWork uow;

    @BeforeEach
    void setUp() {

        uow = database.CreateUnitOfWork();
        uow.getOwnerRepository().deleteAll();
        uow.getPetRepository().deleteAll();

        uow.commit();
        uow = database.CreateUnitOfWork();

    }

    @Test
    void testSaveAndRetrieveOwner() {
        Owner owner = new Owner();
        owner.setName("Тестовый Владелец");
        LocalDate date = LocalDate.of(2020, 1, 1);
        owner.setBirthday(date);

        uow.getOwnerRepository().save(owner);
        uow.commit();
        uow = database.CreateUnitOfWork();
        Owner retrievedOwner = uow.getOwnerRepository().getById(owner.getId());

        assertNotNull(retrievedOwner);
        assertEquals(owner.getName(), retrievedOwner.getName());
        assertNotNull(retrievedOwner.getBirthday());
        uow.commit();
    }

    @Test
    void testSaveAndRetrievePet() {
        // Создаем и сохраняем владельца
        Owner owner = new Owner();
        owner.setName("Владелец Питомца");
        LocalDate date = LocalDate.of(2020, 1, 1);
        owner.setBirthday(date);
        uow.getOwnerRepository().save(owner);

        Pet pet = new Pet();
        pet.setName("Тестовый Питомец");
        LocalDate date2 = LocalDate.of(2020, 1, 1);
        pet.setBirthday(date2);
        pet.setBreed("Тестовая порода");
        pet.setColor(Pet.Color.BLACK);
        pet.setOwner(owner);
        uow.getPetRepository().save(pet);

        uow.commit();
        uow = database.CreateUnitOfWork();

        Pet retrievedPet = uow.getPetRepository().getById(pet.getId());

        assertNotNull(retrievedPet);
        assertEquals(pet.getName(), retrievedPet.getName());
        assertEquals(pet.getBreed(), retrievedPet.getBreed());
        assertEquals(pet.getColor(), retrievedPet.getColor());
        assertNotNull(retrievedPet.getOwner());
        assertEquals(owner.getId(), retrievedPet.getOwner().getId());
        uow.commit();
    }

    @Test
    void testPetFriendship() {
        Pet pet1 = new Pet();
        pet1.setName("Питомец 1");
        LocalDate date2 = LocalDate.of(2020, 1, 1);
        pet1.setBirthday(date2);
        uow.getPetRepository().save(pet1);

        Pet pet2 = new Pet();
        pet2.setName("Питомец 2");
        LocalDate date3 = LocalDate.of(2020, 1, 1);
        pet2.setBirthday(date3);
        uow.getPetRepository().save(pet2);

        pet1.addFriend(pet2);
        uow.getPetRepository().update(pet1);
        uow.getPetRepository().update(pet2);

        uow.commit();
        uow = database.CreateUnitOfWork();

        Pet retrievedPet1 = uow.getPetRepository().getById(pet1.getId());
        Pet retrievedPet2 = uow.getPetRepository().getById(pet2.getId());

        assertNotNull(retrievedPet1.getFriends());
        assertNotNull(retrievedPet2.getFriends());
        assertFalse(retrievedPet1.getFriends().isEmpty());
        assertFalse(retrievedPet2.getFriends().isEmpty());

        assertTrue(retrievedPet1.getFriends().stream()
                .anyMatch(p -> p.getId() == retrievedPet2.getId()));
        assertTrue(retrievedPet2.getFriends().stream()
                .anyMatch(p -> p.getId() == retrievedPet1.getId()));
        uow.commit();
    }
} 