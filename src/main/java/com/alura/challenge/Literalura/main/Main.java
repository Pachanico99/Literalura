package com.alura.challenge.Literalura.main;

import com.alura.challenge.Literalura.controller.BookController;
import com.alura.challenge.Literalura.model.Book;
import com.alura.challenge.Literalura.model.RBookList;
import com.alura.challenge.Literalura.repository.AuthorRepository;
import com.alura.challenge.Literalura.repository.BookRepository;
import com.alura.challenge.Literalura.repository.LanguageRepository;
import com.alura.challenge.Literalura.service.ConsumeApi;
import com.alura.challenge.Literalura.service.ParseData;

import java.util.Scanner;

public class Main {
    private final Scanner keyboardInput = new Scanner(System.in);
    private final ConsumeApi consumeApi = new ConsumeApi();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private final String CONNECTOR_SEARCH = "%20";
    private final ParseData parser = new ParseData();
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private LanguageRepository languageRepository;

    public Main(BookRepository repository, AuthorRepository authorRepository, LanguageRepository languageRepository) {
        this.bookRepository = repository;
        this.authorRepository = authorRepository;
        this.languageRepository = languageRepository;
    }

    public void showMenu(){
        int opcion = -1;
        while(opcion != 0){
            System.out.println("""
                    Enter one of the following options:
                        -1 Find books by title
                        -2 List registered books
                        -3 List registered authors
                        -4 List authors alive in a given year
                        -5 List books by language
                        -6 List books by language
                        -0 Exit
                    """);
            opcion = keyboardInput.nextInt();
            keyboardInput.nextLine();
            switch (opcion){
                case 1:
                    Book book = getBookByTitle();
                    if(book != null){
                        System.out.println(book);
                    }else{
                        System.out.println("Book not found");
                    }
                    break;
                case 2:
                    bookRepository.getAllBooks().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("List registered authors");
                    break;
                case 4:
                    System.out.println("List authors alive in a given year");
                    break;
                case 5:
                    System.out.println("List books by language");
                    break;
                case 6:
                    System.out.println("List books by language");
                    break;
                case 0:
                    System.out.println("Thanks for using literalura: ");
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }

        }
    }

    public Book getBookByTitle(){
        System.out.println("Enter the title of the book: ");
        String title = keyboardInput.nextLine();
        String url = URL_BASE + CONNECTOR_SEARCH + title.toLowerCase().replace(" ", "+");
        String json = consumeApi.getData(url);
        var searchedBook = parser.getData(json, RBookList.class).books();
        if (!searchedBook.isEmpty()){
            Book book = new Book(searchedBook.get(0));
            System.out.println(book);
            book.getAuthors().forEach(a -> authorRepository.saveIfNotExist(a));
            book.getLanguages().forEach(l -> languageRepository.saveIfNotExist(l));
            bookRepository.saveIfNotExist(book);
            return book;
        }
        return null;
    }
}
