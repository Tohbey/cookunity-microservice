package com.example.cookunitytransactionservice.mapper.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardListDTO {
    List<CardDTO> cards;
}
