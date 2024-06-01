package com.alura.challenge.Literalura.service;

import com.alura.challenge.Literalura.model.Author;
import com.alura.challenge.Literalura.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors(){
        return authorRepository.getAllAuthors();
    }

    public Author getAuthorByName(String name){
        Optional<Author> authorInDB = authorRepository.getAuthorByName(name);
        return authorInDB.orElse(null);
    }

    public List<Author> getAuthorsAliveByYear(int yearDead){
        Optional<List<Author>> authorsAlives = authorRepository.getAuthorsAliveByYear(yearDead);
        return authorsAlives.orElse(null);
    }
}
