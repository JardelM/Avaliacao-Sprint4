package com.uol.compass.partidos.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Partido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeDoPartido;
	private String sigla;
	@Enumerated(EnumType.STRING)
	private Ideologia ideologia;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFundacao;

	public Partido() {

	}

	public Partido(Long id, String nomeDoPartido, String sigla, Ideologia ideologia, LocalDate dataFundacao) {
		this.id = id;
		this.nomeDoPartido = nomeDoPartido;
		this.sigla = sigla;
		this.ideologia = ideologia;
		this.dataFundacao = dataFundacao;
	}

	public Partido(String nomeDoPartido, String sigla, Ideologia ideologia, LocalDate dataFundacao) {
		this.nomeDoPartido = nomeDoPartido;
		this.sigla = sigla;
		this.ideologia = ideologia;
		this.dataFundacao = dataFundacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeDoPartido() {
		return nomeDoPartido;
	}

	public void setNomeDoPartido(String nomeDoPartido) {
		this.nomeDoPartido = nomeDoPartido;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Ideologia getIdeologia() {
		return ideologia;
	}

	public void setIdeologia(Ideologia ideologia) {
		this.ideologia = ideologia;
	}

	public LocalDate getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(LocalDate dataFundacao) {
		this.dataFundacao = dataFundacao;
	}
}
