package com.colegio.modelos;

public class Materia {

	private Long id;
	private int nota;
	private String nombre;
	private int usuario_id;

	public Materia() {}

	public Materia(int nota, String nombre, int usuario_id) {
		this.nota = nota;
		this.nombre = nombre;
		this.usuario_id = usuario_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
		
}
