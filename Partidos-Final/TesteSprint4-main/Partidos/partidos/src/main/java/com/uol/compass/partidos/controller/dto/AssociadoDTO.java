package com.uol.compass.partidos.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uol.compass.partidos.modelo.Associado;
import com.uol.compass.partidos.modelo.CargoPolitico;
import com.uol.compass.partidos.modelo.Partido;
import com.uol.compass.partidos.modelo.Sexo;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AssociadoDTO {

	private Long idAssociado;
	private String nome;
	private CargoPolitico cargoPolitico;
	@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
	private LocalDate dataNascimento;
	private Sexo sexo;
	private Partido partido;

	public AssociadoDTO() {
	}

	public AssociadoDTO(Associado associado) {
		this.idAssociado = associado.getIdAssociado();
		this.nome = associado.getNome();
		this.cargoPolitico = associado.getCargoPolitico();
		this.dataNascimento = associado.getDataNascimento();
		this.sexo = associado.getSexo();
		this.partido = associado.getPartido();
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public Long getIdAssociado() {
		return idAssociado;
	}

	public String getNome() {
		return nome;
	}

	public CargoPolitico getCargoPolitico() {
		return cargoPolitico;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public static List<AssociadoDTO> converter(List<Associado> associado) {
		return associado.stream().map(AssociadoDTO::new).collect(Collectors.toList());
	}

}
