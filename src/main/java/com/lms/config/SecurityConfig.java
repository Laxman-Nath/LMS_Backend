package com.lms.config;

import com.lms.entities.mainentities.Librarian;
import com.lms.entities.mainentities.Student;
import com.lms.entities.mainentities.Teacher;
import com.lms.services.librarian.LibrarianService;
import com.lms.services.student.StudentService;
import com.lms.services.teacher.TeacherService;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
// Interface for providing CORS configuration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final RsaKeyProperties jwtConfigProperties;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(
						auth -> auth.requestMatchers("/lms/login").permitAll().requestMatchers("/lms/librarian/**")
								.hasRole("LIBRARIAN").requestMatchers("/lms/user/**").hasAnyRole("STUDENT", "TEACHER")
								.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

//				.requestMatchers("/lms/librarian/addbook").hasAuthority("ADD_BOOK")  // Authority check
//                .requestMatchers("/lms/librarian/deletebook").hasAuthority("DELETE_BOOK")
//                .requestMatchers("/lms/librarian/getallbooks").hasAuthority("GET_ALL_BOOKS")
								.anyRequest().authenticated()

				).cors(cors -> cors.configurationSource(corsConfigurationSource()))
				.httpBasic(AbstractHttpConfigurer::disable)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless
				.oauth2ResourceServer(
						oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())))
				.oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(Customizer.withDefaults())) // JWT
																													// support
				.exceptionHandling(ex -> ex.authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint()) // Handle
																												// authentication
																												// entry
						.accessDeniedHandler(new BearerTokenAccessDeniedHandler())) // Handle access denial
				.build();
	}

	@Bean
	JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withPublicKey(jwtConfigProperties.publicKey()).build();
	}

	@Bean
	JwtEncoder jwtEncoder() {
		JWK jwk = new RSAKey.Builder(jwtConfigProperties.publicKey()).privateKey(jwtConfigProperties.privateKey())
				.build();
		JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwks);
	}

	@Bean
	JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
		converter.setJwtGrantedAuthoritiesConverter(jwt -> {
			List<GrantedAuthority> authorities = new ArrayList<>();
			List<String> roles = jwt.getClaimAsStringList("roles"); // roles claim
			if (roles != null) {
				authorities.addAll(
						roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList()));
			}
			List<String> scopes = jwt.getClaimAsStringList("scopes"); // scopes or permissions claim
			if (scopes != null) {
				authorities.addAll(
						scopes.stream().map(scope -> new SimpleGrantedAuthority(scope)).collect(Collectors.toList()));
			}
			return authorities;
		});
		return converter;
	}

	@Bean // Marks this method as a bean definition
	CorsConfigurationSource corsConfigurationSource() { // Defines CORS configuration
		CorsConfiguration configuration = new CorsConfiguration(); // Creates a new CORS configuration object
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173")); // Specifies allowed origins for CORS
		configuration.setAllowCredentials(true); // Allows credentials (like cookies) to be included in requests
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Specifies allowed
																									// HTTP methods
		configuration.setAllowedHeaders(Arrays.asList("*")); // Allows all headers in CORS requests
		configuration.setExposedHeaders(Arrays.asList("Authorization")); // Exposes the Authorization header to the
																			// client

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); // Creates a source for
																						// URL-based CORS configuration
		source.registerCorsConfiguration("/**", configuration); // Registers the CORS configuration for all endpoints
		return source; // Returns the CORS configuration source
	}

}
