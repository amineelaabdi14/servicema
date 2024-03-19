package com.youcode.servicema.repositories;
import com.youcode.servicema.domain.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByIsDefaultTrue();
    Optional<Role> findByName(String name);

}
