package com.alura.challenge.Literalura.main;

import com.alura.challenge.Literalura.controller.AuthorController;
import com.alura.challenge.Literalura.controller.BookController;
import com.alura.challenge.Literalura.controller.LanguageController;
import com.alura.challenge.Literalura.model.Book;
import com.alura.challenge.Literalura.model.RBookList;
import com.alura.challenge.Literalura.service.ConsumeApi;
import com.alura.challenge.Literalura.service.ParseData;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private final Scanner keyboardInput = new Scanner(System.in);
    private final ConsumeApi consumeApi = new ConsumeApi();
    private final String URL_BASE = "https://gutendex.com/books/";
    private final String CONNECTOR_SEARCH = "?search=";
    private final String CONNECTOR = "%20";
    private final ParseData parser = new ParseData();
    private BookController bookController;
    private AuthorController authorController;
    private LanguageController languageController;

    public Main(BookController bookController, AuthorController authorController, LanguageController languageController) {
        this.bookController = bookController;
        this.authorController = authorController;
        this.languageController = languageController;
    }

    public void showMenu(){
        int opcion = -1;
        while(opcion != 0){
            System.out.println("""
                    Enter one of the following options:
                        -1 Consult books by title
                        -2 Consult books by title and author name
                        -3 List registered books
                        -4 List registered authors
                        -5 List registered language
                        -6 List authors alive in a given year
                        -7 List books by language
                        -8 Book more downloaded
                        -9 Top 10 Books
                        -10 Find book by title
                        -11 Find book by authors
                        -12 List books by author
                        -13 consult fist 5 pages of books
                       \s
                        -0 Exit""");
            opcion = keyboardInput.nextInt();
            keyboardInput.nextLine();
            switch (opcion){
                case 1:
                    consultBookByTitle();
                    break;
                case 2:
                    consultBookByTitleAndAuthorName();
                    break;
                case 3:
                    showAllBooks();
                    break;
                case 4:
                    showAllAuthors();
                    break;
                case 5:
                    showAllLanguages();
                    break;
                case 6:
                    showAuthorsAliveByYear();
                    break;
                case 7:
                    showBooksByLanguage();
                    break;
                case 8:
                    showBookMoreDownloaded();
                    break;
                case 9:
                    showTop10BooksDownloaded();
                    break;
                case 10:
                    showBookByTitle();
                    break;
                case 11:
                    showAuthorByName();
                    break;
                case 12:
                    showBooksByAuthorName();
                    break;
                case 13:
                    System.out.println("Enter statistic menu");
                    DoubleSummaryStatistics statistics = bookController.getAllBooks().stream().collect(Collectors.summarizingDouble(Book::getDownloadCount));
                    while(opcion != 0){
                        System.out.println("""
                        Enter one of the following options:
                            -1 Percentage of books by language
                            -2 Percentage of books per author name
                            -3 Most famous author (most downloaded books)
                            -4 Average downloads per book
                            -5 Maximum number of downloads of a book
                            -6 Minimum number of downloads of a book
                            -7 Total number of downloads
                        \s
                            -0 Exit""");
                        opcion = keyboardInput.nextInt();
                        keyboardInput.nextLine();
                        menuOptions(opcion, statistics);
                    }
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

    private void menuOptions(int option, DoubleSummaryStatistics statistics) {
        switch (option){
            case 1:
                showPercentageOfBooksByLanguage();
                break;
            case 2:
                showPercentageOfBooksByAuthorName();
                break;
            case 3:
                showMaxDownloadByAuthor();
                break;
            case 4:
                System.out.println(statistics.getAverage());
                break;
            case 5:
                System.out.println(statistics.getMax());
                break;
            case 6:
                System.out.println(statistics.getMin());
                break;
            case 7:
                System.out.println(statistics.getSum());
                break;
            case 0:
                System.out.println("Exit statistic menu");
                break;
        }
    }

    private void showMaxDownloadByAuthor() {
        System.out.println("Author [ " + bookController.getAuthorWithMoreDownload() + " ] with [ " + bookController.getMaxDownloadByAuthor() +" ] download");
    }

    private void showPercentageOfBooksByAuthorName() {
        authorController.getAllAuthors().forEach(
                a -> System.out.println("Author [ " + a.getName() + " ]: " +
                        bookController.percentageBooksByAuthorName(a.getName()) + "%")
        );
    }

    private void showPercentageOfBooksByLanguage() {
        languageController.getAllLanguages().forEach(
                l -> System.out.println("Language [ " + l.getLanguageCode() + " ]: " +
                        bookController.porcentageByLanguage(l.getLanguageCode()) + "%")
        );
    }

    private void consultBookByTitleAndAuthorName() {
        System.out.println("Enter the title of the book: ");
        String title = keyboardInput.nextLine();
        System.out.println("Enter the name of the author: ");
        String authorName = keyboardInput.nextLine();
        String url = URL_BASE + CONNECTOR_SEARCH
                + authorName.toLowerCase().replace(" ", "+")
                + CONNECTOR + title.toLowerCase().replace(" ", "+");
        String json = consumeApi.getData(url);
        var searchedBook = parser.getData(json, RBookList.class).books();
        if (!searchedBook.isEmpty()){
            Book book = new Book(searchedBook.get(0));
            bookController.saveBook(book);
            System.out.println(book);
        }else{
            System.out.println("Book not found");
        }
    }

    private void showBooksByAuthorName() {
        System.out.println("Enter the name of the author: ");
        String authorName = keyboardInput.nextLine();
        bookController.getBooksByAuthorName(authorName).forEach(System.out::println);
    }

    private void showAuthorByName() {
        System.out.println("Enter the name of the author: ");
        String authorName = keyboardInput.nextLine();
        System.out.println(authorController.getAuthorByName(authorName));
    }

    private void showBookByTitle() {
        System.out.println("Enter the title of the book: ");
        String bookName = keyboardInput.nextLine();
        System.out.println(bookController.findBookByTitle(bookName));
    }

    private void showTop10BooksDownloaded() {
        bookController.getTop10BooksDownloaded().forEach(System.out::println);
    }

    private void showAllLanguages() {
        System.out.println("Registered language: ");
        languageController.getAllLanguages().forEach(System.out::println);
    }

    private void showAllAuthors() {
        System.out.println("Registered authors: ");
        authorController.getAllAuthors().forEach(System.out::println);
    }

    private void showAllBooks() {
        System.out.println("Registered books: ");
        bookController.getAllBooks().forEach(System.out::println);
    }

    private void showBookMoreDownloaded() {
        System.out.println("Book more downleaded: ");
        System.out.println(bookController.getBookMoreDownloaded());
    }

    private void showAuthorsAliveByYear() {
        System.out.println("Authors alive in a given year: ");
        int inputYearDead = keyboardInput.nextInt();
        keyboardInput.nextLine();
        authorController.getAuthorsAliveByYear(inputYearDead).forEach(System.out::println);
    }

    private void showBooksByLanguage() {
        System.out.println("Enter the language of the books: ");
        String language = keyboardInput.nextLine();
        List<Book> booksByLanguage = bookController.getBooksByLanguage(language);
        if(!booksByLanguage.isEmpty()){
            booksByLanguage.forEach(System.out::println);
        }else{
            System.out.println("No books were found with the language [ " + language + " ].");
        }
    }

    public void consultBookByTitle(){
        System.out.println("Enter the title of the book: ");
        String title = keyboardInput.nextLine();
        String url = URL_BASE + CONNECTOR_SEARCH + CONNECTOR + title.toLowerCase().replace(" ", "+");
        String json = consumeApi.getData(url);
        var searchedBook = parser.getData(json, RBookList.class).books();
        if (!searchedBook.isEmpty()){
            Book book = new Book(searchedBook.get(0));
            bookController.saveBook(book);
            System.out.println(book);
        }else{
            System.out.println("Book not found");
        }
    }

    public void addFist5PagesOfBooks(){
        String url = URL_BASE + CONNECTOR_SEARCH + CONNECTOR;
        String json = consumeApi.getData(url);
        var searchedBook = parser.getData(json, RBookList.class).books();
        var nextPage = parser.getData(json, RBookList.class).nextURL();
        int bookByPage = 32;
        int qPages = 5;
        for (int j = 0; j < qPages; j++) {
            for (int i = 0; i < bookByPage; i++) {
                if (!searchedBook.isEmpty()) {
                    Book book = new Book(searchedBook.get(i));
                    bookController.saveBook(book);
                }
            }
            json = consumeApi.getData(nextPage);
            searchedBook = parser.getData(json, RBookList.class).books();
            nextPage = parser.getData(json, RBookList.class).nextURL();
        }
    }

    public void addSpecialBook(){
        Book b = new Book(
                "Blancanieves falsa",
                authorController.getAllAuthors(),
                languageController.getAllLanguages(),
                1000000
        );
        bookController.saveBook(b);
    }
}
