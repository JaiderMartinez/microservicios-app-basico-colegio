package com.colegio.perfil.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colegio.perfil.entity.Estudiante;
import com.colegio.perfil.service.EstudianteService;

@RestController
@RequestMapping("/perfilDelEstudiante")
public class EstudianteController {
	@Autowired
	private EstudianteService estudianteService;
	
	@GetMapping
	public ResponseEntity<List<Estudiante>> listaMaterias() {
		List<Estudiante> materias = estudianteService.getAll();
		if (materias.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(materias);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Estudiante> obtenerEstudiante(@PathVariable("id") int id) {
		Estudiante estudiante = estudianteService.getEstudianteById(id);
		if (estudiante == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(estudiante);
	}
	
	@PostMapping
	public ResponseEntity<Estudiante> guardarEstudiante(@RequestBody Estudiante estudiante) {
		Estudiante nuevoEstudiante = estudianteService.save(estudiante);
		return ResponseEntity.ok(nuevoEstudiante);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Estudiante>> listarEstudaintesPorUsuarioId(@PathVariable("usuarioId") int id) {
		List<Estudiante> estudiantes = estudianteService.byUsuarioId(id);
		if (estudiantes.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(estudiantes);
	}
}
