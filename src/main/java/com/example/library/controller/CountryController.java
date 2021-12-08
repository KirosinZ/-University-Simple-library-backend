package com.example.library.controller;

import com.example.library.dto.PageDto;
import com.example.library.entity.Country;
import com.example.library.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("/countries")
public class CountryController {
    private final CountryService countryService;

    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Long id) {
        return new ResponseEntity<>(countryService.getCountryById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Country>> getCountrys(PageDto pageDto) {
        return new ResponseEntity<>(countryService.getCountries(pageDto), HttpStatus.OK);
    }
}
