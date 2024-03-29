package com.colegio.perfil.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/PerfilEstudiante-micro")
public class EstudianteController {
	@Autowired
	private EstudianteService estudianteService;
	
	@GetMapping("Perfil/{id}")
	public ResponseEntity<Estudiante> obtenerEstudiante(@PathVariable("id") int id) {
		Estudiante estudiante = estudianteService.getEstudianteById(id);
		if (estudiante == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(estudiante);
	}
	
	@PostMapping("Perfil")
	public ResponseEntity<Estudiante> guardarEstudiante(@RequestBody Estudiante estudiante) {
		Estudiante nuevoEstudiante = estudianteService.save(estudiante);
		return new ResponseEntity<Estudiante>(nuevoEstudiante, HttpStatus.CREATED);

	}
	
}
