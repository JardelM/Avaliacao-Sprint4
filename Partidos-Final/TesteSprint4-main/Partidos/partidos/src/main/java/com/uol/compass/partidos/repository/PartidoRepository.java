package com.uol.compass.partidos.repository;

import com.uol.compass.partidos.modelo.Ideologia;
import com.uol.compass.partidos.modelo.Partido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PartidoRepository extends JpaRepository<Partido, Long> {

	List<Partido> findAllByIdeologia(Ideologia ideologia);
	
	Optional<Partido> findById(Long idPartido);

	List<Partido> findByNomeDoPartido(String nomeDoPartido);
	
	
}
