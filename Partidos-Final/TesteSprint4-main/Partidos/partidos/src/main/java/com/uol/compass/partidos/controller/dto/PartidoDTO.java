package com.uol.compass.partidos.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uol.compass.partidos.modelo.Ideologia;
import com.uol.compass.partidos.modelo.Partido;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class PartidoDTO {

	private Long idPartido;
	private String nomeDoPartido;
	private String sigla;
	private Ideologia ideologia;
	@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
	private LocalDate dataFundacao;

	public PartidoDTO() {
	}

	public PartidoDTO(Partido partido) {

		this.idPartido = partido.getId();
		this.nomeDoPartido = partido.getNomeDoPartido();
		this.sigla = partido.getSigla();
		this.ideologia = partido.getIdeologia();
		this.dataFundacao = partido.getDataFundacao();

	}

	public Long getIdPartido() {
		return idPartido;
	}

	public String getNomeDoPartido() {
		return nomeDoPartido;
	}

	public String getSigla() {
		return sigla;
	}

	public Ideologia getIdeologia() {
		return ideologia;
	}

	public LocalDate getDataFundacao() {
		return dataFundacao;
	}

	public static List<PartidoDTO> converter(List<Partido> partido) {
		return partido.stream().map(PartidoDTO::new).collect(Collectors.toList());

	}

}
