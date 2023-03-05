package com.colegio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.colegio.entity.Materia;

public interface IMateriaRepository extends JpaRepository<Materia, Long> {

	@Query(value = "select * from materia where materia.usuario_id=:usuarioIdBuscar", nativeQuery = true)
	List<Materia> findByUsuario(@Param("usuarioIdBuscar")int usuarioId);
}
