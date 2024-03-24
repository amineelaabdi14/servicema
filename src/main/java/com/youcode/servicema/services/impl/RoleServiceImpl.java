package com.youcode.servicema.services.impl;

import com.youcode.servicema.domain.entities.Authority;
import com.youcode.servicema.domain.entities.Role;
import com.youcode.servicema.repositories.RoleRepository;
import com.youcode.servicema.services.AuthorityService;
import com.youcode.servicema.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final AuthorityService authorityService;
    private final RoleRepository roleRepository;
    @Override
    public List<Role> getAll(){
            return roleRepository.findAll();
    }

    @Override
    public Role save(Role role, boolean isSeed) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!isSeed && authentication != null) {
            List<String> authorities = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();
        }


        return roleRepository.save(role);
    }
    @Override
    public Optional<Role> findDefaultRole(){
        return roleRepository.findByIsDefaultTrue();
    }

    @Override
    public Role grantAuthorities(Long authorityId, Long roleId){
        List<String> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        if (authorities.contains("GRANT_AUTHORITY_TO_ROLE")){
            Role role = roleRepository.findById(roleId).orElse(null);
                Authority authority = authorityService.getById(authorityId).orElse(null);
                if (role != null && authority != null){
                List<Authority> currentAuthorities = role.getAuthorities();
                if (currentAuthorities.stream().anyMatch(a -> a.getId().equals(authorityId))) return null;
                currentAuthorities.add(authority);
                role.setAuthorities(currentAuthorities);
                return roleRepository.save(role);
            }
            return null;
        }
        return null;
    }


    @Override
    public Role revokeAuthorities(Long authorityId, Long roleId){
        List<String> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        if (authorities.contains("REVOKE_AUTHORITY_FROM_ROLE")){
            Role role = roleRepository.findById(roleId).orElse(null);
            Authority authority = authorityService.getById(authorityId).orElse(null);
            if (role != null && authority != null){
                List<Authority> currentAuthorities = role.getAuthorities();
                currentAuthorities = currentAuthorities.stream().filter(a -> !a.getId().equals(authorityId)).collect(Collectors.toList());
                role.setAuthorities(currentAuthorities);
                return roleRepository.save(role);
            }
            return null;
        }
        return null;
    }


    @Override
    public Optional<Role> getById(Long id){
        return roleRepository.findById(id);
    }

    @Override
    public Optional<Role> getByName(String name){
        return roleRepository.findByName(name);
    }

    @Override
    public void delete(Long id){
        List<String> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        if (authorities.contains("DELETE_ROLE"))getById(id).ifPresent(roleRepository::delete);
    }

    @Override
    public Optional<Role> getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

}

