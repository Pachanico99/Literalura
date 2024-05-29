package com.alura.challenge.Literalura.repository;

import com.alura.challenge.Literalura.model.Author;
import com.alura.challenge.Literalura.model.Book;
import com.alura.challenge.Literalura.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language,String> {

    @Query("SELECT l FROM Language l WHERE l.languageCode = :languageCode")
    Language getLanguageByLanguageCode(String languageCode);

    default void saveIfNotExist(Language language){
        Optional<Language> authorInDB = Optional.ofNullable(getLanguageByLanguageCode(language.getLanguageCode()));
        if (!authorInDB.isPresent()){
            save(language);
        }else{
            System.out.println("The language [ " + language.getLanguageCode() + " ] already exists in DB");
        }
    }
}
