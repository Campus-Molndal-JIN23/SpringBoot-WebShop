package com.AAQV.webV1.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import com.AAQV.webV1.model.ApplicationUser;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Integer> {
    Optional<ApplicationUser> findByUsername(String username);
    Optional<User> findById(Long userId);
    void deleteByUserId(Integer userId);


}