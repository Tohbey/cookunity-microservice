package com.example.cookunitytransactionservice.controller;

import com.example.cookunitytransactionservice.dtos.ResponseObject;
import com.example.cookunitytransactionservice.mapper.DTO.CardDTO;
import com.example.cookunitytransactionservice.mapper.DTO.CardListDTO;
import com.example.cookunitytransactionservice.mapper.DTO.TransactionDTO;
import com.example.cookunitytransactionservice.model.Card;
import com.example.cookunitytransactionservice.resource.General;
import com.example.cookunitytransactionservice.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.UUID;

@RestController
@RequestMapping(CardController.BASE_URL)
public class CardController {
    private final CardService cardService;
    public static final String BASE_URL = "/api/v1/card";

    public CardController(CardService cardService){
        this.cardService = cardService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{cardId}")
    public ResponseEntity<ResponseObject<CardDTO>> getCardById(@PathVariable("cardId") UUID cardId){
        ResponseObject<CardDTO> object = new ResponseObject<>();
        try {
            CardDTO cardDTO = cardService.getCard(cardId).get();
            object.setData(Collections.singletonList(cardDTO));
            object.setMessage(General.RETRIEVED);
            object.setValid(true);
        }catch (Exception e){
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(object);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{user}")
    public ResponseEntity<ResponseObject<CardListDTO>> getCardsByUser(@PathVariable("user") UUID user){
        ResponseObject<CardListDTO> object = new ResponseObject<>();
        try {
            CardListDTO cards = new CardListDTO(cardService.getCardsByUser(user));
            object.setData(Collections.singletonList(cards));
            object.setMessage(General.RETRIEVED);
            object.setValid(true);
        }catch (Exception e){
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(object);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseObject<CardDTO>> saveCard(@RequestBody Card card){
        ResponseObject<CardDTO> object = new ResponseObject<>();
        try {
            CardDTO cardDTO = cardService.createCard(card);
            object.setData(Collections.singletonList(cardDTO));
            object.setMessage(General.CREATED);
            object.setValid(true);
        }catch (Exception e){
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return new ResponseEntity<>(object, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/block/{cardId}")
    public ResponseEntity<ResponseObject<CardDTO>> block(@PathVariable("cardId") UUID card){
        ResponseObject<CardDTO> object = new ResponseObject<>();
        try {
            CardDTO cardDTO = cardService.blockCard(card);
            object.setData(Collections.singletonList(cardDTO));
            object.setMessage(General.CARD_BLOCKED);
            object.setValid(true);
        }catch (Exception e){
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return new ResponseEntity<>(object, HttpStatus.CREATED);
    }
}
