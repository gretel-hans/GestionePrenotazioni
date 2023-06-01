package com.hans.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hans.model.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente,Long>{

	public Utente findByNome(String nome);
	
}
