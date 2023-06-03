package com.hans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hans.model.PrenotazionePostazione;

@Repository
public interface PrenotazionePostazioneRepository extends JpaRepository<PrenotazionePostazione, Long>{

}
