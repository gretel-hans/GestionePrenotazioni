package com.hans.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
	public void salvaOModficaPrenotazionePostazione(PrenotazionePostazione p) {
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
		}else if(giaPrenotato==true) {
			System.out.println("ERRORE! "+p.getUtente().getNome()+" non puoi prenotare alla data "+p.getDataPrenotazione()+ " hai già una prenotazione!");			
		}
	}
	
}
