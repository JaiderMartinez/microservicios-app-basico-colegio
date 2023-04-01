package com.colegio.configurations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class Configurations {

	@Autowired
    JwtFilter jwtFilter;
	
	@Autowired
	JwtEntryPoint jwtEntryPoint;
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and()
    	.csrf().disable()
    	.authorizeHttpRequests((authz) -> authz
    			.requestMatchers("/auth/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/v3/api-docs/**")
    			.permitAll()
    			.requestMatchers("/Usuario-micro/Usuario/sing-in/**")
                .permitAll()
    			.anyRequest()
                .authenticated())
        .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    
    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);   
    return http.build();
    }
}
