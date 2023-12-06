package com.AAQV.webV1.repository;

import java.util.Optional;

import com.AAQV.webV1.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Optional<Role> findByAuthority(String authority);
}