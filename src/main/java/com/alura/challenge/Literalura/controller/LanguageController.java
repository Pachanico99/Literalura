package com.alura.challenge.Literalura.controller;

import com.alura.challenge.Literalura.model.Language;
import com.alura.challenge.Literalura.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    public void saveIfNotExist(Language language){
        languageService.saveIfNotExist(language);
    }

    public Language getLanguageByLanguageCode(String languageCode){
        return languageService.getLanguageByLanguageCode(languageCode);
    }

    public List<Language> getAllLanguages(){
        return languageService.getAllLanguages();
    }

}
