package com.youcode.servicema.controllers;

import com.youcode.servicema.services.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cities")
@RequiredArgsConstructor
public class CItyController {
    private final CityService cityService;
    @GetMapping
    public String[] getCities() {
        return cityService.getCities();
    }
}
