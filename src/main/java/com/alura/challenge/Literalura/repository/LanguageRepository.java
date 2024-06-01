package com.alura.challenge.Literalura.repository;

import com.alura.challenge.Literalura.model.Language;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language,String> {

    @Query("SELECT l FROM Language l WHERE l.languageCode = :languageCode")
    Optional<Language> getLanguageByLanguageCode(String languageCode);

    @Query("SELECT l FROM Language l")
    List<Language> getAllLanguages();

}
