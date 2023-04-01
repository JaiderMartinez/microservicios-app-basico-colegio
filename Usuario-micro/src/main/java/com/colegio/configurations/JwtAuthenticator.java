package com.colegio.configurations;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.nimbusds.jwt.JWTClaimsSet;

public class JwtAuthenticator extends AbstractAuthenticationToken{
	
	private Object usuarioLogeado;
	private JWTClaimsSet claims;
	
	
	public JwtAuthenticator(Collection<? extends GrantedAuthority> authorities, Object usuarioLogeado,
			JWTClaimsSet claims) {
		super(authorities);
		this.usuarioLogeado = usuarioLogeado;
		this.claims = claims;
		super.setAuthenticated(true);
	}
	
	@Override
	public Object getCredentials() {
		return null;
	}
	@Override
	public Object getPrincipal() {
		return null;
	}

}
