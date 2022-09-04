package com.example.cookunitytransactionservice.service;

import com.example.cookunitytransactionservice.mapper.DTO.TransactionDTO;
import com.example.cookunitytransactionservice.model.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionService {

    TransactionDTO saveTransaction(Transaction transaction);

    Optional<TransactionDTO> getTransaction(UUID id);

    List<TransactionDTO> getTransactionsForCustomer(String customerId);

    List<TransactionDTO> getTransactionsForChef(String chefId);

    Optional<TransactionDTO> updateTransaction(UUID id, Transaction transaction);
}
