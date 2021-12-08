package com.example.library.serviceimpl;

import com.example.library.dto.PageDto;
import com.example.library.entity.Language;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.repository.LanguageRepository;
import com.example.library.service.LanguageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    @Override
    public Language getLanguageById(Long id) {
        return languageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Language with id="+id+" does not exist"));
    }

    @Override
    public Language getLanguageByName(String name) {
        return languageRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Language with name="+name+" does not exist"));
    }

    @Override
    public Page<Language> getLanguages(PageDto pageDto) {
        return languageRepository.findAll(pageDto.getPageable());
    }
}
