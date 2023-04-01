package com.colegio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.colegio.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Integer>{

}
