package com.example.FinanceApp.service;

import com.example.FinanceApp.model.Budget;
import com.example.FinanceApp.model.User;
import com.example.FinanceApp.repository.BudgetRepository;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.Optional;

@Service
public class BudgetService {
    private final BudgetRepository repository;

    public BudgetService(BudgetRepository repository) {
        this.repository = repository;
    }

    public Budget save(Budget budget) {
        return repository.save(budget);
    }

    public Optional<Budget> find(User user, YearMonth month) {
        return repository.findByUserAndMonth(user, month);
    }
}
