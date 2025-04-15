package org.banking.controller;

import org.banking.Model.Account;
import org.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/save")
    public ResponseEntity<String> createAccount(@RequestBody Account account) {
        accountService.createAccount(account);
        return ResponseEntity.ok("Account created successfully");
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable Long accountNumber) {
        Account account = accountService.getAccountByNumber(accountNumber);
        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PutMapping("/deposit/{accountNumber}")
    public ResponseEntity<String> deposit(@PathVariable Long accountNumber, @RequestParam double amount) {
        boolean result = accountService.deposit(accountNumber, amount);
        if (result) {
            return ResponseEntity.ok("Deposit successful");
        } else {
            return ResponseEntity.badRequest().body("Account not found or deposit failed");
        }
    }

    @PutMapping("/withdraw/{accountNumber}")
    public ResponseEntity<String> withdraw(@PathVariable Long accountNumber, @RequestParam double amount) {
        boolean result = accountService.withdraw(accountNumber, amount);
        if (result) {
            return ResponseEntity.ok("Withdrawal successful");
        } else {
            return ResponseEntity.badRequest().body("Account not found or insufficient balance");
        }
    }

    @PutMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam Long fromAccountNumber, @RequestParam Long toAccountNumber, @RequestParam double amount) {
        boolean result = accountService.transfer(fromAccountNumber, toAccountNumber, amount);
        if (result) {
            return ResponseEntity.ok("Transfer successful");
        } else {
            return ResponseEntity.badRequest().body("Transfer failed");
        }
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<String> closeAccount(@PathVariable Long accountNumber) {
        boolean result = accountService.closeAccount(accountNumber);
        if (result) {
            return ResponseEntity.ok("Account closed successfully");
        } else {
            return ResponseEntity.badRequest().body("Account not found");
        }
    }
}
