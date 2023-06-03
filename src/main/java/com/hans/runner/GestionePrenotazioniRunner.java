package com.hans.runner;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.hans.Enums.TipoPostazione;
import com.hans.model.Edificio;
import com.hans.model.Postazione;
import com.hans.model.PrenotazionePostazione;
import com.hans.model.Utente;
import com.hans.repository.EdificioRepository;
import com.hans.repository.PostazioneRepository;
import com.hans.repository.PrenotazionePostazioneRepository;
import com.hans.repository.UtenteRepository;
import com.hans.service.EdificioService;
import com.hans.service.PostazioneService;
import com.hans.service.PrenotazionePostazioneService;
import com.hans.service.UtenteService;

@Component
public class GestionePrenotazioniRunner implements ApplicationRunner {

	@Autowired private UtenteService utenteService;
	@Autowired private UtenteRepository utenteRepository;
	
	@Autowired private EdificioService edificioService;
	@Autowired private EdificioRepository edificioRepository;
	
	@Autowired private PostazioneService postazioneService;
	@Autowired private PostazioneRepository postazioneRepository;
	
	@Autowired private PrenotazionePostazioneService prenotazioneService;
	@Autowired private PrenotazionePostazioneRepository prenotazionePr;

	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		//CREAZIONE UTENTI
		for(int i=0;i<20;i++) {
			Utente u=utenteService.creaUtente();
			//utenteService.salvaOModificaUtente(u);
		}
		//CREAZIONE EDIFICI
		for(int i=0;i<20;i++) {
			Edificio e=edificioService.creaEdificio();
			//edificioService.salvaOModificaEdificio(e);
		}
		
		//CREAZIONE POSTAZIONE 
		
		String[] listaDescrizioni= {"Postazione Minimalista: Una scrivania moderna con linee pulite e minimali"
				,"Postazione Creativa: Una scrivania colorata e vivace con un sacco di elementi stimolanti"
				,"Postazione Tecnologica: Una scrivania high-tech con un monitor ultrawide curvo"
				,"Postazione Organizzata: Una scrivania impeccabilmente ordinata",
				"Postazione Verde: Una scrivania circondata da piante d'appartamento",
				"Postazione Collaborativa: Una grande scrivania rettangolare che può ospitare più persone",
				"Postazione Accogliente: Una scrivania con una sedia ergonomica super confortevole",
				"Postazione Moderna: Una scrivania con superficie di vetro temperato e gambe in metallo lucido",
				"Postazione High-Tech: Una scrivania dotata di un sistema di realtà virtuale",
				"Postazione Motivante: Una scrivania con una bacheca degli obiettivi"};
		
		TipoPostazione[] sceltaTipo= {TipoPostazione.Openspace,TipoPostazione.Sala_Riunioni,TipoPostazione.Privato};
		for (int i=1;i<15;i++) {
			long idEdificio= (long)(Math.random()*20)+1;
			int tipoPostazione=(int)(Math.random()*3);
			int descrizionePostazione=(int)(Math.random()*9);
			int numeroMaxOccupanti=(int)(Math.random()*60)+10;
			
			Postazione p=new Postazione(listaDescrizioni[descrizionePostazione],numeroMaxOccupanti,sceltaTipo[tipoPostazione],edificioRepository.findById(idEdificio).get());
			//postazioneService.salvaOModificaPostazione(p);
		}
		
		
		//RICERCA DI UNA DETERMINATA POSTAZIONE IN BASE ALLA CITTA 
		//E IL TIPO DI POSTAZIONE INSERITO
		
		//List<Postazione> listaP=postazioneService.getDb().cercaPostazionePerTipoCitta("Milano",TipoPostazione.Privato);
		//listaP.forEach(p->System.out.println("Postazione situata a: "+p.getEdificio().getCitta()+" [ "+p.getDescrizione()+", numero massimo persone: "+p.getNumeroMassimoOccupanti()+", tipo: "+p.getTipoPostazione()+" ]"));
		
		List<Postazione> listaP2=utenteRepository.cercaPostazionePerTipoCitta("Milano", TipoPostazione.Privato);
		//listaP2.forEach(p->System.out.println("Postazione situata a: "+p.getEdificio().getCitta()+" [ "+p.getDescrizione()+", numero massimo persone: "+p.getNumeroMassimoOccupanti()+", tipo: "+p.getTipoPostazione()+" ]"));
	
	
		
		

	//PRENOTAZIONE POSTAZIONI 	
	
	for(int i=0;i<10;i++) {
		long start=LocalDate.of(2020, 01, 01).toEpochDay();
		long end=LocalDate.now().toEpochDay();
		long random = ThreadLocalRandom
				.current()
				.nextLong(start, end);
		long idUtente= (long)(Math.random()*20)+1;
		long idPostazione= (long)(Math.random()*15)+1;
		
		PrenotazionePostazione p=new PrenotazionePostazione(utenteRepository.findById(idUtente).get(), postazioneRepository.findById(idPostazione).get(), LocalDate.ofEpochDay(random));
		//prenotazioneService.salvaOModficaPrenotazionePostazione(p);
	}
    
	//PROVA PRENOTAZIONE STESSO UTENTE STESSA DATA E POSTAZIONE DIVERSA 
	PrenotazionePostazione p2=new PrenotazionePostazione(utenteRepository.findById(1l).get(), postazioneRepository.findById(4l).get(), LocalDate.of(2023,06,10));
	//prenotazioneService.salvaOModficaPrenotazionePostazione(p2);
	
	
    //CAMBIO DATA PRENOTAZIONE DELL'UTENTE SOPRA
	PrenotazionePostazione p3=prenotazionePr.findById(12l).get();
	p3.setDataPrenotazione(LocalDate.of(2023, 06, 13));
	//prenotazioneService.salvaOModficaPrenotazionePostazione(p3);
	
	
	//PRENOTAZIONE CON LO STESSO UTENTE LA DATA PRIMA DELLA MODIFICA 
	//PER VEDERE SE MI PERMETTE DI PRENOTARE
	
	PrenotazionePostazione preno=new PrenotazionePostazione(utenteRepository.findById(1l).get(), postazioneRepository.findById(1l).get(), LocalDate.of(2023,06,10));
	//prenotazioneService.salvaOModficaPrenotazionePostazione(preno);
	
	
	
	

	
	
	}
}
