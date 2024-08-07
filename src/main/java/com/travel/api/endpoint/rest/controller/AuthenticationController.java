package com.travel.api.endpoint.rest.controller;

import com.travel.api.endpoint.rest.dto.AuthenticationDto;
import com.travel.api.model.auth.Client;
import com.travel.api.security.JwtService;
import com.travel.api.service.impl.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private CustomUserDetailsService userService;
    private JwtService jwtService;

    @PostMapping(path = "register")
    public Map<String, String> register(@RequestBody Client client) {
        this.userService.createUser(client);
        return jwtService.generate(client.getEmail());
    }

    @PostMapping(path = "login")
    public Map<String, String> login(@RequestBody AuthenticationDto authenticationDto) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationDto.email(),
                        authenticationDto.password()
                )
        );

        if (authenticate.isAuthenticated()) {
            return this.jwtService.generate(authenticationDto.email());
        }

        return null;
    }

}