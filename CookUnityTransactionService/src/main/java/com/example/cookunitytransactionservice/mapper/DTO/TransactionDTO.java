package com.example.cookunitytransactionservice.mapper.DTO;

import com.example.cookunitytransactionservice.model.Card;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class TransactionDTO {
    private Long id;
    private String customerId;
    private String customerName;
    private String chefId;
    private String chefName;
    private String Status;
    private String type;
    private CardDTO card;
    private Double amount;
    private String transactionCode;
    private String address;
    private String paymentMethod;

    @Column(name = "created_at")
    private Date createdAT;
}
