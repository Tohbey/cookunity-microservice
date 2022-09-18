package com.example.cookunitytransactionservice.model;

import com.example.cookunitytransactionservice.dtos.CardStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class Card extends BaseEntity{
    public Card(Long id){
        super(id);
    }

    private String cardType;
    private String cvv;
    private String expireDate;
    private String pin;
    private String cardNumber;
    private Double amount;
    private String cardHolder;
    private UUID userId;

    @Enumerated(EnumType.STRING)
    private CardStatus status;
}
