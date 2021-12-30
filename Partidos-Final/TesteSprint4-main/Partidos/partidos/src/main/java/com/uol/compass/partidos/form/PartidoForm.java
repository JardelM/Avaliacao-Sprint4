package com.uol.compass.partidos.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uol.compass.partidos.modelo.Ideologia;
import com.uol.compass.partidos.modelo.Partido;
import com.uol.compass.partidos.repository.PartidoRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class PartidoForm {

    @NotBlank(message = "Este campo é obrigatório!")
    private String nomeDoPartido;
    @NotBlank(message = "Este campo é obrigatório!")
    private String sigla;
    @NotNull(message = "Este campo é obrigatório!")
    private Ideologia ideologia;
    @NotNull(message = "Este campo é obrigatório!")
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataFundacao;

    public PartidoForm() {

    }

    public PartidoForm(String nomeDoPartido, String sigla, Ideologia ideologia,
                       LocalDate dataFundacao) {

        this.nomeDoPartido = nomeDoPartido;
        this.sigla = sigla;
        this.ideologia = ideologia;
        this.dataFundacao = dataFundacao;
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

    public Partido converter() {
        return new Partido(this.nomeDoPartido, this.sigla, this.ideologia, this.dataFundacao);
    }

    public Partido atualiza(Long id, PartidoRepository partidoRepository) {
        Partido partido = partidoRepository.getById(id);

        partido.setNomeDoPartido(this.nomeDoPartido);
        partido.setSigla(this.sigla);
        partido.setIdeologia(this.ideologia);
        partido.setDataFundacao(this.dataFundacao);

        return partido;
    }

}
