package org.banking.service;

import org.banking.Model.Account;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account);

    Account getAccountByNumber(Long accountNumber);

    List<Account> getAllAccounts();

    boolean deposit(Long accountNumber, double amount);

    boolean withdraw(Long accountNumber, double amount);

    boolean transfer(Long fromAccountNumber, Long toAccountNumber, double amount);

    boolean closeAccount(Long accountNumber);

    double getAmount(Long accountNumber);

    boolean validateBankAccount(Long accountNumber);
}
