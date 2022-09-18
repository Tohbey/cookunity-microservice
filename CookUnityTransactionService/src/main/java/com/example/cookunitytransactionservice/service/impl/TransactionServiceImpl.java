package com.example.cookunitytransactionservice.service.impl;

import com.example.cookunitytransactionservice.exceptions.NotFoundException;
import com.example.cookunitytransactionservice.mapper.DTO.TransactionDTO;
import com.example.cookunitytransactionservice.mapper.mapper.TransactionMapper;
import com.example.cookunitytransactionservice.model.Transaction;
import com.example.cookunitytransactionservice.repository.TransactionRepository;
import com.example.cookunitytransactionservice.service.CardService;
import com.example.cookunitytransactionservice.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    private final CardService cardService;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper, CardService cardService){
        this.transactionMapper = transactionMapper;
        this.transactionRepository = transactionRepository;
        this.cardService = cardService;
    }

    @Override
    public TransactionDTO saveTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public Optional<TransactionDTO> getTransaction(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isEmpty()) {
            throw new NotFoundException("Transaction Not Found. for ID value " + id);
        }

        Optional<TransactionDTO> transactionDTO = transaction.map(transactionMapper::transactionToTransactionDTO)
                .map(transactionDTO1 -> {
                        transactionDTO1.setCard(cardService.getCard(transaction.get().getCardDetail()).get());

                        return transactionDTO1;
                });
        return transactionDTO;
    }

    @Override
    public List<TransactionDTO> getTransactionsForCustomer(String customerId) {
        List<Transaction> transactions = transactionRepository.findTransactionsByCustomerId(customerId);
        return transactions.stream().map(
                transaction -> {
                    TransactionDTO transactionDTO = transactionMapper.transactionToTransactionDTO(transaction);
                    transactionDTO.setCard(cardService.getCard(transaction.getCardDetail()).get());

                    return transactionDTO;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> getTransactionsForChef(String chefId) {
        List<Transaction> transactions = transactionRepository.findTransactionsByChefId(chefId);
        return transactions.stream().map(
                transaction -> {
                    TransactionDTO transactionDTO = transactionMapper.transactionToTransactionDTO(transaction);
                    transactionDTO.setCard(cardService.getCard(transaction.getCardDetail()).get());

                    return transactionDTO;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public Optional<TransactionDTO> updateTransaction(Long id, Transaction transaction) {
        Optional<Transaction> currentTransaction = transactionRepository.findById(id);
        if (currentTransaction.isEmpty()) {
            throw new NotFoundException("User Not Found. for ID value" + id);
        }

        return currentTransaction.map(transaction1 -> {
            if(transaction.getStatus() != null){
                transaction1.setStatus(transaction.getStatus());
            }

            return saveAndReturnDTO(transaction1);
        });
    }

    private TransactionDTO saveAndReturnDTO(Transaction transaction) {
        Transaction savedTransaction = transactionRepository.save(transaction);

        TransactionDTO returnDTO = transactionMapper.transactionToTransactionDTO(savedTransaction);
        returnDTO.setCard(cardService.getCard(savedTransaction.getCardDetail()).get());
        return returnDTO;
    }
}
