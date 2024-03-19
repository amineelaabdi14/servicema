package com.youcode.servicema.seeders;

import com.youcode.servicema.domain.entities.Authority;
import com.youcode.servicema.domain.enums.AuthorityEnum;
import com.youcode.servicema.repositories.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthoritySeeder implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) throws Exception {
        if (authorityRepository.count() == 0) {
            seedAuthorities();
        }
    }

    public void seedAuthorities() {
        List<Authority> authorities = new ArrayList<>();
        for (AuthorityEnum authorityName : AuthorityEnum.values()) {
            Authority authority = Authority.builder()
                    .name(authorityName)
                    .build();
            authorities.add(authority);
        }
        authorityRepository.saveAll(authorities);
    }
}
