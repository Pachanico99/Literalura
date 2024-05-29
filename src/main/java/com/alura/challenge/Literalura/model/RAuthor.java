package com.alura.challenge.Literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record RAuthor(
        @JsonAlias("name") String name,
        @JsonAlias("birth_year") Integer bornYear,
        @JsonAlias("death_year") Integer deathYear
) {
}
