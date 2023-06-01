package com.hans.service;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hans.Enums.TipoPostazione;
import com.hans.model.Edificio;
import com.hans.model.Postazione;
import com.hans.repository.PostazioneRepository;

@Service
public class PostazioneService {

	@Autowired PostazioneRepository db;
	
	@Autowired @Qualifier("NuovaPostazione") ObjectProvider<Postazione> nuovaPostazioneProvider;
	
	public Postazione creaPostazione() {
		return nuovaPostazioneProvider.getObject();
	}
	
	public void salvaOModificaUtentePostazione(Postazione p) {
		db.save(p);
		System.out.println("Postazione con id: "+p.getId()+" salvato nel DB!");
	}
}
