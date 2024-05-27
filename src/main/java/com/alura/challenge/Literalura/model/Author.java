package com.alura.challenge.Literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @Column(name = "author_id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private int dateOfDead;
    private int dateOfBorn;
    @ManyToMany
    private List<Book> books;
}
