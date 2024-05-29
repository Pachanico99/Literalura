package com.alura.challenge.Literalura.service;

import com.alura.challenge.Literalura.model.Book;
import com.alura.challenge.Literalura.repository.AuthorRepository;
import com.alura.challenge.Literalura.repository.BookRepository;
import com.alura.challenge.Literalura.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Transactional
    public void saveBookIfNotExists(Book book) {
        // Verifica si el libro ya existe por título
        Optional<Book> existingBook = Optional.ofNullable(bookRepository.getBookByTitle(book.getTitle()));
        if (!existingBook.isPresent()) {
            // Guarda y recupera los autores desde la base de datos
            book.getAuthors().forEach(author -> authorRepository.saveIfNotExist(author));

            // Guarda y recupera los lenguajes desde la base de datos
            book.getLanguages().forEach(language -> languageRepository.saveIfNotExist(language));

            // Guarda el libro
            bookRepository.save(book);
        }
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Optional<Book> getBookByTitle(String title) {
        return Optional.ofNullable(bookRepository.getBookByTitle(title));
    }
}
