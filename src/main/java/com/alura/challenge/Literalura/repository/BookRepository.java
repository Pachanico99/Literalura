package com.alura.challenge.Literalura.repository;

import com.alura.challenge.Literalura.model.Author;
import com.alura.challenge.Literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {

    @Query("SELECT b FROM Book b")
    List<Book> getAllBooks();

    @Query("SELECT b FROM Book b WHERE b.title = :title")
    Book getBookByTitle(String title);

    default void saveIfNotExist(Book book){
        Optional<Book> bookInDB = Optional.ofNullable(getBookByTitle(book.getTitle()));
        if (!bookInDB.isPresent()){
            save(book);
        }else{
            System.out.println("The book [ " + book.getTitle() + " ] already exists in DB");
        }
    }
}