package com.example.cookunityuserservice.repository;

import com.example.cookunityuserservice.model.SecretQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SecretQuestionRepository extends JpaRepository<SecretQuestion, UUID> {
    Optional<SecretQuestion> findSecretQuestionByQuestion(String question);
}
