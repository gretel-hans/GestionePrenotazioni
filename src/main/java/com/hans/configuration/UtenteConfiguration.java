package com.hans.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.hans.model.Utente;

@Configuration
public class UtenteConfiguration {

	@Bean
	@Scope("prototype")
	public Utente FakeUtente() {
		Faker fake= Faker.instance(new Locale("it-IT"));
		Utente fakeUtente=new Utente();
		fakeUtente.setNome(fake.name().firstName());
		fakeUtente.setCognome(fake.name().lastName());
		fakeUtente.setUsername(fakeUtente.getNome().toLowerCase()+"."+fakeUtente.getCognome().toLowerCase());
		fakeUtente.setEmail(fakeUtente.getUsername()+"@icloud.com");
		return fakeUtente;
	}
}
