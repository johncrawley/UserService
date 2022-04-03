package com.jcrawley.userservice.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

//@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
//@EnableWebSecurity
@EnableResourceServer
@Configuration
public class WebSecurity extends ResourceServerConfigurerAdapter {


	@Override
	public void configure(HttpSecurity http) throws Exception {

		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
		
		  http
          .requestMatchers()
              .antMatchers(HttpMethod.GET, "/api/v1/user**")
          .and().authorizeRequests()
              .anyRequest().authenticated();
		
		 /*
		http.cors().and().authorizeRequests()
    	.antMatchers("/api/**").permitAll()
    	.antMatchers("/api/v1/user").permitAll()
    	.antMatchers(HttpMethod.GET, "/api/v1/user").authenticated()
    	.antMatchers("/").permitAll();*/
	}
	
	

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("my-resource-id");
    }
    
	
	/*
	 * doesn't work for anonymous access

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
		
		http.cors().and().authorizeRequests()
		.antMatchers("/login", "/api/v1/user").permitAll()
		.antMatchers(HttpMethod.POST, "/api/v1/user").permitAll()
		.anyRequest().authenticated()
		//.anyRequest().authenticated()
		//.antMatchers(HttpMethod.GET, "/api/v1/user**")
		//.hasAuthority("SCOPE_profile")
		//.hasRole("user")
		.and()	
		.oauth2ResourceServer()
		.jwt()
		.jwtAuthenticationConverter(jwtAuthenticationConverter);
	}
	
	*/
	
	

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Arrays.asList("*"));
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        corsConfig.setAllowedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }

}