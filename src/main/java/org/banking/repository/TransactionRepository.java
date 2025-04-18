package org.banking.repository;

import org.banking.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    public List<Transaction> findByAccountAccountNumber(Long accountNumber);
}
