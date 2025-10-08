package com.rafael.security.repository;

import com.rafael.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
