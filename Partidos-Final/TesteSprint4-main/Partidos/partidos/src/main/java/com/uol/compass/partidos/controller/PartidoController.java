package com.uol.compass.partidos.controller;

import com.uol.compass.partidos.controller.dto.AssociadoDTO;
import com.uol.compass.partidos.controller.dto.PartidoDTO;
import com.uol.compass.partidos.form.PartidoForm;
import com.uol.compass.partidos.modelo.Associado;
import com.uol.compass.partidos.modelo.Ideologia;
import com.uol.compass.partidos.modelo.Partido;
import com.uol.compass.partidos.repository.AssociadoRepository;
import com.uol.compass.partidos.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/partidos")
public class PartidoController {

	@Autowired
	private PartidoRepository partidoRepository;

	@Autowired
	private AssociadoRepository associadoRepository;

	// POST - /partidos
	@PostMapping
	public ResponseEntity<PartidoDTO> cadastrar(@RequestBody @Valid PartidoForm form, UriComponentsBuilder uriBuilder) {
		Partido partido = form.converter();
		partidoRepository.save(partido);

		URI uri = uriBuilder.path("/partidos{id}").buildAndExpand(partido.getId()).toUri();
		return ResponseEntity.created(uri).body(new PartidoDTO(partido));
	}

	// GET - /partidos (Ter uma opção de filtrar partidos de acordo com sua ideologia)
	@GetMapping()
	public List<PartidoDTO> pesquisaIdeologia(@RequestParam(required = false) Ideologia ideologia,
			@RequestParam(required = false) String filtro) {

		List<Partido> partidos;

		if (ideologia == null) {
			partidos = partidoRepository.findAll();
		} else {
			partidos = partidoRepository.findAllByIdeologia(ideologia);
		}

		return PartidoDTO.converter(partidos);
	}

	// GET - /partidos/{id}
	@GetMapping("/{id}")
	public ResponseEntity<PartidoDTO> detalhar(@PathVariable Long id) {

		Optional<Partido> partido = partidoRepository.findById(id);
		if (partido.isPresent()) {
			return ResponseEntity.ok(new PartidoDTO(partido.get()));
		}
		return ResponseEntity.notFound().build();

	}

	// GET - /partidos/{id}/associados (Listar todos os associados daquele partido)
	@GetMapping("{id}/associados")
	public List<AssociadoDTO> pesquisaAssociado(@PathVariable Long id) {

		List<Associado> associados;

		if (id == null) {
			associados = associadoRepository.findAll();
		} else {
			associados = associadoRepository.findAllByPartidoId(id);
		}

		return AssociadoDTO.converter(associados);
	}

	// DELETE - /partidos/{id}
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {

		Optional<Partido> optional = partidoRepository.findById(id);
		if (optional.isPresent()) {
			partidoRepository.deleteById(id);
			return ResponseEntity.ok().build();

		}
		return ResponseEntity.notFound().build();

	}

	// PUT - /partidos/{id}
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PartidoDTO> atualiza(@PathVariable Long id, @RequestBody @Valid PartidoForm form) {

		Optional<Partido> optional = partidoRepository.findById(id);
		if (optional.isPresent()) {
			Partido partido = form.atualiza(id, partidoRepository);
			return ResponseEntity.ok(new PartidoDTO(partido));

		}
		return ResponseEntity.notFound().build();

	}

}
