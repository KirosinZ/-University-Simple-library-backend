package com.example.library.serviceimpl;

import com.example.library.dto.PageDto;
import com.example.library.entity.Country;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.repository.CountryRepository;
import com.example.library.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    public Country getCountryById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country with id="+id+" does not exist"));
    }

    @Override
    public Country getCountryByName(String name) {
        return countryRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Country with name="+name+" does not exist"));
    }

    @Override
    public Page<Country> getCountries(PageDto pageDto) {
        return countryRepository.findAll(pageDto.getPageable());
    }
}
