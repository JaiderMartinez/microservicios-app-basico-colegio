package com.colegio.perfil.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.colegio.perfil.entity.Estudiante;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
public class EstudianteControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void guardarEstudiante() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/PerfilEstudiante-micro/Perfil")
						.content(objectMapper.writeValueAsString(informacionEstudiante()))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1)).andReturn();
		
		assertEquals(objectMapper.writeValueAsString(informacionEstudiante()),
				result.getResponse().getContentAsString());
		assertFalse("Los dos estudiantes son iguales", result.getResponse().getContentAsString()
				.equals(objectMapper.writeValueAsString(informacionEstudianteDos())));
	}

	@Test
	void obtenerEstudiante() throws Exception {
		MvcResult perfilEstudiante = mockMvc
				.perform(MockMvcRequestBuilders.get("/PerfilEstudiante-micro/Perfil/1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1)).andReturn();
		
		assertEquals(objectMapper.writeValueAsString(informacionEstudiante()),
				perfilEstudiante.getResponse().getContentAsString());
		assertFalse("Los dos estudiantes son iguales", perfilEstudiante.getResponse().getContentAsString()
				.equals(objectMapper.writeValueAsString(informacionEstudianteDos())));
	}

	private Estudiante informacionEstudiante() {
		Estudiante perfilEstudiante = new Estudiante();
		perfilEstudiante.setId(1);
		perfilEstudiante.setNumeroDeIdentificacion(1090378874);
		perfilEstudiante.setNombre("jaider");
		perfilEstudiante.setApellido1("mar");
		perfilEstudiante.setApellido2("asd");
		perfilEstudiante.setCorreo("sadsdf");
		perfilEstudiante.setDireccion("asdsad");
		perfilEstudiante.setFechaDeNacimiento("ad");
		perfilEstudiante.setPromedio(4);
		perfilEstudiante.setTelefono(30139863);
		perfilEstudiante.setUsuarioId(1);
		return perfilEstudiante;
	}

	private ResponseEntity<Estudiante> informacionEstudianteDos() {
		Estudiante perfilEstudiante = new Estudiante();
		perfilEstudiante.setId(2);
		perfilEstudiante.setNumeroDeIdentificacion(1090378874);
		perfilEstudiante.setNombre("jaider");
		perfilEstudiante.setApellido1("mar");
		perfilEstudiante.setApellido2("asd");
		perfilEstudiante.setCorreo("sadsdf");
		perfilEstudiante.setDireccion("asdsad");
		perfilEstudiante.setFechaDeNacimiento("ad");
		perfilEstudiante.setPromedio(4);
		perfilEstudiante.setTelefono(30139863);
		perfilEstudiante.setUsuarioId(1);
		return ResponseEntity.ok(perfilEstudiante);
	}
}
