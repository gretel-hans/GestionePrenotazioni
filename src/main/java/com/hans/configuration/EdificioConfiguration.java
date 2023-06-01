package com.hans.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.github.javafaker.Faker;
import com.hans.model.Edificio;

@Configuration
public class EdificioConfiguration {

	@Bean
	@Scope("prototype")
	public Edificio FakeEdificio() {
		Faker fake=Faker.instance(new Locale("it-IT"));
		Edificio fakeEdificio =new Edificio();
		fakeEdificio.setNome(fake.company().name());
		fakeEdificio.setIndirizzo(fake.address().fullAddress());
		fakeEdificio.setCitta(fake.address().city());
		return fakeEdificio;
	}
}
