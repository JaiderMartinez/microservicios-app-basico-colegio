package com.colegio.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.colegio.modelo.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Integer>{

}
