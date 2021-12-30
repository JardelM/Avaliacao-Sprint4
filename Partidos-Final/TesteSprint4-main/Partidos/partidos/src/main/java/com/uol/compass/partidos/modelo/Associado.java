package com.uol.compass.partidos.modelo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Associado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAssociado;
	private String nome;
	@Enumerated(EnumType.STRING)
	private CargoPolitico cargoPolitico;
	private LocalDate dataNascimento;
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	@ManyToOne
	@JoinColumn
	private Partido partido;

	public Associado() {

	}

	public Associado(Long idAssociado, String nome, CargoPolitico cargoPolitico, LocalDate dataNascimento, Sexo sexo, Partido partido) {
		this.idAssociado = idAssociado;
		this.nome = nome;
		this.cargoPolitico = cargoPolitico;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.partido = partido;
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

	public void setIdAssociado(Long idAssociado) {
		this.idAssociado = idAssociado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public CargoPolitico getCargoPolitico() {
		return cargoPolitico;
	}

	public void setCargoPolitico(CargoPolitico cargoPolitico) {
		this.cargoPolitico = cargoPolitico;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Associado(String nome, CargoPolitico cargoPolitico, LocalDate dataNascimento, Sexo sexo, Partido partido) {
		this.nome = nome;
		this.cargoPolitico = cargoPolitico;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.partido = partido;
	}

}
