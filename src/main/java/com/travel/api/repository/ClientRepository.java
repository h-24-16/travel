package com.travel.api.repository;

import com.travel.api.model.auth.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource(collectionResourceRel = "client", path = "client")
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findClientById(Long id);
    Optional<Client> findClientByEmail(String email);
}