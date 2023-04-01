package com.colegio.modelos;

public class PerfilEstudiante {

	private int id;
	private int numeroDeIdentificacion;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String fechaDeNacimiento;
	private String direccion;
	private String correo;
	private int telefono;
	private int promedio;
	private int usuarioId;

	
	public PerfilEstudiante() {}
	
	public PerfilEstudiante(int numeroDeIdentificacion, String nombre, String apellido1, String apellido2,
			String fechaDeNacimiento, String direccion, String correo, int telefono, int promedio, int usuarioId) {
		this.numeroDeIdentificacion = numeroDeIdentificacion;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.direccion = direccion;
		this.correo = correo;
		this.telefono = telefono;
		this.promedio = promedio;
		this.usuarioId = usuarioId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroDeIdentificacion() {
		return numeroDeIdentificacion;
	}

	public void setNumeroDeIdentificacion(int numeroDeIdentificacion) {
		this.numeroDeIdentificacion = numeroDeIdentificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(String fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public int getPromedio() {
		return promedio;
	}

	public void setPromedio(int promedio) {
		this.promedio = promedio;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

}
