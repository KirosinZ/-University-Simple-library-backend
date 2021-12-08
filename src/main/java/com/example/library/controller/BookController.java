package com.example.library.controller;

import com.example.library.dto.PageDto;
import com.example.library.entity.Book;
import com.example.library.model.BookModel;
import com.example.library.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Book>> getBooks(@RequestParam(name = "lang", required = false) Long langId, @RequestParam(name = "auth", required = false) Long authId, PageDto pageDto) {
        if (langId == null && authId == null) return new ResponseEntity<>(bookService.getBooks(pageDto), HttpStatus.OK);
        if (langId != null && authId == null) return new ResponseEntity<>(bookService.getBooksByLanguageId(langId, pageDto), HttpStatus.OK);
        if (langId == null) return new ResponseEntity<>(bookService.getBooksByAuthorId(authId, pageDto), HttpStatus.OK);
        return new ResponseEntity<>(bookService.getBooksByAuthorIdAndLanguageId(authId, langId, pageDto), HttpStatus.OK);
    }

    /*@GetMapping
    public ResponseEntity<Page<Book>> getBooks(@RequestParam(required = false) Map<String, String> allParams, PageDto pageDto)
    {
        if (allParams == null || allParams.isEmpty()) return new ResponseEntity<>(bookService.getBooks(pageDto), HttpStatus.OK);
        Long authId = (allParams.get("auth") == null ? null : Long.parseLong(allParams.get("auth")));
        Long langId = (allParams.get("lang") == null ? null : Long.parseLong(allParams.get("lang")));
        return new ResponseEntity<>(bookService.getBooksByExample(Example.of(new BookModel(null, authId, langId)),pageDto), HttpStatus.OK);
    }*/

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody BookModel bookModel) {
        return new ResponseEntity<>(bookService.addBook(bookModel), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookModel bookModel) {
        return new ResponseEntity<>(bookService.updateBook(id, bookModel), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

}
