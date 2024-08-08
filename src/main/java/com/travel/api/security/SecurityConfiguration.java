package com.travel.api.security;

import com.travel.api.model.auth.Permission;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpMethod.DELETE;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfiguration {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtFilter jwtFilter;
    private final UserDetailsService userDetailsService;

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorize ->
                                authorize
                                  .requestMatchers(
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
                                  )
                                  .permitAll()
                                        .requestMatchers(POST, "/auth/**").permitAll()
                                        .requestMatchers(GET, "/ping").permitAll()

                                        .requestMatchers(GET, "/files/**").permitAll()
                                        .requestMatchers(POST, "/files").permitAll()

                                        .requestMatchers(GET, "/client/**").permitAll()

                                        .requestMatchers(GET, "/travel/**").permitAll()
                                        .requestMatchers(POST, "/travel/**").hasRole(Permission.ADMIN.name())
                                        .requestMatchers(PUT, "/travel/**").hasRole(Permission.ADMIN.name())
                                        .requestMatchers(DELETE, "/travel/**").hasRole(Permission.ADMIN.name())

                                        .requestMatchers(GET, "/travels/**").permitAll()
                                        .requestMatchers(POST, "/travels/**").hasRole(Permission.ADMIN.name())
                                        .requestMatchers(PUT, "/travels/**").hasRole(Permission.ADMIN.name())
                                        .requestMatchers(DELETE, "/travels/**").hasRole(Permission.ADMIN.name())

                                        .requestMatchers(GET, "/steps/**").permitAll()
                                        .requestMatchers(POST, "/steps/**").hasRole(Permission.ADMIN.name())
                                        .requestMatchers(PUT, "/steps/**").hasRole(Permission.ADMIN.name())
                                        .requestMatchers(DELETE, "/steps/**").hasRole(Permission.ADMIN.name())

                                        .requestMatchers(PUT, "/step/**").hasRole(Permission.ADMIN.name())

                                        .requestMatchers(GET, "/bookings/**").permitAll()
                                        .requestMatchers(POST, "/bookings/**").hasRole(Permission.ADMIN.name())
                                        .requestMatchers(PUT, "/bookings/**").hasRole(Permission.ADMIN.name())
                                        .requestMatchers(DELETE, "/bookings/**").hasRole(Permission.ADMIN.name())

                                        .requestMatchers(GET, "/booking/**").permitAll()
                                        .requestMatchers(PUT, "/booking/**").authenticated()
                                        .requestMatchers(PUT, "/booking/validation/**").hasRole(Permission.ADMIN.name())

                                        .anyRequest().authenticated()
                )
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .cors().and()
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return daoAuthenticationProvider;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type", "*"));
        configuration.setExposedHeaders(List.of("X-Total-Count"));
        configuration.setAllowCredentials(false);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}