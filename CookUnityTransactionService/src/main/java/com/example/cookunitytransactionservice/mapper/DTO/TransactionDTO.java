package com.example.cookunitytransactionservice.mapper.DTO;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
import java.util.UUID;

@Data
public class TransactionDTO {
    private UUID id;
    private String customerId;
    private String customerFullName;
    private String chefId;
    private String chefFullName;
    private String Status;
    private String type;
    private Double amount;
    private String transactionCode;
    private String address;
    private String paymentMethod;

    @Column(name = "created_at")
    private Date createdAT;
}
