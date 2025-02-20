package com.nick.efe.oni.oauth2application.config;

import com.nick.efe.oni.oauth2application.config.filter.LoggingFilter;
import jakarta.websocket.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http//TODO cors filter
                .csrf(AbstractHttpConfigurer::disable);

        http.addFilterBefore(new LoggingFilter(), WebAsyncManagerIntegrationFilter.class)
                ;

        //TODO logout filter
        //TODO usernamePasswordAuthenticationFilter
        //TODO defaultLogoutPageGeneratingFilter
        //TODO defaultLoginPageGeneratingFilter
        //TODO Basic Authentication (Authorization: Basic ...)
        //TODO BearerTokenAuthenticationFilter (jwt)
        //TODO RequestCacheAwareFilter
        //TODO

        //SessionManagementFilter
        http.sessionManagement(session
                -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        // Security filter Chain interceptor
        http.authorizeHttpRequests(authorizeRequests -> {
            authorizeRequests.requestMatchers(HttpMethod.GET, "/", "/favicon.ico", "/error").permitAll();
            authorizeRequests.requestMatchers("/basic").hasRole("BASIC");
            authorizeRequests.requestMatchers("/intermediate").hasRole("INTERMEDIATE");
            authorizeRequests.requestMatchers("/advanced").hasRole("ADVANCED");
            authorizeRequests.requestMatchers("/master").hasRole("MASTER");
            authorizeRequests.requestMatchers("/supreme").hasRole("SUPREME");
            authorizeRequests.anyRequest().authenticated();
        });


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
