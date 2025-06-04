package com.example.FinanceApp.controller;

import com.example.FinanceApp.model.Transaction;
import com.example.FinanceApp.model.User;
import com.example.FinanceApp.service.TransactionService;
import com.example.FinanceApp.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService service;
    private final UserService userService;

    public TransactionController(TransactionService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping
    public Transaction create(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails,
                              @RequestBody Transaction transaction) {
        User user = userService.findByUsername(userDetails.getUsername());
        transaction.setUser(user);
        return service.save(transaction);
    }

    @GetMapping
    public List<Transaction> list(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails,
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        User user = userService.findByUsername(userDetails.getUsername());
        return service.list(user, start, end);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
