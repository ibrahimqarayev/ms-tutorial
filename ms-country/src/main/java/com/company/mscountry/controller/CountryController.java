package com.company.mscountry.controller;

import com.company.mscountry.model.CountryResponse;
import com.company.mscountry.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryService countryService;

    @GetMapping
    public List<CountryResponse> getAvailableCountries(@RequestParam String currency) {
        return countryService.getAvailableCountries(currency);
    }

}
