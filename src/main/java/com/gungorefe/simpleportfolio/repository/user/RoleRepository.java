package com.gungorefe.simpleportfolio.repository.user;

import com.gungorefe.simpleportfolio.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Boolean existsByName(String name);

    Optional<Role> findByName(String name);
}
