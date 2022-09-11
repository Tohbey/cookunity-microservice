package com.example.cookunitytransactionservice.controller;

import com.example.cookunitytransactionservice.dtos.ResponseObject;
import com.example.cookunitytransactionservice.mapper.DTO.TransactionDTO;
import com.example.cookunitytransactionservice.mapper.DTO.TransactionListDTO;
import com.example.cookunitytransactionservice.model.Transaction;
import com.example.cookunitytransactionservice.resource.General;
import com.example.cookunitytransactionservice.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(TransactionController.BASE_URL)
public class TransactionController {
    public static final String BASE_URL = "/api/v1/transaction";
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping("/status")
    public String status(){
        return "Transaction Controller is working";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{transactionId}")
    public ResponseEntity<ResponseObject<TransactionDTO>> getTransactionById(@PathVariable("transactionId")UUID transactionId){
        ResponseObject<TransactionDTO> object = new ResponseObject<>();
        try {
            Optional<TransactionDTO> transaction = transactionService.getTransaction(transactionId);
            object.setData(Collections.singletonList(transaction.get()));
            object.setValid(true);
            object.setMessage(General.RETRIEVED);
        }catch (Exception e){
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(object);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customer/{customerId}")
    public ResponseEntity<ResponseObject<TransactionListDTO>> getTransactionForCustomer(@PathVariable("customerId") String customerId){
        ResponseObject<TransactionListDTO> object = new ResponseObject<>();
        try {
            List<TransactionDTO> transactions = transactionService.getTransactionsForCustomer(customerId);
            object.setData(Collections.singletonList(new TransactionListDTO(transactions)));
            object.setValid(true);
            object.setMessage(General.RETRIEVED);
        }catch (Exception e){
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(object);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/chef/{chefId}")
    public ResponseEntity<ResponseObject<TransactionListDTO>> getTransactionForChef(@PathVariable("chefId") String chefId){
        ResponseObject<TransactionListDTO> object = new ResponseObject<>();
        try {
            List<TransactionDTO> transactions = transactionService.getTransactionsForChef(chefId);
            object.setData(Collections.singletonList(new TransactionListDTO(transactions)));
            object.setValid(true);
            object.setMessage(General.RETRIEVED);
        }catch (Exception e){
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(object);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseObject<TransactionDTO>> createTransaction(@RequestBody Transaction transaction){
        ResponseObject<TransactionDTO> object = new ResponseObject<>();
        try {
            TransactionDTO transactionDTO = transactionService.saveTransaction(transaction);
            object.setData(Collections.singletonList(transactionDTO));
            object.setValid(true);
            object.setMessage(General.RETRIEVED);
        }catch (Exception e){
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return new ResponseEntity<>(object, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{transactionId}")
    public ResponseEntity<ResponseObject<TransactionDTO>> updateTransaction(@RequestBody Transaction transaction, @PathVariable("transactionId") UUID transactionId){
        ResponseObject<TransactionDTO> object = new ResponseObject<>();
        try {
            Optional<TransactionDTO> transactionDTO = transactionService.updateTransaction(transactionId,transaction);
            object.setData(Collections.singletonList(transactionDTO.get()));
            object.setValid(true);
            object.setMessage(General.RETRIEVED);
        }catch (Exception e){
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return new ResponseEntity<>(object, HttpStatus.CREATED);
    }
}
