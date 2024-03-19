package com.youcode.servicema.seeders;

import com.youcode.servicema.domain.entities.Authority;
import com.youcode.servicema.domain.entities.Role;
import com.youcode.servicema.domain.enums.AuthorityEnum;
import com.youcode.servicema.repositories.AuthorityRepository;
import com.youcode.servicema.repositories.RoleRepository;
import com.youcode.servicema.services.AuthorityService;
import com.youcode.servicema.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@RequiredArgsConstructor
@Component
public class RoleSeeder implements CommandLineRunner {

    private final RoleService roleService;
    private final AuthorityService authorityService;
    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;


    @Override
    public void run(String... args) {
        if (roleRepository.count() == 0) {
            seedRoles();
        }
    }

    private void seedRoles() {
        // create authorities
        Authority addservice = authorityService.getByName(AuthorityEnum.ADD_SERVICE)
                .orElseThrow(() -> new RuntimeException("authority not found"));

        Authority editservice = authorityService.getByName(AuthorityEnum.EDIT_SERVICE)
                .orElseThrow(() -> new RuntimeException("authority not found"));

        Authority deleteservice = authorityService.getByName(AuthorityEnum.DELETE_SERVICE)
                .orElseThrow(() -> new RuntimeException("authority not found"));

        Authority getreports = authorityService.getByName(AuthorityEnum.GET_REPORTS)
                .orElseThrow(() -> new RuntimeException("authority not found"));

        Authority viewusers = authorityService.getByName(AuthorityEnum.VIEW_USERS)
                .orElseThrow(() -> new RuntimeException("authority not found"));

        // Create roles and associate authorities
        Role userRole = Role.builder()
                .name("USER")
                .isDefault(true)
                .build();

        Role seller = Role.builder()
                .name("SELLER")
                .authorities(Arrays.asList(addservice, editservice, deleteservice))
                .build();

        Role superAdminRole = Role.builder()
                .name("ADMIN")
                .authorities(authorityRepository.findAll())
                .build();

        roleService.save(userRole, true);
        roleService.save(seller, true);
        roleService.save(superAdminRole, true);
    }

}
