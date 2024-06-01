package com.alura.challenge.Literalura.service;

import com.alura.challenge.Literalura.model.Language;
import com.alura.challenge.Literalura.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    @Transactional
    public void saveIfNotExist(Language language){
        Optional<Language> authorInDB = languageRepository.getLanguageByLanguageCode(language.getLanguageCode());
        if (!authorInDB.isPresent()){
            languageRepository.save(language);
        }else{
            System.out.println("The language [ " + language.getLanguageCode() + " ] already exists in DB");
        }
    }

    public Language getLanguageByLanguageCode(String languageCode){
        Optional<Language> languageInDB = languageRepository.getLanguageByLanguageCode(languageCode);
        return languageInDB.orElse(null);
    }

    public List<Language> getAllLanguages(){
        return languageRepository.getAllLanguages();
    }

}
