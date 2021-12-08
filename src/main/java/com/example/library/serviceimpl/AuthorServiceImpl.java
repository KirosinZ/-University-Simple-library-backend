package com.example.library.serviceimpl;

import com.example.library.dto.PageDto;
import com.example.library.entity.Author;
import com.example.library.entity.Country;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.model.AuthorModel;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.CountryRepository;
import com.example.library.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id="+id+" does not exist"));
    }

    @Override
    public Page<Author> getAuthors(PageDto pageDto) {
        return authorRepository.findAll(pageDto.getPageable());
    }

    @Override
    public Page<Author> getAuthorsByCountryId(Long id, PageDto pageDto) {
        if (!countryRepository.existsById(id))
        {
            throw new ResourceNotFoundException("Country with id=" + id + " does not exist");
        }
        return authorRepository.findAllByCountryId(id, pageDto.getPageable());
    }

    @Override
    public Author addAuthor(AuthorModel authorModel) {
        Country country = countryRepository
                .findById(authorModel.getCountryId())
                .orElseThrow(() -> new ResourceNotFoundException("Country with id="+authorModel.getCountryId()+" does not exist"));
        Author author = new Author(authorModel.getFullName(), country);
        authorRepository.save(author);
        return author;
    }

    @Override
    public Author updateAuthor(Long id, AuthorModel authorModel) {
        if (!authorRepository.existsById(id))
        {
            throw new ResourceNotFoundException("Author with id="+id+" does not exist");
        }
        Country ctry = countryRepository.findById(authorModel.getCountryId())
                .orElseThrow(() -> new ResourceNotFoundException("Country with id="+authorModel.getCountryId()+" does not exist"));
        Author auth = new Author(id, authorModel.getFullName(), ctry);
        authorRepository.save(auth);
        return auth;
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
