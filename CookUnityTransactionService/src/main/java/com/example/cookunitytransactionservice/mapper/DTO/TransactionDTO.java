package com.example.cookunitytransactionservice.mapper.DTO;

import com.example.cookunitytransactionservice.model.Card;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
import java.util.UUID;

@Data
public class TransactionDTO {
    private UUID id;
    private String customerId;
    private String customerName;
    private String chefId;
    private String chefName;
    private String Status;
    private String type;
    private Card cardDetail;
    private Double amount;
    private String transactionCode;
    private String address;
    private String paymentMethod;

    @Column(name = "created_at")
    private Date createdAT;
}
