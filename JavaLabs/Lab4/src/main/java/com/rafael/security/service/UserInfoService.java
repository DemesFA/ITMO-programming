package com.rafael.security.service;

import com.rafael.security.model.Owner;
import com.rafael.security.model.User;
import com.rafael.security.repository.OwnerRepository;
import com.rafael.security.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final UserInfoRepository userInfoRepository;

    private final OwnerRepository ownerRepository;

    @Autowired
    UserInfoService(PasswordEncoder passwordEncoder, UserInfoRepository userInfoRepository, OwnerRepository ownerRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userInfoRepository = userInfoRepository;
        this.ownerRepository = ownerRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userInfoRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserInfoDetails(user);
    }

    public String addUser(User user, Owner owner) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        owner = ownerRepository.save(owner);
        user.setOwnerId(owner);
        return userInfoRepository.save(user).getUsername();

    }
}