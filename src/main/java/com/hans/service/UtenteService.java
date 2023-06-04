package com.hans.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.hans.Enums.TipoPostazione;
import com.hans.model.Postazione;
import com.hans.model.Utente;
import com.hans.repository.PostazioneRepository;
import com.hans.repository.UtenteRepository;


@Service
public class UtenteService {

	@Autowired UtenteRepository db;
	@Autowired PostazioneRepository db2;
	
	
	
	@Autowired @Qualifier("FakeUtente") ObjectProvider<Utente> fakeUtenteProvider;
	
	public Utente creaUtente() {
		return fakeUtenteProvider.getObject();			
	}
	
	public void salvaOModificaUtente(Utente u) {
		db.save(u);
		System.out.println("L'utente: "+u.getNome()+" "+u.getCognome()+" è stato salvato nel DB!");
	}
	
	public Utente cercaUtente(long id) {
		return db.findById(id).get();
	}
	
	public List<Postazione> cercaPostazionePerCittaTipo(String s, TipoPostazione tipo){
		return db2.cercaPostazionePerTipoCitta(s, tipo);
	}
}
