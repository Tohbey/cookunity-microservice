package com.example.cookunitytransactionservice.service.impl;

import com.example.cookunitytransactionservice.dtos.CardStatus;
import com.example.cookunitytransactionservice.exceptions.NotFoundException;
import com.example.cookunitytransactionservice.mapper.DTO.CardDTO;
import com.example.cookunitytransactionservice.mapper.mapper.CardMapper;
import com.example.cookunitytransactionservice.model.Card;
import com.example.cookunitytransactionservice.repository.CardRepository;
import com.example.cookunitytransactionservice.service.CardService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    public CardServiceImpl(CardRepository cardRepository, CardMapper cardMapper){
        this.cardMapper = cardMapper;
        this.cardRepository = cardRepository;
    }

    @Override
    public CardDTO createCard(Card card) throws Exception {
        Optional<Card> checkCard = this.cardRepository.findCardByCardNumberAndCardType(card.getCardNumber(), card.getCardType());
        if(checkCard.isPresent()){
            throw new Exception("Card already exist with card number: "+checkCard.get().getCardNumber());
        }

        card.setStatus(CardStatus.AVAILABLE);
//        card.setPin(passwordEncoder.encode(card.getPin()));
        Card savedCard = this.cardRepository.save(card);

        CardDTO cardDTO = cardMapper.cardToCardDTO(savedCard);
        cardDTO.setStatus(savedCard.getStatus().getName());

        return cardDTO;
    }

    @Override
    public List<CardDTO> getCardsByUser(Long user) {
        List<Card> cards = cardRepository.findCardsByUserId(user);
        return cards.stream().map(
                card -> {
                    CardDTO cardDTO = cardMapper.cardToCardDTO(card);
                    cardDTO.setStatus(card.getStatus().getName());

                    return cardDTO;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public CardDTO credit(Long cardId, Double amount) {
        return null;
    }

    @Override
    public CardDTO debit() {
        return null;
    }

    @Override
    public CardDTO blockCard(Long id) {
        Optional<Card> card = this.cardRepository.findById(id);
        card.get().setCardType(CardStatus.BLOCKED.getName());

        return saveAndReturnDTO(card.get());
    }

    @Override
    public Optional<CardDTO> getCard(Long id) {
        Optional<Card> card = this.cardRepository.findById(id);
        if(card.isEmpty()){
            throw new NotFoundException("Card Not Found. for ID value " + id);
        }

        Optional<CardDTO> cardDTO = card.map(cardMapper::cardToCardDTO)
                .map(cardDTO1 -> {
                    cardDTO1.setStatus(card.get().getStatus().getName());

                    return cardDTO1;
                });

        return cardDTO;
    }

    private CardDTO saveAndReturnDTO(Card card){
        Card savedCard = cardRepository.save(card);

        CardDTO returnDTO = cardMapper.cardToCardDTO(card);
        returnDTO.setStatus(savedCard.getStatus().getName());

        return returnDTO;
    }
}
