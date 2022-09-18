package com.example.cookunitytransactionservice.mapper.DTO;

import com.example.cookunitytransactionservice.dtos.CardStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;

@Data
public class CardDTO {
    private Long id;
    private String cardType;
    private String cvv;
    private String expireDate;
    private Double amount;
    private String cardHolder;
    private String status;
}
