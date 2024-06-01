package com.alura.challenge.Literalura.controller;

import com.alura.challenge.Literalura.model.Author;
import com.alura.challenge.Literalura.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    public List<Author> getAllAuthors(){
        return  authorService.getAllAuthors();
    }

    public Author getAuthorByName(String name){
        return authorService.getAuthorByName(name);
    }

    public List<Author> getAuthorsAliveByYear(int yearDead){
        return authorService.getAuthorsAliveByYear(yearDead);
    }
}
