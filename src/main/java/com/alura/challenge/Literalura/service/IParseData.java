package com.alura.challenge.Literalura.service;

public interface IParseData {
    <T> T getData(String json, Class<T> classObject);
}
