package com.example.cookunityuserservice.repository;

import com.example.cookunityuserservice.model.RememberToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface RememberTokenRepository extends JpaRepository<RememberToken, Long> {
    Optional<RememberToken> findRememberTokenByToken(String token);
}
