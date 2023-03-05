package com.colegio.service;

import java.util.List;

import com.colegio.entity.Materia;

public interface IMateriaService {

	public List<Materia> getAll();
	public Materia getMateriaById(Long id);
	public Materia save(Materia materia);
	public List<Materia> byUsuarioId(int usuarioId);
}
