package com.hans.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.hans.Enums.TipoPostazione;
import com.hans.model.Edificio;
import com.hans.model.Postazione;
import com.hans.model.Utente;
import com.hans.repository.EdificioRepository;
import com.hans.service.EdificioService;
import com.hans.service.PostazioneService;
import com.hans.service.UtenteService;

@Component
public class GestionePrenotazioniRunner implements ApplicationRunner {

	@Autowired private UtenteService utenteService;
	@Autowired private EdificioService edificioService;
	@Autowired private EdificioRepository edificioRepository;
	@Autowired private PostazioneService postazioneService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Runner in esecuzione...");
		
		//CREAZIONE UTENTI
		for(int i=0;i<20;i++) {
			Utente u=utenteService.creaUtente();
			utenteService.salvaOModificaUtente(u);
		}

		//CREAZIONE EDIFICI
		for(int i=0;i<20;i++) {
			Edificio e=edificioService.creaEdificio();
			edificioService.salvaOModificaEdificio(e);
		}
		
		//CREAZIONE POSTAZIONE 
		
		Postazione p=postazioneService.creaPostazione();
		p.setDescrizione("ciaoo");
		p.setTipoPostazione(TipoPostazione.Sala_Riunioni);
		p.setEdificio(edificioRepository.findById(4l).get());
		postazioneService.salvaOModificaUtentePostazione(p);
	}

	

}
