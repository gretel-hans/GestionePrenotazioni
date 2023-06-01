package com.hans.service;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hans.model.Utente;
import com.hans.repository.UtenteRepository;

@Service
public class UtenteService {

	@Autowired UtenteRepository db;
	
	@Autowired @Qualifier("FakeUtente") ObjectProvider<Utente> fakeUtenteProvider;
	
	public Utente creaOModificaUtente() {
		Utente u=fakeUtenteProvider.getObject();
		return u;			
	}
	
	public void inserisciUtente(Utente u) {
		db.save(u);
		System.out.println("L'utente: "+u.getNome()+" "+u.getCognome()+" è stato salvato nel DB!");
	}
}
