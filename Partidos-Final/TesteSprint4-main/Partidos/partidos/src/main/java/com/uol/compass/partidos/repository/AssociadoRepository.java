package com.uol.compass.partidos.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.uol.compass.partidos.modelo.Associado;
import com.uol.compass.partidos.modelo.CargoPolitico;


public interface AssociadoRepository extends JpaRepository<Associado, Long> {

	List<Associado> findAllByCargoPolitico(CargoPolitico cargo);

	List<Associado> findAllByPartidoId(Long id);

	Associado findByNome(String nomeAssociado);

	List<Associado> findByCargoPolitico(CargoPolitico prefeito);

	List<Associado> deleteByNome(String nome);

	
}
