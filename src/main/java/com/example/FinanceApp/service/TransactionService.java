package com.example.FinanceApp.service;

import com.example.FinanceApp.model.Transaction;
import com.example.FinanceApp.model.User;
import com.example.FinanceApp.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public Transaction save(Transaction transaction) {
        return repository.save(transaction);
    }

    public List<Transaction> list(User user, LocalDate start, LocalDate end) {
        return repository.findByUserAndDateBetween(user, start, end);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
