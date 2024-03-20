package com.youcode.servicema.seeder;

import com.youcode.servicema.domain.entities.Authority;
import com.youcode.servicema.domain.entities.City;
import com.youcode.servicema.domain.entities.Role;
import com.youcode.servicema.domain.enums.AuthorityEnum;
import com.youcode.servicema.repositories.AuthorityRepository;
import com.youcode.servicema.repositories.CityRepository;
import com.youcode.servicema.repositories.RoleRepository;
import com.youcode.servicema.services.AuthorityService;
import com.youcode.servicema.services.CityService;
import com.youcode.servicema.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@RequiredArgsConstructor
@Component
public class CitySeeder implements CommandLineRunner {

    private final CityService cityService;
    private final CityRepository cityRepository;

    @Override
    public void run(String... args) {
        if (cityRepository.count() == 0) {
            seedCities();
        }
    }

    private void seedCities() {
        cityService.saveAll(Arrays.asList(
                City.builder().name("Casablanca").build(),
                City.builder().name("Rabat").build(),
                City.builder().name("Tanger").build(),
                City.builder().name("Marrakech").build(),
                City.builder().name("Fes").build(),
                City.builder().name("Agadir").build(),
                City.builder().name("Oujda").build(),
                City.builder().name("Kenitra").build(),
                City.builder().name("Tetouan").build(),
                City.builder().name("Safi").build(),
                City.builder().name("El Jadida").build(),
                City.builder().name("Nador").build(),
                City.builder().name("Settat").build(),
                City.builder().name("Khouribga").build(),
                City.builder().name("Beni Mellal").build(),
                City.builder().name("Meknes").build()
        ));
    }

}
