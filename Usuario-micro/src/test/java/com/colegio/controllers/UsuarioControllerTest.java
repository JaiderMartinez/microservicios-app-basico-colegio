package com.colegio.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.colegio.entity.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	
	@Test
	void guardarUsuario_1() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/Usuario-micro/Usuario")
						.header("AutorizarToken",
								"eyJraWQiOiI4d2J5a1dRc0NpOVFnXC9mb2JRUksxdlo5dkI4WmVpVXJjV3pwd3hCTVYxVT0iLCJhbGciOiJSUzI1NiJ9.eyJhdF9oYXNoIjoiekVEQ09fVVBXS3M0aWlkR05VQy1NZyIsInN1YiI6IjZiMmEyN2U4LWQ4NTYtNDJkMy1iZDhkLWE2YzRhZDFiOWM0NiIsImF1ZCI6IjY2NDFoazdnMG1vdjloZnZtdWpsbWR2OGhtIiwiZXZlbnRfaWQiOiJjZTVjNTVjYy02YjkxLTQ0NmYtYmQ3Ni02NTUxYTIzNTI4N2IiLCJ0b2tlbl91c2UiOiJpZCIsImF1dGhfdGltZSI6MTY4MDM5MTYzMSwiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLnVzLWVhc3QtMS5hbWF6b25hd3MuY29tXC91cy1lYXN0LTFfeml1d0tsd21VIiwiY29nbml0bzp1c2VybmFtZSI6IjEwOTAzNzg4NzQiLCJleHAiOjE2ODAzOTUyMzEsImlhdCI6MTY4MDM5MTYzMSwianRpIjoiNDcwNWY0NmYtYjhiYy00OWE4LWFmNjAtZjE0ZjY5MWI2M2I4In0.Lp-9BtXXnaW5GThWY4a5tWlNllJx-cE8WqBISTmWSt9_EDknDevbmo1Y8Xz6YNUp89osnNRDHYzgAx2Q-zeqAh9DK-TR2_5YxTJUmZbBmoD2kdtqVWFGuB3IoFuu38E8VEhcEvWPMe8cBzOleNJCK3fNfBPQcBwZ3olRXrOJyz7ThjvdlveI6ZqpRFjyba0z9XbyQ8D_qSmHOhdI1cbSj7dp8ARcTYMQNeeZppSZtQZ76wFXpOdJ7UBwd4IJaXVdQ8rQ6yinrRV5TV_oafN8f9zIdsYob2ouiJ31wSTQkCJfbPdcM2eLCZLCS3NKyIKNkuKKWoRt3ONsBTjmuA3eAA")
						.content(objectMapper.writeValueAsString(usuarioTest()))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id_usuario").value(1)).andReturn();
		
		Assertions.assertEquals(objectMapper.writeValueAsString(usuarioTest()), result.getResponse().getContentAsString());
	}
	
	@Test
	void obtenerUsuario_2() throws Exception {
		MvcResult usuarioObtenido = mockMvc
				.perform(MockMvcRequestBuilders.get("/Usuario-micro/Usuario/1")
						.header("AutorizarToken",
								"eyJraWQiOiI4d2J5a1dRc0NpOVFnXC9mb2JRUksxdlo5dkI4WmVpVXJjV3pwd3hCTVYxVT0iLCJhbGciOiJSUzI1NiJ9.eyJhdF9oYXNoIjoiekVEQ09fVVBXS3M0aWlkR05VQy1NZyIsInN1YiI6IjZiMmEyN2U4LWQ4NTYtNDJkMy1iZDhkLWE2YzRhZDFiOWM0NiIsImF1ZCI6IjY2NDFoazdnMG1vdjloZnZtdWpsbWR2OGhtIiwiZXZlbnRfaWQiOiJjZTVjNTVjYy02YjkxLTQ0NmYtYmQ3Ni02NTUxYTIzNTI4N2IiLCJ0b2tlbl91c2UiOiJpZCIsImF1dGhfdGltZSI6MTY4MDM5MTYzMSwiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLnVzLWVhc3QtMS5hbWF6b25hd3MuY29tXC91cy1lYXN0LTFfeml1d0tsd21VIiwiY29nbml0bzp1c2VybmFtZSI6IjEwOTAzNzg4NzQiLCJleHAiOjE2ODAzOTUyMzEsImlhdCI6MTY4MDM5MTYzMSwianRpIjoiNDcwNWY0NmYtYjhiYy00OWE4LWFmNjAtZjE0ZjY5MWI2M2I4In0.Lp-9BtXXnaW5GThWY4a5tWlNllJx-cE8WqBISTmWSt9_EDknDevbmo1Y8Xz6YNUp89osnNRDHYzgAx2Q-zeqAh9DK-TR2_5YxTJUmZbBmoD2kdtqVWFGuB3IoFuu38E8VEhcEvWPMe8cBzOleNJCK3fNfBPQcBwZ3olRXrOJyz7ThjvdlveI6ZqpRFjyba0z9XbyQ8D_qSmHOhdI1cbSj7dp8ARcTYMQNeeZppSZtQZ76wFXpOdJ7UBwd4IJaXVdQ8rQ6yinrRV5TV_oafN8f9zIdsYob2ouiJ31wSTQkCJfbPdcM2eLCZLCS3NKyIKNkuKKWoRt3ONsBTjmuA3eAA")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id_usuario").value(1)).andReturn();
		
		Assertions.assertEquals(objectMapper.writeValueAsString(usuarioTest()),
				usuarioObtenido.getResponse().getContentAsString());
		Assertions.assertFalse(usuarioObtenido.getResponse().getContentAsString()
				.equals(objectMapper.writeValueAsString(usuarioTestDos())), "Los dos usuarios son iguales");
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
