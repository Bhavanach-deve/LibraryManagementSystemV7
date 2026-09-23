package com.learnjava.libraraymanagementsystemv7.config;

import com.learnjava.libraraymanagementsystemv7.service.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
public class SecurityConfig
{
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(
            UserDetailsService userDetailsService)
    {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthenticationProvider authenticationProvider,
            JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authenticationProvider(authenticationProvider)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()

                        .requestMatchers(HttpMethod.POST, "/librarian-invitations").hasRole("ADMIN")
                        .requestMatchers(
                                HttpMethod.POST,
                                "/librarian-invitations/accept"
                        ).permitAll()
                        .requestMatchers(
                                HttpMethod.GET,
                                "/books",
                                "/books/**"
                        )
                        .hasAnyRole("USER", "LIBRARIAN", "ADMIN")

                        .requestMatchers(
                                HttpMethod.POST,
                                "/books"
                        )
                        .hasRole("LIBRARIAN")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/books/**"
                        )
                        .hasRole("LIBRARIAN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/books/**"
                        )
                        .hasRole("LIBRARIAN")
                        .requestMatchers(
                                HttpMethod.GET,
                                "/librarians",
                                "/librarians/**"
                        )
                        .hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/librarians/**"
                        )
                        .hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/librarians/**"
                        )
                        .hasRole("ADMIN")


                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }
}