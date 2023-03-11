package com.colegio.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.colegio.entity.Usuario;
import com.colegio.modelos.Materia;
import com.colegio.modelos.PerfilEstudiante;
import com.colegio.repositorio.IUsuarioDao;
import com.colegio.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	
	@Autowired
	private IUsuarioDao usuarioRepo;

	WebClient webClient = WebClient.builder().build();

	@Override
	@Transactional
	public Usuario guardar(Usuario usuario) {
		return usuarioRepo.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario encontrarUsuario(Integer idUsuario) {
		return usuarioRepo.findById(idUsuario).orElse(null);
	}

	@Override
	@Transactional
	public boolean findExistUsuario(Integer id) {
		return usuarioRepo.existsById(id);
	}

	@Override
	public List<Materia> listarMaterias(Long usuarioId) {
		List<Materia> materias = webClient.get().uri("http://localhost:8081/mic-materias/materias/usuario/"  + usuarioId)
															.retrieve()
															.bodyToFlux(Materia.class)
															.collectList()
															.block();
		return materias;
	}

	@Override
	public PerfilEstudiante listarEstudiantes(int usuarioId) {
	PerfilEstudiante  estudiante = this.webClient.get().uri("http://localhost:8082/PerfilEstudiante-micro/Perfil/" + usuarioId)
														.retrieve()
														.bodyToFlux(PerfilEstudiante.class)
														.blockFirst();
		return estudiante;
	}

}
