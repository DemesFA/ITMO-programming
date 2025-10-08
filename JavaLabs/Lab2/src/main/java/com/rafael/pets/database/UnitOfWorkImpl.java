package com.rafael.pets.database;

import com.rafael.pets.model.Owner;
import com.rafael.pets.model.Pet;
import com.rafael.pets.repository.OwnerRepositoryImpl;
import com.rafael.pets.repository.PetRepositoryImpl;
import com.rafael.pets.repository.Repository;
import org.hibernate.Session;

public class UnitOfWorkImpl implements UnitOfWork, AutoCloseable {

    private final Session session;

    private final OwnerRepositoryImpl ownerRepository;

    private final PetRepositoryImpl petRepository;


    public UnitOfWorkImpl(Session session) {
        this.session = session;
        this.session.beginTransaction();
        this.ownerRepository = new OwnerRepositoryImpl(session);
        this.petRepository = new PetRepositoryImpl(session);

    }

    @Override
    public void close() {
        if (session.getTransaction().isActive()) {
            session.getTransaction().rollback();
        }
        this.session.close();
    }


    @Override
    public Repository<Owner> getOwnerRepository() {
        return ownerRepository;
    }

    @Override
    public Repository<Pet> getPetRepository() {
        return petRepository;
    }

    @Override
    public void commit() {
        session.getTransaction().commit();
    }


}
