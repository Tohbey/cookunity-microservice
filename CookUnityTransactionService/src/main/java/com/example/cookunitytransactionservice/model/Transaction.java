package com.example.cookunitytransactionservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class Transaction extends BaseEntity{
    public Transaction(UUID id){
        super(id);
    }

    private String customerId;
    private String chefId;
    private String bookingId;

    @Enumerated(EnumType.STRING)
    private Flag status;

    private String type;
    private Double amount;
    private String transactionCode;
    private String address;
    private String paymentMethod;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
