package io.github.publications.publicationsapi.config;

import io.github.publications.publicationsapi.application.jwt.JwtService;
import io.github.publications.publicationsapi.config.filter.JwtFilter;
import io.github.publications.publicationsapi.domain.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public JwtFilter jwtFilter(JwtService jwtService, UserService userService){
        return new JwtFilter(jwtService, userService);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    // auth.anyRequest().permitAll() -  permite qualquer url fazer requisicao
    // auth.requestMatchers("v1/users/**").permitAll(); - autenticacao especifica
    // auth.anyRequest().authenticated(); - qualquer requisicao vai precisar da autenticacao
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configure(http))
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("v1/users/**").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/v1/publications/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
        UrlBasedCorsConfigurationSource cors = new UrlBasedCorsConfigurationSource();
        // aqui posso configurar quais URLS podem ser acessadas
        // ex: /v1/users no lugar /**
        cors.registerCorsConfiguration("/**", config);

        return cors;
    }
}
