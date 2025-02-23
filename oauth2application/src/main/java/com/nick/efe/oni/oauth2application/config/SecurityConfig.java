package com.nick.efe.oni.oauth2application.config;

import com.nick.efe.oni.oauth2application.config.filter.CustomJwtAuthFilter;
import com.nick.efe.oni.oauth2application.config.filter.LoggingFilter;
import com.nick.efe.oni.oauth2application.config.provider.CustomJwtAuthenticationProvider;
import com.nick.efe.oni.oauth2application.repository.UserRepository;
import com.nick.efe.oni.oauth2application.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomJwtAuthenticationProvider customJwtAuthenticationProvider;

    public SecurityConfig(CustomJwtAuthenticationProvider customJwtAuthenticationProvider) {
        this.customJwtAuthenticationProvider = customJwtAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserRepository userRepository) throws Exception {
        // 🔹 Disable CSRF (only for APIs; keep it enabled for traditional web apps)
        http.csrf(AbstractHttpConfigurer::disable);

        // 🔹 CORS configuration (enable if frontend is on a different origin)
        http.cors(Customizer.withDefaults());

        // 🔹 Custom Logging Filter (Before AsyncManagerFilter)
        http.addFilterBefore(new LoggingFilter(), WebAsyncManagerIntegrationFilter.class);
        http.addFilterBefore(new CustomJwtAuthFilter(requestMatcher(),
                authenticationManager(userDetailsService(userRepository))), UsernamePasswordAuthenticationFilter.class);

        // 🔹 Logout Filter (Enables /logout)
        http.logout(logout -> logout.logoutUrl("/logout").permitAll());

        // 🔹 Authentication Filters
        http.httpBasic(Customizer.withDefaults());  // Basic Auth (for APIs)

        // 🔹 Session Management (Stateless for JWT, Session-Based for OAuth2)
        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 🔹 Role-Based Authorization
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers(HttpMethod.GET, "/", "/favicon.ico", "/error").permitAll();
            auth.requestMatchers("/login").permitAll();
            auth.requestMatchers("/basic").hasRole("BASIC");
            auth.requestMatchers("/intermediate").hasRole("INTERMEDIATE");
            auth.requestMatchers("/advanced").hasRole("ADVANCED");
            auth.requestMatchers("/master").hasRole("MASTER");
            auth.requestMatchers("/supreme").hasRole("SUPREME");
            auth.anyRequest().authenticated(); // Protect all other routes
        });

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setUserDetailsService(userDetailsService);
        daoProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));

        return new ProviderManager(List.of(customJwtAuthenticationProvider, daoProvider));
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new CustomUserDetailsService(userRepository); // Ensure this is properly implemented
    }

    @Bean
    public RequestMatcher requestMatcher() {
        return new AntPathRequestMatcher("**");
    }

}
