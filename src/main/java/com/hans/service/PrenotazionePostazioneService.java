package com.hans.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hans.model.Edificio;
import com.hans.model.PrenotazionePostazione;
import com.hans.repository.PrenotazionePostazioneRepository;

@Service
public class PrenotazionePostazioneService {
 
	@Autowired PrenotazionePostazioneRepository db;
	@Autowired @Qualifier("NuovaPrenotazione") ObjectProvider<PrenotazionePostazione> nuovaPrenotazione;
	
	public PrenotazionePostazione creaPrenotazione() {
		return new PrenotazionePostazione();
	}
	
	
	public boolean giaPrenotato;
	public boolean salvaOModficaPrenotazionePostazione(PrenotazionePostazione p) {
		List<PrenotazionePostazione> listaPrenotazioniCompleta=db.findAll();
		giaPrenotato=false;
		listaPrenotazioniCompleta.forEach(e->{
			if(e.getUtente().getId()==p.getUtente().getId() & (e.getDataPrenotazione().equals(p.getDataPrenotazione()))==true) {
				giaPrenotato=true;
			}
		});
		if(giaPrenotato==false) {
			db.save(p);
			System.out.println(p.getUtente().getNome()+" la tua prenotazione della postazione al: "+p.getPostazione().getEdificio().getNome()+ " è stato salvato nel dB!");			
			return true;		
		}else if(giaPrenotato==true) {
			System.out.println("ERRORE! "+p.getUtente().getNome()+" non puoi prenotare nella data: "+p.getDataPrenotazione()+ " hai già una prenotazione!");			
		return false;
		}
		return (Boolean) null;
	}
	
	public PrenotazionePostazione cercaPrenotazionePostazione(long id) {
		return db.findById(id).get();
	}
	
	public List<PrenotazionePostazione> cercaTuttePrenotazioni(){
		return db.findAll();
	}

	public void cercaNumeroPrenotazioniAziendaInData (Edificio e, LocalDate data1, LocalDate data2) {
		int n=db.cercaNumeroPrenotazioniAziendaInData(e, data1, data2);
		if(n==0) {
			System.out.println(e.getNome()+" non ha nessuna prenotazione di postazione prenotata dal "+data1+ " al "+data2);
		}else if(n>0) {
			System.out.println(e.getNome()+" ha: "+n+" prenotazioni di postazione dal "+data1+ " al "+data2);
		}
	}
}
