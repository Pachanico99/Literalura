package com.alura.challenge.Literalura.controller;

import com.alura.challenge.Literalura.model.Book;
import com.alura.challenge.Literalura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


public class BookController {

    @Autowired
    private BookService bookService;

    public void saveBook(Book book) {
        bookService.saveBookIfNotExists(book);
    }

    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    public Book getBookById(Long id) {
        return bookService.getBookById(id);
    }

    public Optional<Book> getBookByTitle(String title) {
        return bookService.getBookByTitle(title);
    }
}