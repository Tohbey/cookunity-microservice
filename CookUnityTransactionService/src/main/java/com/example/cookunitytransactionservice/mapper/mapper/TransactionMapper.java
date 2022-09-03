package com.example.cookunitytransactionservice.mapper.mapper;

import com.example.cookunitytransactionservice.mapper.DTO.TransactionDTO;
import com.example.cookunitytransactionservice.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    TransactionDTO transactionToTransactionDTO(Transaction transaction);

    Transaction TransactionDTOToTransaction(TransactionDTO transactionDTO);
}
