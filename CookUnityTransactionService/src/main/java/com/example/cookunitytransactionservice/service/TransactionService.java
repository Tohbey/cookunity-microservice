package com.example.cookunitytransactionservice.service;

import com.example.cookunitytransactionservice.mapper.DTO.TransactionDTO;
import com.example.cookunitytransactionservice.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    TransactionDTO saveTransaction(Transaction transaction);

    Optional<TransactionDTO> getTransaction(Long id);

    List<TransactionDTO> getTransactionsForCustomer(String customerId);

    List<TransactionDTO> getTransactionsForChef(String chefId);

    Optional<TransactionDTO> updateTransaction(Long id, Transaction transaction);
}
