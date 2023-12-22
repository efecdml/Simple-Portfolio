package com.gungorefe.simpleportfolio.setup;

import com.gungorefe.simpleportfolio.entity.user.Role;
import com.gungorefe.simpleportfolio.entity.user.User;
import com.gungorefe.simpleportfolio.repository.user.RoleRepository;
import com.gungorefe.simpleportfolio.repository.user.UserRepository;
import com.gungorefe.simpleportfolio.vo.RoleName;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@RequiredArgsConstructor
@Log4j2
@Transactional
@Component
public class UserSetup implements ApplicationListener<ContextRefreshedEvent> {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private Role roleAdmin;
    private Role roleMod;
    private User userAdmin;
    private User userMod;

    private void insertRolesIfNotExist() {
        if (!roleRepository.existsByName(RoleName.ROLE_ADMIN.name())) {
            log.warn("Administrator role not found, inserting..");
            roleAdmin = roleRepository.save(new Role(RoleName.ROLE_ADMIN.name()));
        }
        if (!roleRepository.existsByName(RoleName.ROLE_MOD.name())) {
            log.warn("Moderator role not found, inserting..");
            roleMod = roleRepository.save(new Role(RoleName.ROLE_MOD.name()));
        }
    }

    private void insertAdminUserIfNotExist() {
        if (!userRepository.existsByRole_Name(RoleName.ROLE_ADMIN.name())) {
            log.warn("User with administrator role not found, inserting..");
            userAdmin = userRepository.save(new User(
                    "admin@email.com",
                    passwordEncoder.encode("admin"),
                    roleAdmin
            ));
        }
    }

    private void initializeTestSetup() {
        if (!userRepository.existsByRole_Name(RoleName.ROLE_MOD.name())) {
            log.warn("User with moderator role not found, inserting..");
            userMod = userRepository.save(new User(
                    "mod@email.com",
                    passwordEncoder.encode("mod"),
                    roleMod
            ));

            System.out.println(MessageFormat.format(
                    "Administrator user email: {0}, password: {1}",
                    userAdmin.getEmail(),
                    userAdmin.getPassword()
            ));
            System.out.println(MessageFormat.format(
                    "Moderator user email: {0}, password: {1}",
                    userMod.getEmail(),
                    userMod.getPassword()
            ));
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        insertRolesIfNotExist();
        insertAdminUserIfNotExist();
        initializeTestSetup();
    }
}
