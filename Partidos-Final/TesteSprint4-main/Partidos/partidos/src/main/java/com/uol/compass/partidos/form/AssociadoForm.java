package com.uol.compass.partidos.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uol.compass.partidos.modelo.Associado;
import com.uol.compass.partidos.modelo.CargoPolitico;
import com.uol.compass.partidos.modelo.Partido;
import com.uol.compass.partidos.modelo.Sexo;
import com.uol.compass.partidos.repository.AssociadoRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AssociadoForm {

    @NotBlank(message = "Este campo é obrigatório!")
    private String nome;
    @NotNull(message = "Este campo é obrigatório!")
    private CargoPolitico cargoPolitico;
    @NotNull(message = "Este campo é obrigatório!")
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataNascimento;
    @NotNull(message = "Este campo é obrigatório!")
    private Sexo sexo;
    private Partido partido;

    public AssociadoForm() {
    }

    public AssociadoForm(String nome, CargoPolitico cargoPolitico, LocalDate dataNascimento, Sexo sexo, Partido partido) {
        this.nome = nome;
        this.cargoPolitico = cargoPolitico;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.partido = partido;
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

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Associado converter(AssociadoRepository associadoRepository) {
        return new Associado(nome, cargoPolitico, dataNascimento, sexo, partido);
    }

    public Associado atualiza(Long id, AssociadoRepository associadoRepository) {
        Associado associado = associadoRepository.getById(id);

        associado.setNome(this.nome);
        associado.setCargoPolitico(this.cargoPolitico);
        associado.setDataNascimento(this.dataNascimento);
        associado.setSexo(this.sexo);

        return associado;

    }
}
