package com.example.cookunitytransactionservice.service.impl;

import com.example.cookunitytransactionservice.mapper.DTO.CardDTO;
import com.example.cookunitytransactionservice.model.Card;
import com.example.cookunitytransactionservice.service.CardService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CardServiceImpl implements CardService {
    @Override
    public CardDTO createCard(Card card) {
        return null;
    }

    @Override
    public List<CardDTO> getCardsByUser(String user) {
        return null;
    }

    @Override
    public CardDTO credit(UUID cardId, Double amount) {
        return null;
    }

    @Override
    public CardDTO debit() {
        return null;
    }

    @Override
    public CardDTO blockCard() {
        return null;
    }

    @Override
    public Optional<Card> getCard(UUID id) {
        return Optional.empty();
    }
}
