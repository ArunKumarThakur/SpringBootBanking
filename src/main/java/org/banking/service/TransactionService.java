package org.banking.service;

import org.banking.Model.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction recordTransaction(Transaction transaction);

    List<Transaction> getAllTransactions();

    List<Transaction> getTransactionsByAccountNumber(Long accountNumber);
}
