package com.example.library.serviceimpl;

import com.example.library.dto.PageDto;
import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.entity.Language;
import com.example.library.exception.ResourceAlreadyExistsException;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.model.BookModel;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LanguageRepository;
import com.example.library.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final LanguageRepository languageRepository;

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id="+id+" does not exist"));
    }

    @Override
    public Page<Book> getBooks(PageDto pageDto) {
        return bookRepository.findAll(pageDto.getPageable());
    }

    @Override
    public Page<Book> getBooksByLanguageId(Long id, PageDto pageDto) {
        if (!languageRepository.existsById(id))
        {
            throw new ResourceNotFoundException("Language with id=" + id + " does not exist");
        }
        return bookRepository.findAllByLanguageId(id, pageDto.getPageable());
    }

    @Override
    public Page<Book> getBooksByAuthorId(Long id, PageDto pageDto) {
        if (!authorRepository.existsById(id))
        {
            throw new ResourceNotFoundException("Author with id=" + id + " does not exist");
        }
        return bookRepository.findAllByAuthorId(id, pageDto.getPageable());
    }

    @Override
    public Page<Book> getBooksByAuthorIdAndLanguageId(Long authId, Long langId, PageDto pageDto) {
        if (!authorRepository.existsById(authId))
        {
            throw new ResourceNotFoundException("Author with id=" + authId + " does not exist");
        }
        if (!languageRepository.existsById(langId))
        {
            throw new ResourceNotFoundException("Language with id=" + langId + " does not exist");
        }
        return bookRepository.findAllByAuthorIdAndLanguageId(authId, langId, pageDto.getPageable());
    }

    @Override
    public Page<Book> getBooksByExample(Example<BookModel> example, PageDto pageDto) {
        BookModel book = example.getProbe();

        Author auth = authorRepository.findById(book.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author with id=" + book.getAuthorId() + " does not exist"));
        Language lang = languageRepository.findById(book.getLanguageId())
                .orElseThrow(() -> new ResourceNotFoundException("Language with id=" + book.getLanguageId() + " does not exist"));
        Book bk = new Book(0L, "", auth, lang);
        ExampleMatcher ignorename = ExampleMatcher.matchingAll().withIgnorePaths("id","name");
        Example<Book> ex = Example.of(bk, ignorename);
        return bookRepository.findAll(ex, pageDto.getPageable());
    }

    @Override
    public Book addBook(BookModel bookModel) {
        Author author = authorRepository
                .findById(bookModel.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author with id="+bookModel.getAuthorId()+" does not exist"));
        Language language = languageRepository
                .findById(bookModel.getLanguageId())
                .orElseThrow(() -> new ResourceNotFoundException("Language with id="+bookModel.getLanguageId()+" does not exist"));
        if (bookRepository.existsByNameAndAuthorAndLanguage(bookModel.getName(), author, language))
        {
            throw new ResourceAlreadyExistsException("The book with such name, author and on that language already exists");
        }
        Book book = new Book(bookModel.getName(), author, language);
        bookRepository.save(book);
        return book;
    }

    @Override
    public Book updateBook(Long id, BookModel bookModel) {
        if (!bookRepository.existsById(id))
        {
            throw new ResourceNotFoundException("Book with id="+id+" does not exist");
        }
        Language lang = languageRepository.findById(bookModel.getLanguageId())
                        .orElseThrow(() -> new ResourceNotFoundException("Language with id="+bookModel.getLanguageId()+" does not exist"));
        Author auth = authorRepository.findById(bookModel.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author with id="+bookModel.getAuthorId()+" does not exist"));
        Book book = new Book(id, bookModel.getName(), auth, lang);
        bookRepository.save(book);
        return book;
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
