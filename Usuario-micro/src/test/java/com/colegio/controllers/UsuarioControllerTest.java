package com.colegio.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colegio.entity.Usuario;
import com.colegio.modelos.Materia;
import com.colegio.modelos.PerfilEstudiante;
import com.colegio.repositorio.IUsuarioDao;
import com.colegio.service.IUsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	void guardarUsuario() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/Usuario-micro/Usuario")
						.header("AutorizarToken", "eyJraWQiOiI4d2J5a1dRc0NpOVFnXC9mb2JRUksxdlo5dkI4WmVpVXJjV3pwd3hCTVYxVT0iLCJhbGciOiJSUzI1NiJ9.eyJhdF9oYXNoIjoiVjFjSF9PbkkyMFZtLWNsQWEwYXVVZyIsInN1YiI6IjZiMmEyN2U4LWQ4NTYtNDJkMy1iZDhkLWE2YzRhZDFiOWM0NiIsImF1ZCI6IjY2NDFoazdnMG1vdjloZnZtdWpsbWR2OGhtIiwiZXZlbnRfaWQiOiJkZmMxZTZkYi00MTYzLTRmMzQtODdiMC1mZGYzODkxZTVkZmMiLCJ0b2tlbl91c2UiOiJpZCIsImF1dGhfdGltZSI6MTY4MDM1OTE0NCwiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLnVzLWVhc3QtMS5hbWF6b25hd3MuY29tXC91cy1lYXN0LTFfeml1d0tsd21VIiwiY29nbml0bzp1c2VybmFtZSI6IjEwOTAzNzg4NzQiLCJleHAiOjE2ODAzNjI3NDQsImlhdCI6MTY4MDM1OTE0NCwianRpIjoiYTc0NWYzNTctOTNhNy00MzhhLTk3ZWUtZjVhYzI3OWQ1YzNjIn0.FDeJHjP7lBAK4UXp5QAeZU0zpPbz6ikYWBnM4pthuQbarfrKSMM-4-dDH3bVauvRWi0cAMxfhbXLxxZVAn-mYT7aobfhU5vHb9Fm8-uW_lQxk3_Mx5SC9jHMCqQjORzDyIrF9TuIg0yWH2r_QFX6onSIqHw1ySsep_CtcBgxLbi-vElBLpmr6PE1NeIGksElsPmb81aZOXpwea79JWz9EvijSgsYyj1e19kcLamuEdXDdXqPnQkZ6bKSvQEDe8zqVhGITYu2hsJpJWKLPMU6T7mJ4MpWKG4Qu2r6pFZW2x57xEuAc3rs7nOcrR7kgA4fTauuVsXK93T1q5KrkNDqTQ")
						.content(objectMapper.writeValueAsString(usuarioTest()))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id_usuario").value(1)).andReturn();
		
		Assertions.assertEquals(objectMapper.writeValueAsString(usuarioTest()), result.getResponse().getContentAsString());
	}
	
	@Test
	void obtenerUsuario() throws Exception {
		MvcResult usuarioObtenido = mockMvc
				.perform(MockMvcRequestBuilders.get("/Usuario-micro/Usuario/1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1)).andReturn();
		
		Assertions.assertEquals(objectMapper.writeValueAsString(usuarioTest()),
				usuarioObtenido.getResponse().getContentAsString());
		Assertions.assertFalse(usuarioObtenido.getResponse().getContentAsString()
				.equals(objectMapper.writeValueAsString(usuarioTestDos())), "Los dos estudiantes son iguales");
	}
	
	@Test
	void listarMaterias() {
		
	}
	
	@Test
	void obtenerPerfilDelEstudiante() {
		
	}
	
	private Usuario usuarioTest() {
		Usuario user = new Usuario();
		user.setId_usuario(1);
		user.setUser_dni("1090378874");
		return user;
	}
	
	private Usuario usuarioTestDos() {
		Usuario user = new Usuario();
		user.setId_usuario(2);
		user.setUser_dni("23445665");
		return user;
	}
	
}
