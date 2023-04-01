package com.colegio.serviceImpl;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.colegio.entity.Usuario;
import com.colegio.modelos.Materia;
import com.colegio.modelos.PerfilEstudiante;
import com.colegio.repositorio.IUsuarioDao;
import com.colegio.service.IUsuarioService;

import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AuthFlowType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.InitiateAuthRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.InitiateAuthResponse;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	
	@Autowired
	private IUsuarioDao usuarioRepo;

	WebClient webClient = WebClient.builder().build();

	@Override
	@Transactional
	public Usuario guardar(Usuario usuario) {
		return usuarioRepo.save(usuario);
	}

	@Override
	@Transactional
	public Usuario encontrarUsuario(Integer idUsuario) {
		return usuarioRepo.findById(idUsuario).orElse(null);
	}

	@Override
	@Transactional
	public boolean findExistUsuario(Integer id) {
		return usuarioRepo.existsById(id);
	}

	@Override
	public List<Materia> listarMaterias(Long usuarioId) {
		List<Materia> materias = webClient.get().uri("http://localhost:8081/mic-materias/materias/usuario/"  + usuarioId)
															.retrieve()
															.bodyToFlux(Materia.class)
															.collectList()
															.block();
		return materias;
	}

	@Override
	public PerfilEstudiante mostrarPerfilDelEstudiante(Integer usuarioId) {
	PerfilEstudiante  estudiante = this.webClient.get().uri("http://localhost:8082/PerfilEstudiante-micro/Perfil/" + usuarioId)
														.retrieve()
														.bodyToFlux(PerfilEstudiante.class)
														.blockFirst();
		return estudiante;
	}

	@Override
	public String signIn(String username, String password) {
		CognitoIdentityProviderClient identityProviderClient = CognitoIdentityProviderClient.builder()
				.region(Region.US_EAST_1).credentialsProvider(ProfileCredentialsProvider.create()).build();
		try {
			Map<String, String> authParameters = new HashMap<>();
			authParameters.put("USERNAME", username);
			authParameters.put("PASSWORD", password);
			InitiateAuthRequest authRequest = InitiateAuthRequest.builder().clientId("6641hk7g0mov9hfvmujlmdv8hm")
					.authParameters(authParameters).authFlow(AuthFlowType.USER_PASSWORD_AUTH).build();
			InitiateAuthResponse response = identityProviderClient.initiateAuth(authRequest);
			//Obtiene el id token del usuario, se puede obtener el access token
			return response.authenticationResult().idToken();
		} catch (CognitoIdentityProviderException e) {
			System.out.println(e.awsErrorDetails().errorMessage());
		} finally {
			identityProviderClient.close();
		}
		return null;
	}

}
