package com.example.cookunitytransactionservice.service;

import com.example.cookunitytransactionservice.mapper.DTO.CardDTO;
import com.example.cookunitytransactionservice.model.Card;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CardService {
    CardDTO createCard(Card card);

    List<CardDTO> getCardsByUser(String user);

    Optional<Card> getCard(UUID id);

    CardDTO credit(UUID cardId, Double amount);

    CardDTO debit();

    CardDTO blockCard();
}
