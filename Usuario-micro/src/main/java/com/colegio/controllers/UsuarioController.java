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

import com.colegio.modelo.Usuario;
import com.colegio.modelos.Materia;
import com.colegio.modelos.PerfilEstudiante;
import com.colegio.service.IUsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		return ResponseEntity.ok(usuarioService.listarUsuarios());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id) {
		Usuario usuario = usuarioService.encontrarUsuario(id);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}

	@PostMapping
	public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
		
		return ResponseEntity.ok(usuarioService.guardar(usuario));
	}

	@GetMapping("/materias/{usuarioId}")
	public ResponseEntity<List<Materia>> listarMaterias(@PathVariable("usuarioId") Long id){
		
		if(usuarioService.encontrarUsuario((int) (long)id) == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuarioService.listarMaterias(id));
	}
	
	
	@GetMapping("/perfilDelEstudiante/{usuarioId}")
	public ResponseEntity<List<PerfilEstudiante>> listarEstudiantes(@PathVariable("usuarioId") Integer id){
		return ResponseEntity.ok(usuarioService.listarEstudiantes(id));
	}
}
