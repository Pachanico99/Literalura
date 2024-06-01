package com.alura.challenge.Literalura.controller;

import com.alura.challenge.Literalura.model.Book;
import com.alura.challenge.Literalura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    public Book findBookByTitle(String title){
        return bookService.findBookByTitle(title);
    }

    public void saveBook(Book book) {
        bookService.saveBook(book);
    }

    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    public List<Book> getBooksByLanguage(String languageCode){
        return bookService.getBooksByLanguage(languageCode);
    }

    public Book getBookMoreDownloaded(){
        return bookService.getBookMoreDownloaded();
    }

    public List<Book> getTop10BooksDownloaded(){
        return bookService.getTop10BooksDownloaded();
    }

    public List<Book> getBooksByAuthorName(String authorName) {
        return bookService.getBooksByAuthorName(authorName);
    }

    public Double porcentageByLanguage(String languageCode){
        return bookService.percentageByLanguage(languageCode);
    }

    public Double percentageBooksByAuthorName(String name) {
        return bookService.percentageBooksByAuthorName(name);
    }

    public String getAuthorWithMoreDownload(){
        return bookService.getAuthorWithMoreDownload();
    }

    public Integer getMaxDownloadByAuthor(){
        return bookService.getMaxDownloadByAuthor();
    }
}
