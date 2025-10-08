package com.rafael.security.service;


import com.rafael.security.model.OwnerDTO;

import java.util.List;

public interface OwnerService {
    void save(OwnerDTO owner);

    List<OwnerDTO> getAll();

    void updateById(OwnerDTO owner);

    void deleteById(long id);
}