package com.travel.api.repository;

import com.travel.api.model.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileDB, Long> {
    Optional<FileDB> findFileDBByPath(String path);
}