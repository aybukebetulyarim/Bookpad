package com.library.bookpad.security;

import com.library.bookpad.service.AuthenticationService;
import com.library.bookpad.util.PasswordEncoderConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationService authenticationService;
    private final PasswordEncoderConfig passwordEncoderConfig;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
                          @Lazy AuthenticationService authenticationService,
                          PasswordEncoderConfig passwordEncoderConfig) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationService = authenticationService;
        this.passwordEncoderConfig = passwordEncoderConfig;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(x -> x.requestMatchers("/auth/welcome/**", "/auth/register/**", "/auth/login/**", "/auth/refreshToken/**").permitAll())
                .authorizeHttpRequests(x ->
                        x
                                .requestMatchers("/auth/user/**").hasRole("USER")
                                .requestMatchers("/auth/admin/**").hasRole("ADMIN"))
                .authorizeHttpRequests(x -> x.requestMatchers("/users/**").hasRole("ADMIN"))
                .authorizeHttpRequests(x -> x.requestMatchers("/books/**").hasRole("USER"))
                .sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(authenticationService);
        authenticationProvider.setPasswordEncoder(passwordEncoderConfig.passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
