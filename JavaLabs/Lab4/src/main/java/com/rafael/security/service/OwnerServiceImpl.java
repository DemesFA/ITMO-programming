package com.rafael.security.service;

import com.rafael.security.model.Owner;
import com.rafael.security.model.OwnerDTO;
import com.rafael.security.model.Pet;
import com.rafael.security.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    @Transactional
    public void save(OwnerDTO ownerDTO) {
        var owner = new Owner();
        owner.setId(ownerDTO.getId());
        owner.setName(ownerDTO.getName());
        owner.setBirthday(ownerDTO.getBirthday());
        ownerRepository.save(owner);
    }

    @Override
    public List<OwnerDTO> getAll() {
        List<OwnerDTO> ownerDTOList = new ArrayList<>();
        for (Owner owner : ownerRepository.findAll()) {
            List<Long> petIds = new ArrayList<>();
            for (Pet pet : owner.getPets()) {
                petIds.add(pet.getId());
            }
            ownerDTOList.add(new OwnerDTO(owner.getId(), owner.getName(), owner.getBirthday(), petIds));
        }
        return ownerDTOList;
    }

    @Override
    @Transactional
    public void updateById(OwnerDTO ownerDTO) {
        Owner ownerdb = ownerRepository.findById(ownerDTO.getId()).get();
        if (Objects.nonNull(ownerDTO.getName())) {
            ownerdb.setName(ownerDTO.getName());
        }
        if (Objects.nonNull(ownerDTO.getBirthday())) {
            ownerdb.setBirthday(ownerDTO.getBirthday());
        }
        if (Objects.nonNull(ownerDTO.getPetsId())) {
            List<Pet> pets = new ArrayList<>();
            for (var petId : ownerDTO.getPetsId()) {
                var pet = new Pet();
                pet.setId(petId);
                pets.add(pet);
            }
            ownerdb.setPets(pets);
        }
        ownerRepository.save(ownerdb);
    }

    @Override
    public void deleteById(long id) {
        ownerRepository.deleteById(id);
    }
}