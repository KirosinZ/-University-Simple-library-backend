package com.example.library.controller;

import com.example.library.dto.PageDto;
import com.example.library.entity.Author;
import com.example.library.model.AuthorModel;
import com.example.library.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Author>> getAuthors(@RequestParam(name = "ctry", required = false) Long ctryId, PageDto pageDto) {
        if (ctryId != null) return new ResponseEntity<>(authorService.getAuthorsByCountryId(ctryId, pageDto), HttpStatus.OK);
        return new ResponseEntity<>(authorService.getAuthors(pageDto), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Author> addAuthor(@RequestBody AuthorModel authorModel) {
        return new ResponseEntity<>(authorService.addAuthor(authorModel), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody AuthorModel authorModel) {
        return new ResponseEntity<>(authorService.updateAuthor(id, authorModel), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        authorService.deleteById(id);
    }

}
