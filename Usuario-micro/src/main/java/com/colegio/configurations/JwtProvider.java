package com.colegio.configurations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.colegio.service.IUsuarioService;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtProvider {

	@Value("${spring.security.oauth2.client.provider.cognito.issuer-uri}")
	private String identityPoolUrl;
	private static final String USERNAME_FIELD = "cognito:username";
    private static final String AUTHORIZATION = "AutorizarToken";
    
    
    @Autowired
    ConfigurableJWTProcessor<?> configurableJWTProcessor;
	
	public Authentication authenticate(HttpServletRequest request) throws Exception {
        String token = request.getHeader(AUTHORIZATION);
        if (token != null) {
            JWTClaimsSet claims = configurableJWTProcessor.process(getToken(token), null);
            validacionDelToken(claims);
            String username = getUsername(claims);
            if (username != null ) {
                List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
                User user = new User(username.toString(), "", authorities);
                return new JwtAuthenticator(authorities, user, claims);
            }
        }
        return null;
    }
	
	private String getUsername(JWTClaimsSet claims) {
		return (String) claims.getClaim(USERNAME_FIELD);
	}
	
	private void validacionDelToken(JWTClaimsSet claims) throws Exception {
		if(!claims.getIssuer().equals(identityPoolUrl)) {
			throw new Exception("JWT not valid");
		}
	}
	
	private String getToken(String token) {
		return token.startsWith("Bearer ") ? token.substring("Bearer ".length()) : token;
	}
}
