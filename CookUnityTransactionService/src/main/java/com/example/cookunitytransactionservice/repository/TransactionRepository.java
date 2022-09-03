package com.example.cookunitytransactionservice.repository;

import com.example.cookunitytransactionservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
