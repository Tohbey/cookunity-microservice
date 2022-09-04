package com.example.cookunitytransactionservice.mapper.mapper;

import com.example.cookunitytransactionservice.mapper.DTO.CardDTO;
import com.example.cookunitytransactionservice.model.Card;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CardMapper {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    CardDTO cardToCardDTO(Card card);

    Card CardDTOToCard(CardDTO cardDTO);
}
