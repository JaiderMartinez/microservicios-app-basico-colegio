package com.colegio.service;

import java.util.List;

import com.colegio.entity.Usuario;
import com.colegio.modelos.Materia;
import com.colegio.modelos.PerfilEstudiante;

public interface IUsuarioService {

    public Usuario guardar(Usuario usuario);
    public Usuario encontrarUsuario(Integer idUsuario);
    public boolean findExistUsuario(Integer id);
    public List<Materia> listarMaterias(Long usuarioId);
    public PerfilEstudiante mostrarPerfilDelEstudiante(Integer usuarioId);
    public String signIn(String username , String password);
    
}
