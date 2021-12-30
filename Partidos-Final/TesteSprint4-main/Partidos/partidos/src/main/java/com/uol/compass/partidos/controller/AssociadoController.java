package com.uol.compass.partidos.controller;

import com.uol.compass.partidos.controller.dto.AssociadoDTO;
import com.uol.compass.partidos.form.AssociadoForm;
import com.uol.compass.partidos.form.VinculoForm;
import com.uol.compass.partidos.modelo.Associado;
import com.uol.compass.partidos.modelo.CargoPolitico;
import com.uol.compass.partidos.repository.AssociadoRepository;
import com.uol.compass.partidos.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/associados")
public class AssociadoController {

	@Autowired
	private AssociadoRepository associadoRepository;

	@Autowired
	private PartidoRepository partidoRepository;

	// POST - /associados
	@PostMapping
	public ResponseEntity<AssociadoDTO> cadastrar(@RequestBody @Valid AssociadoForm form,
			UriComponentsBuilder uriBuilder) {
		Associado associado = form.converter(associadoRepository);
		associadoRepository.save(associado);

		URI uri = uriBuilder.path("/associados{id}").buildAndExpand(associado.getIdAssociado()).toUri();
		return ResponseEntity.created(uri).body(new AssociadoDTO(associado));
	}

	// POST - /associados/partidos (Vincula um associado a um partido, com o body:
	// {“idAssociado”: 10, “idPartido”: 10})

	@PostMapping("/partidos")
	public ResponseEntity<AssociadoDTO> vincular(@RequestBody VinculoForm form, UriComponentsBuilder uriBuilder) {

		Associado associado = form.associa(associadoRepository, partidoRepository, form);
		associadoRepository.save(associado);

		URI uri = uriBuilder.path("/associados{id}").buildAndExpand(associado.getIdAssociado()).toUri();
		return ResponseEntity.created(uri).body(new AssociadoDTO(associado));
	}

	// DELETE - /associados/{id}/partidos/{id} (Remove determinado associado daquele partido)
	@DeleteMapping("/partidos")
	@Transactional
	public ResponseEntity<?> remover(@RequestBody VinculoForm form) {

		Associado associado = form.remove(associadoRepository, partidoRepository, form);

		if (associado.getIdAssociado() != null) {
			return ResponseEntity.ok().build();
		} else
			return ResponseEntity.notFound().build();

	}

	// PUT - /associados/{id}
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<AssociadoDTO> atualiza(@PathVariable Long id, @RequestBody @Valid AssociadoForm form) {

		Optional<Associado> optional = associadoRepository.findById(id);
		if (optional.isPresent()) {
			Associado associado = form.atualiza(id, associadoRepository);
			return ResponseEntity.ok(new AssociadoDTO(associado));

		}
		return ResponseEntity.notFound().build();

	}

	// GET - /associados (Ter uma opção de filtrar associados de acordo com seu cargo político e outra de ordenar por nome)
	@GetMapping()
	public List<AssociadoDTO> pesquisaAssociado(@RequestParam(required = false) CargoPolitico cargo,
			@RequestParam(required = false) String ordena) {

		List<Associado> associados;

		if (cargo == null) {
			associados = associadoRepository.findAll();
		} else {
			associados = associadoRepository.findAllByCargoPolitico(cargo);
		}

		if (ordena != null) {

			if (ordena.equals("nome"))
				associados.sort(Comparator.comparing(Associado::getNome, Comparator.naturalOrder()));
		}

		return AssociadoDTO.converter(associados);
	}

	// GET - /associados/{id}
	@GetMapping("/{id}")
	public ResponseEntity<AssociadoDTO> detalhar(@PathVariable Long id) {

		Optional<Associado> associado = associadoRepository.findById(id);
		if (associado.isPresent()) {
			return ResponseEntity.ok(new AssociadoDTO(associado.get()));
		}
		return ResponseEntity.notFound().build();

	}

	// DELETE - /associados/{id}
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {

		Optional<Associado> optional = associadoRepository.findById(id);
		if (optional.isPresent()) {

			associadoRepository.deleteById(id);
			return ResponseEntity.ok().build();

		}
		return ResponseEntity.notFound().build();

	}

}
