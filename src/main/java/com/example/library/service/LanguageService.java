package com.example.library.service;

import com.example.library.dto.PageDto;
import com.example.library.entity.Language;
import org.springframework.data.domain.Page;

public interface LanguageService {

    Language getLanguageById(Long id);

    Language getLanguageByName(String name);

    Page<Language> getLanguages(PageDto pageDto);
}
