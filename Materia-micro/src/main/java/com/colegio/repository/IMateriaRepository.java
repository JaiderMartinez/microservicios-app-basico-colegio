package com.colegio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.colegio.entity.Materia;

public interface IMateriaRepository extends JpaRepository<Materia, Long> {

	List<Materia> findByUsuarioId(int usuarioId);
	
	@Query(value = "select * from materia where materia.nota>5 and materia.usuario_id=:id order by nota desc", nativeQuery = true)
	List<Materia> findByNotaMajor( @Param("id") int id);
	
	@Query(value = "select * from materia where materia.nota<=5 and materia.usuario_id=:id order by nota desc", nativeQuery = true)
	List<Materia> findMateriaDesaprobadaByNotaAndUsuarioId( @Param("id")int id);
}
