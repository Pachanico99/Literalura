package com.alura.challenge.Literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParseData implements IParseData{
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> classObject) {
        try{
            return mapper.readValue(json, classObject);
        }catch (JsonProcessingException e){
            System.out.println("An error happened: [" + e.getMessage() +"]");
        }
        return null;
    }
}
