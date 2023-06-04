package com.hans.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="prenotazioni")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class PrenotazionePostazione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	
	private Utente utente;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	
	private Postazione postazione;
	
	@Column(nullable = false, name="data_prenotazione")
	private LocalDate dataPrenotazione;

	public PrenotazionePostazione(Utente utente, Postazione postazione, LocalDate dataPrenotazione) {
		this.utente = utente;
		this.postazione = postazione;
		this.dataPrenotazione = dataPrenotazione;
	}
	
	
	
}
