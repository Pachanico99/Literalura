package com.alura.challenge.Literalura.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "books_has_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "books_has_languages",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"))
    private List<Language> languages;

    @Column
    private Integer downloadCount;

    public Book() {}

    public Book(String title, List<Author> authors, List<Language> languages, Integer downloadCount) {
        this.title = title;
        this.authors = authors;
        this.languages = languages;
        this.downloadCount = downloadCount;
    }

    public Book(RBook rBook) {
        this.authors = rBook.authors().stream().map(Author::new).collect(Collectors.toList());
        this.downloadCount = rBook.downloadCount();
        this.title = rBook.title();
        this.languages = rBook.languages().stream().map(Language::new).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return  "********************************************\n" +
                "Book:\n"+
                "Title: " + title + " \n" +
                "Authors:\n " + authors + " \n" +
                "Languages:\n " + languages + " \n" +
                "Download count:\n " + downloadCount + " \n" +
                "********************************************";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }
}
