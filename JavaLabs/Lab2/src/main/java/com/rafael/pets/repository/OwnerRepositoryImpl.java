package com.rafael.pets.repository;

import com.rafael.pets.model.Owner;
import com.rafael.pets.model.Pet;
import org.hibernate.Session;

import java.util.List;

public class OwnerRepositoryImpl implements Repository<Owner> {

    private final Session session;

    public OwnerRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public Owner save(Owner entity) {
        session.save(entity);
        return entity;
    }

    @Override
    public void deleteById(long id) {
        session.delete(session.get(Owner.class, id));
    }

    @Override
    public void deleteByEntity(Owner entity) {
        session.delete(entity);
    }

    @Override
    public void deleteAll() {
        session.createNativeQuery("truncate table owners cascade").executeUpdate();
    }

    @Override
    public Owner update(Owner entity) {
        session.update(entity);
        return entity;
    }

    @Override
    public Owner getById(long id) {
        return session.get(Owner.class, id);
    }

    @Override
    public List<Owner> getAll() {
        String hql = "SELECT p FROM Owner p JOIN FETCH p.pets";
        return session.createQuery(hql, Owner.class).getResultList();
    }
}
