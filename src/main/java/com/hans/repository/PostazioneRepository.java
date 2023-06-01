package com.hans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hans.model.Postazione;

@Repository
public interface PostazioneRepository extends JpaRepository<Postazione, Long>{

}
