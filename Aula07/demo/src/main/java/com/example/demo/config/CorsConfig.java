package com.example.demo.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Configuração de CORS (Cross-Origin Resource Sharing)
 * Permite que o frontend acesse a API de diferentes origens
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // Permite credenciais (cookies, headers de autenticação)
        config.setAllowCredentials(true);
        
        // Origens permitidas - adicione aqui os domínios do seu frontend
        config.setAllowedOriginPatterns(Arrays.asList(
            "http://localhost:*",
            "http://127.0.0.1:*",
            "file://*",
            "https://*.azurestaticapps.net",
            "https://*.azurewebsites.net"
        ));
        
        // Headers permitidos
        config.setAllowedHeaders(Arrays.asList(
            "Origin",
            "Content-Type",
            "Accept",
            "Authorization",
            "X-Requested-With",
            "Access-Control-Request-Method",
            "Access-Control-Request-Headers"
        ));
        
        // Métodos HTTP permitidos
        config.setAllowedMethods(Arrays.asList(
            "GET",
            "POST",
            "PUT",
            "DELETE",
            "PATCH",
            "OPTIONS"
        ));
        
        // Headers expostos ao frontend
        config.setExposedHeaders(List.of("Authorization"));
        
        // Tempo de cache para requisições preflight (OPTIONS)
        config.setMaxAge(3600L);
        
        source.registerCorsConfiguration("/api/**", config);
        
        return new CorsFilter(source);
    }
}
