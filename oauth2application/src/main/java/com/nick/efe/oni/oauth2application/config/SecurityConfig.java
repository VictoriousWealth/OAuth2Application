package com.nick.efe.oni.oauth2application.config;

import com.nick.efe.oni.oauth2application.config.filter.LoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.addFilterBefore(new LoggingFilter(), WebAsyncManagerIntegrationFilter.class)
                ;

        //TODO add authentication
        //TODO jwt
        //TODO OAuth2
        //TODO Basic Authentication

        http.authorizeHttpRequests(authorizeRequests -> {
            authorizeRequests
                    .requestMatchers(HttpMethod.GET, "/", "/favicon.ico", "/error").permitAll()
                    .anyRequest().authenticated();
        });


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
