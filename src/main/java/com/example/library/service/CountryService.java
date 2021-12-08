package com.example.library.service;

import com.example.library.dto.PageDto;
import com.example.library.entity.Country;
import org.springframework.data.domain.Page;

public interface CountryService {

    Country getCountryById(Long id);

    Country getCountryByName(String name);

    Page<Country> getCountries(PageDto pageDto);
}
