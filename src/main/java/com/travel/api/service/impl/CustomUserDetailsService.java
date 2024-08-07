package com.travel.api.service.impl;


import com.travel.api.model.auth.Client;
import com.travel.api.model.auth.Permission;
import com.travel.api.model.auth.Role;
import com.travel.api.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private ClientRepository clientRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public void createUser(Client client) {

        if (!client.getEmail().contains("@") || !client.getEmail().contains(".")) {
            throw new RuntimeException("Invalid e-mail.");
        }
        if (client.getPassword().length() < 8) {
            throw new RuntimeException("Password must contain 8 characters minimum.");
        }

        Optional<Client> userOptional = clientRepository.findClientByEmail(client.getEmail());
        if (userOptional.isPresent()) {
            throw new RuntimeException("E-mail already registered.");
        }

        // Encode client password and make this as new password that saving in databaseopenapi: 3.0.3
        String encodedPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(encodedPassword);

        Role userRole = new Role();
        userRole.setRoleName(Permission.CLIENT);
        client.setRole(userRole);

        client = this.clientRepository.save(client);
    }

    @Override
    public Client loadUserByUsername(String email) throws UsernameNotFoundException {
        return this
                .clientRepository
                .findClientByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user match."));
    }

}