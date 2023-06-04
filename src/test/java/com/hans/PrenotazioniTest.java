package com.hans;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hans.model.PrenotazionePostazione;
import com.hans.service.PostazioneService;
import com.hans.service.PrenotazionePostazioneService;
import com.hans.service.UtenteService;

@SpringBootTest
class PrenotazioniTest {
	
	//Per eseguire il test cambiare l'id dell'utente a riga 30 e 36
	
	@Autowired private UtenteService utenteService;
	@Autowired private PostazioneService postazioneService;
	@Autowired private PrenotazionePostazioneService prenotazioneService;
	
	public boolean giaPrenotato;
	PrenotazionePostazione p;
	
	@BeforeEach
	 void eseguiPrima() {
		p= new PrenotazionePostazione(utenteService.cercaUtente(7l), postazioneService.cercaPostazione(2l), LocalDate.of(2023, 06, 10));
		//prenotazioneService.salvaOModficaPrenotazionePostazione(p);
	}
	
	@Test
	void test() {
		PrenotazionePostazione p1= new PrenotazionePostazione(utenteService.cercaUtente(7l), postazioneService.cercaPostazione(4l), LocalDate.of(2023, 06, 10));;
		//boolean c=prenotazioneService.salvaOModficaPrenotazionePostazione(p1);
		//assertFalse(c);
	}
	
	
	
	}


