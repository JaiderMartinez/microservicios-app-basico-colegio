package com.colegio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colegio.entity.Materia;
import com.colegio.repository.IMateriaRepository;


@Service
public class MateriaService implements IMateriaService {
	
	@Autowired
	private IMateriaRepository materiaRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Materia> getAll(){
		return materiaRepository.findAll();
	}
	
	@Transactional
	@Override
	public Materia getMateriaById(Long id) {
		return materiaRepository.findById(id).orElse(null);
	}
	
	@Transactional
	@Override
	public Materia save(Materia materia) {
		Materia nuevaMateria = materiaRepository.save(materia);
		return nuevaMateria;
	}
	
	@Transactional
	@Override
	public List<Materia> byUsuarioId(int usuarioId){
		return materiaRepository.findByUsuarioId(usuarioId);
	}

	@Override
	public List<Materia> listaMateriasAprobadas(int usuarioId) {
		if(materiaRepository.findByNotaMajor(usuarioId).size() == 0 
				|| materiaRepository.findByNotaMajor(usuarioId).isEmpty()) {
			return null;
		}
		return materiaRepository.findByNotaMajor(usuarioId);
	}

	@Override
	public List<Materia> listaMateriasDesaprobadas(int usuarioId) {
		if(materiaRepository.findMateriaDesaprobadaByNotaAndUsuarioId(usuarioId).size() == 0 
				|| materiaRepository.findMateriaDesaprobadaByNotaAndUsuarioId(usuarioId).isEmpty()) {
			return null;
		}
		return materiaRepository.findMateriaDesaprobadaByNotaAndUsuarioId(usuarioId);
	}


}
