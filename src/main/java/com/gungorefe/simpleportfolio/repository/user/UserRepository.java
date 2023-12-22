package com.gungorefe.simpleportfolio.repository.user;

import com.gungorefe.simpleportfolio.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Boolean existsByRole_Name(String roleName);

    @Query("select u from User u left join fetch u.role where u.email = ?1")
    Optional<User> findByEmailWithRole(String email);

    @Query("select new com.gungorefe.simpleportfolio.entity.user.User(u.email, u.role.name) from User u")
    Set<User> findEmailAndRoleNameOfAll();

    Boolean existsByEmail(String email);

    @Query("select new com.gungorefe.simpleportfolio.entity.user.User(u.id) from User u where u.email = ?1")
    Optional<User> findIdByEmail(String email);
}
