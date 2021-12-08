package com.example.library.repository;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.entity.Language;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByNameAndAuthorAndLanguage(String name, Author author, Language language);

    Page<Book> findAllByLanguageId(Long id, Pageable pageable);

    Page<Book> findAllByAuthorId(Long id, Pageable pageable);

    Page<Book> findAllByAuthorIdAndLanguageId(Long authId, Long langid, Pageable pageable);
}
