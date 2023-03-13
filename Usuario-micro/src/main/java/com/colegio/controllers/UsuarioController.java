package com.colegio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colegio.entity.Usuario;
import com.colegio.modelos.Materia;
import com.colegio.modelos.PerfilEstudiante;
import com.colegio.service.IUsuarioService;

@RestController
@RequestMapping(value = "/Usuario-micro")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@PostMapping("/Usuario")
	public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
		return ResponseEntity.ok(usuarioService.guardar(usuario));
	}

	@GetMapping("/Usuario/{id}")
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id) {
		if (!usuarioService.findExistUsuario(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuarioService.encontrarUsuario(id));
	}

	@GetMapping("/Usuario/materias/{usuarioId}")
	public ResponseEntity<List<Materia>> listarMaterias(@PathVariable("usuarioId") Long id){
		if (!usuarioService.findExistUsuario((int) (long) id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuarioService.listarMaterias(id));
	}
	
	
	@GetMapping("/Usuario/perfilDelEstudiante/{usuarioId}")
	public ResponseEntity<PerfilEstudiante> obtenerPerfilDelEstudiante(@PathVariable("usuarioId") Integer id){
		if (!usuarioService.findExistUsuario(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuarioService.mostrarPerfilDelEstudiante(id));
	}
}
