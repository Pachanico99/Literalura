package com.alura.challenge.Literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lenguage")
public class Language {
    @Id
    @Column(name = "lenguage_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToMany
    private String lenguajeCode;
}
