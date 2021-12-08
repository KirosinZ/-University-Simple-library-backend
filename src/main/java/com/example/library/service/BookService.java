package com.example.library.service;

import com.example.library.dto.PageDto;
import com.example.library.entity.Book;
import com.example.library.model.BookModel;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;

public interface BookService {

    Book getBookById(Long id);

    Page<Book> getBooks(PageDto pageDto);

    Page<Book> getBooksByLanguageId(Long id, PageDto pageDto);

    Page<Book> getBooksByAuthorId(Long id, PageDto pageDto);

    Page<Book> getBooksByAuthorIdAndLanguageId(Long authId, Long langId, PageDto pageDto);

    Page<Book> getBooksByExample(Example<BookModel> example, PageDto pageDto);

    Book addBook(BookModel bookModel);

    Book updateBook(Long id, BookModel bookModel);

    void deleteById(Long id);
}
