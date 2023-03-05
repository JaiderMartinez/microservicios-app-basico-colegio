package com.colegio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colegio.entity.Materia;
import com.colegio.service.IMateriaService;

@RestController
@RequestMapping("/materias")
public class MateriaControllers {

	@Autowired
	private IMateriaService materiaService;
	
	@GetMapping
	public ResponseEntity<List<Materia>> listaMaterias() {
		List<Materia> materias = materiaService.getAll();
		if (materias.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(materias);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Materia> obtenerMateria(@PathVariable("id") Long id) {
		Materia materia = materiaService.getMateriaById(id);
		if (materia == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(materia);
	}
	
	@PostMapping
	public ResponseEntity<Materia> guardarMaterias(@RequestBody Materia materia) {
		Materia nuevaMateria = materiaService.save(materia);
		return ResponseEntity.ok(nuevaMateria);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Materia>> listarMateriasPorUsuarioId(@PathVariable("usuarioId") int id) {
		List<Materia> materias = materiaService.byUsuarioId(id);
		if (materias.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(materias);
	}
}
