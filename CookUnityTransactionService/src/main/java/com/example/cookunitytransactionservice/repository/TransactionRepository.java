package com.example.cookunitytransactionservice.repository;

import com.example.cookunitytransactionservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findTransactionsByCustomerId(String customerId);

    List<Transaction> findTransactionsByChefId(String chefId);
}
