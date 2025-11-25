package com.learning.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Slf4j
public class CustomSecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-url}")
    private String issuerUri;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{

            http.authorizeHttpRequests(
                    authorize -> authorize.requestMatchers("/customer/*").authenticated()
                            .anyRequest().authenticated()
            ).oauth2ResourceServer(oauth2 -> oauth2
                    .jwt(Customizer.withDefaults()));
            return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(RealmAccessRoleConverter realmAccessRoleConverter){
        JwtAuthenticationConverter convertor = new JwtAuthenticationConverter();
        convertor.setJwtGrantedAuthoritiesConverter(realmAccessRoleConverter);
        return convertor;
    }

}
