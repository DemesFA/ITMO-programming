package com.rafael.pets;

import com.rafael.pets.database.DataBase;
import com.rafael.pets.model.Owner;
import com.rafael.pets.model.Pet;
import com.rafael.pets.repository.PetRepositoryImpl;
import com.rafael.pets.repository.Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.type.LocalDateType;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        DataBase database = new DataBase();
        var uow = database.CreateUnitOfWork();
        Owner owner = new Owner();
        owner.setName("Raf");
        LocalDate date = LocalDate.of(2018, 1, 1);
        LocalDate date2 = LocalDate.of(2017, 1, 1);
        owner.setBirthday(date);
        Pet pet = new Pet();
        pet.setOwner(owner);
        pet.setName("RafPet");
        pet.setBirthday(date2);
        owner.addPet(pet);
        uow.getOwnerRepository().save(owner);
        uow.getPetRepository().save(pet);

        uow.commit();

    }
}