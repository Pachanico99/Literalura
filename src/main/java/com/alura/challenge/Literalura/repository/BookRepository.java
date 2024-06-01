package com.alura.challenge.Literalura.repository;

import com.alura.challenge.Literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {

    @Query("SELECT b FROM Book b")
    List<Book> getAllBooks();

    @Query("SELECT b FROM Book b WHERE b.title = :title")
    Optional<Book> getBookByTitle(String title);

    @Query("SELECT b FROM Book b JOIN b.languages l WHERE l.languageCode = :languageCode")
    Optional<List<Book>> getBooksByLanguage(String languageCode);

    @Query("SELECT b FROM Book b WHERE b.downloadCount = (SELECT MAX(bMAX.downloadCount) FROM Book bMAX)")
    Optional<Book> getBookMoreDownloaded();

    @Query("SELECT b FROM Book b GROUP BY b ORDER BY MAX(b.downloadCount) DESC LIMIT 10")
    List<Book> getTop10BooksDownloaded();

    @Query("SELECT b FROM Book b JOIN b.authors a WHERE LOWER(a.name) ILIKE LOWER(CONCAT('%',:authorName, '%'))")
    Optional<List<Book>> getBooksByAuthorName(String authorName);

    @Query("SELECT ROUND((COUNT(b) * 100.0) / (SELECT COUNT(b2) FROM Book b2), 2) FROM Book b JOIN b.languages l WHERE l.languageCode = :languageCode")
    Double percentageBooksByLanguage(String languageCode);

    @Query("SELECT ROUND((COUNT(b) * 100.0) / (SELECT COUNT(b2) FROM Book b2), 2) FROM Book b JOIN b.authors ath WHERE ath.name = :name")
    Double percentageBooksByAuthorName(String name);

    @Query("SELECT atnName FROM (SELECT SUM(b.downloadCount) qDownload, ath.name atnName FROM Book b JOIN b.authors ath GROUP BY ath.name)" +
            " ORDER BY qDownload DESC LIMIT 1")
    String getAuthorWithMoreDownload();

    @Query("SELECT qDownload FROM (SELECT SUM(b.downloadCount) qDownload, ath.name atnName FROM Book b JOIN b.authors ath GROUP BY ath.name)" +
            " ORDER BY qDownload DESC LIMIT 1")
    Integer getMaxDownloadByAuthor();
}