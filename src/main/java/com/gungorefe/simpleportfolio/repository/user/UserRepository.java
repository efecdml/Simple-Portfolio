package com.gungorefe.simpleportfolio.repository.user;

import com.gungorefe.simpleportfolio.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Boolean existsByRole_Name(String roleName);
}
