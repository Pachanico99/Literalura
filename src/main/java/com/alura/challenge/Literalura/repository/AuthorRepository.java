package com.alura.challenge.Literalura.repository;

import com.alura.challenge.Literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Long> {

    @Query("SELECT author FROM Author author WHERE author.name = :name")
    Author getAuthorByName(String name);

    default void saveIfNotExist(Author author){
        Optional<Author> authorInDB = Optional.ofNullable(getAuthorByName(author.getName()));
        if (!authorInDB.isPresent()){
            save(author);
        }else{
            System.out.println("The author [ " + author.getName() + " ] already exists in DB");
        }
    }
}
