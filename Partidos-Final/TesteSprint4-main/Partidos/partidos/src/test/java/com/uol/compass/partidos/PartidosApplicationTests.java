package com.uol.compass.partidos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.text.ParseException;
import java.time.LocalDate;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.uol.compass.partidos.modelo.Associado;
import com.uol.compass.partidos.modelo.CargoPolitico;
import com.uol.compass.partidos.modelo.Ideologia;
import com.uol.compass.partidos.modelo.Partido;
import com.uol.compass.partidos.modelo.Sexo;
import com.uol.compass.partidos.repository.AssociadoRepository;
import com.uol.compass.partidos.repository.PartidoRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class PartidosApplicationTests {

	@Autowired
	private AssociadoRepository associadoRepository;

	@Autowired
	private PartidoRepository partidoRepository;

	@Autowired
	private TestEntityManager em;

	@Test
	public void deveriaCadastrarUmAssociado() {

		Associado associado = new Associado();
		Partido partido = new Partido();

		associado.setNome("Fulano");
		associado.setCargoPolitico(CargoPolitico.Presidente);
		associado.setPartido(partido);
		LocalDate dt2 = LocalDate.of(1990, 3, 15);
		associado.setDataNascimento(dt2);
		associado.setSexo(Sexo.MASCULINO);
		em.persist(associado);

		Associado associado2 = associadoRepository.findByNome(associado.getNome());
		assertNotNull(associado2);
		assertEquals(associado.getNome(), associado2.getNome());
		assertEquals(associado.getCargoPolitico(), associado2.getCargoPolitico());
		assertEquals(associado.getDataNascimento(), associado2.getDataNascimento());
		assertEquals(associado.getSexo(), associado2.getSexo());
	}

	@Test
	public void deveriaApagarUmAssociadoAPartirDoNome() {
		Associado associado = new Associado();
		Partido partido = new Partido();

		associado.setNome("Fulano");
		associado.setCargoPolitico(CargoPolitico.Nenhum);

		LocalDate dt2 = LocalDate.of(1990, 3, 15);
		associado.setDataNascimento(dt2);
		associado.setPartido(partido);
		associado.setSexo(Sexo.MASCULINO);

		em.persist(associado);
		associadoRepository.deleteByNome(associado.getNome());
		assertNull(associadoRepository.findByNome(associado.getNome()));
	}



	@Test
	public void deveriaRecuperarUmAssociadoPeloNome() throws ParseException {
		String nomeAssociado = "Luiz Inacio";
		Associado associado = new Associado();
		Partido partido = new Partido();

		associado.setNome(nomeAssociado);
		associado.setCargoPolitico(CargoPolitico.Presidente);

		LocalDate dt2 = LocalDate.of(1990, 3, 15);
		partido.setDataFundacao(dt2);

		associado.setDataNascimento(dt2);
		associado.setSexo(Sexo.MASCULINO);
		associado.setPartido(partido);
		em.persist(associado);

		Associado associado2 = associadoRepository.findByNome(nomeAssociado);
		Assertions.assertNotNull(associado2);
		Assertions.assertEquals(associado2.getNome(), nomeAssociado);
	}

}
