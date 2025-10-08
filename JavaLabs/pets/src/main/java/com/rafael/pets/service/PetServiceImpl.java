package com.rafael.pets.service;

import com.rafael.pets.model.Owner;
import com.rafael.pets.model.Pet;
import com.rafael.pets.model.PetDTO;
import com.rafael.pets.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    @Autowired
    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    @Transactional
    public void save(PetDTO petDTO) {
        var owner = new Owner();
        owner.setId(petDTO.getOwnerId());
        var pet = new Pet();
        pet.setId(petDTO.getId());
        pet.setOwner(owner);
        pet.setName(petDTO.getName());
        pet.setBreed(petDTO.getBreed());
        pet.setColor(petDTO.getColor());
        pet.setBirthday(petDTO.getBirthday());
        petRepository.save(pet);
    }

    @Override
    public List<PetDTO> getAll() {
        List<PetDTO> pets = new ArrayList<>();

        for (Pet pet : petRepository.findAll()) {
            List<Long> friends = new ArrayList<>();
            for (Pet friend : pet.getFriends()) {
                friends.add(friend.getId());
            }
            pets.add(new PetDTO(pet.getId(), pet.getName(), pet.getBirthday(), pet.getBreed(), pet.getColor(), pet.getOwner().getId(), friends));
        }

        return pets;
    }

    @Override
    @Transactional
    public void updateById(PetDTO petDTO) {
        Pet petdb = petRepository.findById(petDTO.getId()).get();
        if (Objects.nonNull(petDTO.getName())) {
            petdb.setName(petDTO.getName());
        }
        if (Objects.nonNull(petDTO.getBirthday())) {
            petdb.setBirthday(petDTO.getBirthday());
        }
        if (Objects.nonNull(petDTO.getOwnerId())) {
            var owner = new Owner();
            owner.setId(petDTO.getOwnerId());
            petdb.setOwner(owner);
        }
        if (Objects.nonNull(petDTO.getBreed())) {
            petdb.setBreed(petDTO.getBreed());
        }
        if (Objects.nonNull(petDTO.getColor())) {
            petdb.setColor(petDTO.getColor());
        }
        if (Objects.nonNull(petDTO.getFriendsId())) {
            List<Pet> pets = new ArrayList<>();
            for (var petid : petDTO.getFriendsId()) {
                var pet = new Pet();
                pet.setId(petid);
                pets.add(pet);
            }
            petdb.setFriends(pets);
        }
        petRepository.save(petdb);

    }

    @Override
    @Transactional
    public void deleteById(long id) {
        petRepository.deleteById(id);
    }


    @Override
    @Transactional
    public void addBothFriends(long id1, long id2) {
        Pet petdb = petRepository.findById(id1).get();
        Pet petdb1 = petRepository.findById(id2).get();
        petdb.addFriend(petdb1);
        petRepository.save(petdb);
        petRepository.save(petdb1);
    }


    @Override
    public List<PetDTO> getByColor(Pet.Color color, int page, int size) {
        try {
            List<PetDTO> pets = new ArrayList<>();
            Pageable paging = PageRequest.of(page, size);
            Page<Pet> pageTuTs;
            pageTuTs = petRepository.findByColor(color, paging);
            pageTuTs.getContent().forEach(pet -> {
                List<Long> friends = new ArrayList<>();
                for (Pet friend : pet.getFriends()) {
                    friends.add(friend.getId());
                }
                pets.add(new PetDTO(pet.getId(), pet.getName(), pet.getBirthday(), pet.getBreed(), pet.getColor(), pet.getOwner().getId(), friends));
            });
            return pets;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<PetDTO> getByBreed(String breed, int page, int size) {
        try {
            List<PetDTO> pets = new ArrayList<>();
            Pageable paging = PageRequest.of(page, size);
            Page<Pet> pageTuTs;
            pageTuTs = petRepository.findByBreed(breed, paging);
            pageTuTs.getContent().forEach(pet -> {
                List<Long> friends = new ArrayList<>();
                for (Pet friend : pet.getFriends()) {
                    friends.add(friend.getId());
                }
                pets.add(new PetDTO(pet.getId(), pet.getName(), pet.getBirthday(), pet.getBreed(), pet.getColor(), pet.getOwner().getId(), friends));
            });
            return pets;
        } catch (Exception e) {
            return null;
        }
    }

}
