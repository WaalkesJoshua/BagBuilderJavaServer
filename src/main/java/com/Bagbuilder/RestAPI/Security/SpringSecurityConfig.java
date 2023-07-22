package com.Bagbuilder.RestAPI.Security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );
        http.httpBasic();
        http.csrf().disable();
        http.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
            // Set the status code to 403 when unauthorized
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        });
        return http.build();
    }
}
