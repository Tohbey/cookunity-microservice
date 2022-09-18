package com.example.cookunitytransactionservice.model;

import com.example.cookunitytransactionservice.dtos.TransactionStatus;
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
    public Transaction(Long id){
        super(id);
    }

    private String customerId;
    private String customerName;
    private String chefId;
    private String chefName;
    private String bookingId;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    private String type;
    private Double amount;
    private String transactionCode;
    private String address;
    private String paymentMethod;

    private Long cardDetail;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
