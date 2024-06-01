package com.alura.challenge.Literalura.repository;

import com.alura.challenge.Literalura.model.Author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Long> {

    @Query("SELECT a FROM Author a WHERE a.name = :name")
    Optional<Author> getAuthorByName(String name);

    @Query("SELECT a FROM Author a WHERE :yearDead BETWEEN a.bornYear AND a.deadYear")
    Optional<List<Author>> getAuthorsAliveByYear(int yearDead);

    @Query("SELECT a FROM Author a")
    List<Author> getAllAuthors();
}
