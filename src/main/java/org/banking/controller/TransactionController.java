package org.banking.controller;

import org.banking.Model.Transaction;
import org.banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<String> recordTransaction(@RequestBody Transaction transaction) {
        transactionService.recordTransaction(transaction);
        return ResponseEntity.ok("Transaction recorded successfully");
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/account/{accountNumber}")
    public List<Transaction> getTransactionsByAccountNumber(@PathVariable Long accountNumber) {
        return transactionService.getTransactionsByAccountNumber(accountNumber);
    }
}
