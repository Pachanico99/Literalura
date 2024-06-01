package com.alura.challenge.Literalura;

import com.alura.challenge.Literalura.controller.AuthorController;
import com.alura.challenge.Literalura.controller.BookController;
import com.alura.challenge.Literalura.controller.LanguageController;
import com.alura.challenge.Literalura.main.Main;
import com.alura.challenge.Literalura.repository.AuthorRepository;
import com.alura.challenge.Literalura.repository.BookRepository;
import com.alura.challenge.Literalura.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private BookController bookController;

	@Autowired
	private AuthorController authorController;

	@Autowired
	private LanguageController languageController;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main m = new Main(bookController, authorController, languageController);
		m.showMenu();
		System.out.println("Bye Bye");
	}
}
