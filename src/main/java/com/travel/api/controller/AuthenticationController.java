package com.travel.api.controller;

import com.travel.api.dto.AuthenticationDto;
import com.travel.api.model.auth.Client;
import com.travel.api.security.JwtService;
import com.travel.api.service.impl.CustomUserDetailsService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "authentication")
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