package com.colegio.configurations;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;



@Component
public class JwtFilter extends OncePerRequestFilter {

	
	@Autowired
    JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication;
        try {
            authentication = jwtProvider.authenticate(request);
            if(authentication != null)
                SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
           System.out.println("fallo en hacer el filtro ");
           System.out.println(e);
        }
        filterChain.doFilter(request, response);
    }

}
