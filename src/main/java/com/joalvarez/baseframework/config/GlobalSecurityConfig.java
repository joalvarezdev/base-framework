package com.joalvarez.baseframework.config;

import com.joalvarez.baseframework.data.dto.EndPointSecurityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class GlobalSecurityConfig {

    private EndPointSecurityConfig endPointSecurity;

    private static final String[] IGNORED_PATHS = {
            "/api/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        List<EndPointSecurityDTO> endpoints = this.endPointSecurity.getEndpoints();

        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers(IGNORED_PATHS).permitAll();
        });

        if (Objects.nonNull(endpoints) && !endpoints.isEmpty()) {

            for (EndPointSecurityDTO endpoint : endpoints) {
                List<String> authorizations = endpoint.getAuthorities();
                if (Objects.nonNull(authorizations) && !authorizations.isEmpty()) {
                    String authorities = authorizations.stream()
                            .map(authorization -> "'ROLE_".concat(authorization).concat("'"))
                            .collect(Collectors.joining(","));
                    http
                            .authorizeHttpRequests(auth -> {
                                auth.requestMatchers(endpoint.getPath()).hasAnyRole(authorities);
                            });
                }

            }
        }

        return http
                .build();
    }

    @Autowired
    public void setEndPointSecurity(EndPointSecurityConfig endPointSecurity) {
        this.endPointSecurity = endPointSecurity;
    }
}
