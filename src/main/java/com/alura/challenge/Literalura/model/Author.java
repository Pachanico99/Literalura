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

    @Column
    private int deadYear;

    @Column
    private int bornYear;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    public Author(){}

    public Author(RAuthor author){
        this.name = author.name();
        this.bornYear = author.bornYear();
        this.deadYear = author.deathYear();
    }

    @Override
    public String toString() {
        return "Author{" + "\n" +
                "id=" + id + "\n" +
                ", name='" + name + '\'' + "\n" +
                ", deadYear=" + deadYear + "\n" +
                ", bornYear=" + bornYear + "\n" +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeadYear() {
        return deadYear;
    }

    public void setDeadYear(int deadYear) {
        this.deadYear = deadYear;
    }

    public int getBornYear() {
        return bornYear;
    }

    public void setBornYear(int bornYear) {
        this.bornYear = bornYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
