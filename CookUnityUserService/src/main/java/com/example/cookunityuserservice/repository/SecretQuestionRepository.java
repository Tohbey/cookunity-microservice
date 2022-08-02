package com.example.cookunityuserservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SecretQuestionRepository extends JpaRepository<SecretQuestionRepository, UUID> {
}
