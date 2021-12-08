package com.example.library.controller;

import com.example.library.dto.PageDto;
import com.example.library.entity.Language;
import com.example.library.service.LanguageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("/languages")
public class LanguageController {

    private final LanguageService languageService;

    @GetMapping("/{id}")
    public ResponseEntity<Language> getLanguageById(@PathVariable Long id) {
        return new ResponseEntity<>(languageService.getLanguageById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Language>> getLanguages(PageDto pageDto) {
        return new ResponseEntity<>(languageService.getLanguages(pageDto), HttpStatus.OK);
    }
}
