package com.rafael.pets.service;

import com.rafael.pets.model.Owner;
import com.rafael.pets.model.OwnerDTO;

import java.util.List;

public interface OwnerService {
    void save(OwnerDTO owner);

    List<OwnerDTO> getAll();

    void updateById(OwnerDTO owner);

    void deleteById(long id);
}
