package com.example.library.service;

import com.example.library.dto.PageDto;
import com.example.library.entity.Author;
import com.example.library.model.AuthorModel;
import org.springframework.data.domain.Page;

public interface AuthorService {
    Author getAuthorById(Long id);

    Page<Author> getAuthors(PageDto pageDto);

    Page<Author> getAuthorsByCountryId(Long id, PageDto pageDto);

    Author addAuthor(AuthorModel authorModel);

    Author updateAuthor(Long id, AuthorModel authorModel);

    void deleteById(Long id);
}
