package com.rafael.pets.repository;

import com.rafael.pets.model.Pet;
import org.hibernate.Session;

import java.util.List;

public class PetRepositoryImpl implements Repository<Pet> {

    private final Session session;

    public PetRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public Pet save(Pet entity) {
        session.save(entity);
        return entity;
    }

    @Override
    public void deleteById(long id) {
        Pet pet = session.get(Pet.class, id);
        if (pet != null) {
            session.delete(pet);
        }
    }

    @Override
    public void deleteByEntity(Pet entity) {
        session.delete(entity);
    }

    @Override
    public void deleteAll() {
        session.createNativeQuery("truncate table pets cascade").executeUpdate();
    }

    @Override
    public Pet update(Pet entity) {
        session.update(entity);
        return entity;
    }

    @Override
    public Pet getById(long id) {
        return session.get(Pet.class, id);
    }

    @Override
    public List<Pet> getAll() {
        String hql = "SELECT p FROM Pet p JOIN FETCH p.owner";
        return session.createQuery(hql, Pet.class).getResultList();
    }

}
