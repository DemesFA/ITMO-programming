package com.rafael.pets.database;

import com.rafael.pets.model.Owner;
import com.rafael.pets.model.Pet;
import com.rafael.pets.repository.OwnerRepositoryImpl;
import com.rafael.pets.repository.Repository;

public interface UnitOfWork {
    Repository<Owner> getOwnerRepository();

    Repository<Pet> getPetRepository();

    void commit();
}
