package com.company.mscountry.service;

import com.company.mscountry.model.CountryResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {

    public List<CountryResponse> getAvailableCountries(String currency) {
        if (currency.equals("USD")) {
            return List.of(
                    new CountryResponse("USA", BigDecimal.ZERO),
                    new CountryResponse("GER", BigDecimal.ONE) );
        }
        return new ArrayList<>();
    }

}
