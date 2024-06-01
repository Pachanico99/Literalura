package com.alura.challenge.Literalura.service;

import com.alura.challenge.Literalura.controller.LanguageController;
import com.alura.challenge.Literalura.model.Author;
import com.alura.challenge.Literalura.model.Book;
import com.alura.challenge.Literalura.repository.AuthorRepository;
import com.alura.challenge.Literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private LanguageController languageController;

    @Transactional
    public void saveBook(Book book) {
        Optional<Book> bookInDB = bookRepository.getBookByTitle(book.getTitle());
        if (!bookInDB.isPresent()){
            book.getAuthors().forEach(a -> {
                Optional<Author> authorInDB = authorRepository.getAuthorByName(a.getName());
                if(!authorInDB.isPresent()){
                    authorRepository.save(a);
                }else{
                    a.setId(authorInDB.get().getId());
                }
            });

            book.getLanguages().forEach(l -> languageController.saveIfNotExist(l));

            bookRepository.save(book);
        }else{
            System.out.println("The book [ " + book.getTitle() + " ] already exists in DB");
        }
    }

    public Book findBookByTitle(String title) {
        Optional<Book> bookInDB = bookRepository.getBookByTitle(title);
        return bookInDB.orElse(null);
    }

    public List<Book> getAllBooks(){
        return bookRepository.getAllBooks();
    }

    public List<Book> getBooksByLanguage(String languageCode){
        Optional<List<Book>> bookInDB = bookRepository.getBooksByLanguage(languageCode);
        return bookInDB.orElse(null);
    }

    public Book getBookMoreDownloaded(){
        Optional<Book> bookInDB = bookRepository.getBookMoreDownloaded();
        return bookInDB.orElse(null);
    }

    public List<Book> getTop10BooksDownloaded(){
        return bookRepository.getTop10BooksDownloaded();
    }

    public List<Book> getBooksByAuthorName(String authorName) {
        Optional<List<Book>> booksInDB = bookRepository.getBooksByAuthorName(authorName);
        return booksInDB.orElse(null);
    }

    public Double percentageByLanguage(String languageCode){
        return bookRepository.percentageBooksByLanguage(languageCode);
    }

    public Double percentageBooksByAuthorName(String name) {
        return bookRepository.percentageBooksByAuthorName(name);
    }

    public String getAuthorWithMoreDownload(){
        return bookRepository.getAuthorWithMoreDownload();
    }

    public Integer getMaxDownloadByAuthor(){
        return bookRepository.getMaxDownloadByAuthor();
    }
}