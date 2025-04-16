package org.banking.service;

import org.banking.Model.Account;
import org.banking.Model.Customer;
import org.banking.repository.AccountRepository;
import org.banking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Account createAccount(Account account) {
        Customer customer = account.getCustomer();

        // Check if customer is new (transient) — customerId should be null
        if (customer.getCustomerId() == null) {
            customerRepository.save(customer);
        }

        return accountRepository.save(account);
    }

    @Override
    public Account getAccountByNumber(Long accountNumber) {
        Optional<Account> acc = accountRepository.findById(accountNumber);
        return acc.orElse(null);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public boolean deposit(Long accountNumber, double amount) {
        Account account = getAccountByNumber(accountNumber);
        if (account == null) {
            return false;
        }
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        return true;
    }

    @Override
    public boolean withdraw(Long accountNumber, double amount) {
        Account account = getAccountByNumber(accountNumber);
        if (account == null || account.getBalance() < amount) {
            return false;
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
        return true;
    }

    @Override
    public boolean transfer(Long fromAccountNumber, Long toAccountNumber, double amount) {
        Account senderAccount = getAccountByNumber(fromAccountNumber);
        Account receiverAccount = getAccountByNumber(toAccountNumber);

        if (senderAccount == null || receiverAccount == null) {
            return false;
        }

        if (senderAccount.getBalance() < amount) {
            return false;
        }

        senderAccount.setBalance(senderAccount.getBalance() - amount);
        receiverAccount.setBalance(receiverAccount.getBalance() + amount);

        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);
        return true;
    }

    @Override
    public boolean closeAccount(Long accountNumber) {
        Account account = getAccountByNumber(accountNumber);
        if (account == null) return false;
        accountRepository.delete(account);
        return true;
    }

    @Override
    public double getAmount(Long accountNumber) {
        Account account = getAccountByNumber(accountNumber);
        return account == null ? 0.0 : account.getBalance();
    }

    @Override
    public boolean validateBankAccount(Long accountNumber) {
        return accountRepository.existsById(accountNumber);
    }
}
