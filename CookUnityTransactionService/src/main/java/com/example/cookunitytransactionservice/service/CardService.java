package com.example.cookunitytransactionservice.service;

import com.example.cookunitytransactionservice.mapper.DTO.CardDTO;
import com.example.cookunitytransactionservice.model.Card;

import java.util.List;
import java.util.Optional;

public interface CardService {
    CardDTO createCard(Card card) throws Exception;

    List<CardDTO> getCardsByUser(Long user);

    Optional<CardDTO> getCard(Long id);

    CardDTO credit(Long cardId, Double amount);

    CardDTO debit();

    CardDTO blockCard(Long id);
}
