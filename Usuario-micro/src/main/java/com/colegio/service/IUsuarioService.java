package com.colegio.service;

import java.util.List;

import com.colegio.modelo.Usuario;
import com.colegio.modelos.Materia;
import com.colegio.modelos.PerfilEstudiante;

public interface IUsuarioService {


	public List<Usuario> listarUsuarios();
    
    public Usuario guardar(Usuario usuario);
    
    public void eliminar(Integer idUsuario);
    
    public Usuario encontrarUsuario(Integer idUsuario);
    
    public boolean findExistUsuario(Integer id);
    
    public List<Materia> listarMaterias(Long usuarioId);
    public List<PerfilEstudiante> listarEstudiantes(int usuarioId);
}
