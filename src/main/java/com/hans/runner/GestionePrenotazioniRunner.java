package com.hans.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.hans.model.Utente;
import com.hans.service.UtenteService;

@Component
public class GestionePrenotazioniRunner implements ApplicationRunner {

	@Autowired private UtenteService utenteService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Runner in esecuzione...");
		
		for(int i=0;i<20;i++) {
			Utente u=utenteService.creaOModificaUtente();
			utenteService.inserisciUtente(u);
		}

		
		
	}

	

}
