package com.alura.challenge.Literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "languages")
public class Language {
    @Id
    @Column(name = "language_id")
    private String languageCode;

    @ManyToMany(mappedBy = "languages")
    private List<Book> books;

    public Language() {}

    public Language(String languageCode){
        this.languageCode = languageCode;
    }

    @Override
    public String toString() {
        return "Language{" + "\n" +
                "lenguajeCode='" + languageCode + '\'' + "\n" +
                '}';
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
