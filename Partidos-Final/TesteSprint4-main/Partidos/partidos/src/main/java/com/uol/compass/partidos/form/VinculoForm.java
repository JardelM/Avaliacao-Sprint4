package com.uol.compass.partidos.form;

import com.uol.compass.partidos.modelo.Associado;
import com.uol.compass.partidos.modelo.Partido;
import com.uol.compass.partidos.repository.AssociadoRepository;
import com.uol.compass.partidos.repository.PartidoRepository;

import java.util.Optional;

public class VinculoForm {

    private Long idAssociado;
    private Long idPartido;

    public Long getIdAssociado() {
        return idAssociado;
    }

    public void setIdAssociado(Long idAssociado) {
        this.idAssociado = idAssociado;
    }

    public Long getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(Long idPartido) {
        this.idPartido = idPartido;
    }

    public Associado associa(AssociadoRepository associadoRepository, PartidoRepository partidoRepository,
                             VinculoForm testeForm) {

        Long idAssociado = testeForm.getIdAssociado();
        Long idPartido = testeForm.getIdPartido();

        Optional<Associado> associado = associadoRepository.findById(idAssociado);
        Optional<Partido> partido = partidoRepository.findById(idPartido);

        Associado associadoParaVincular;

        if (associado.isPresent() && partido.isPresent()) {

            associadoParaVincular = associado.get();
            associadoParaVincular.setPartido(partido.get());
            return associadoParaVincular;
        }
        return null;

    }

    public Associado remove(AssociadoRepository associadoRepository, PartidoRepository partidoRepository,
                            VinculoForm form) {

        Long idAssociado = form.getIdAssociado();
        Long idPartido = form.getIdPartido();

        Optional<Associado> associado = associadoRepository.findById(idAssociado);

        if (associado.isPresent()) {
            Optional<Partido> partido = partidoRepository.findById(idPartido);

            if (partido.isPresent()) {
                Associado associadoParaRemover = associado.get();
                associadoParaRemover.setPartido(null);
                return associadoParaRemover;
            }

        }

        return null;
    }

}
