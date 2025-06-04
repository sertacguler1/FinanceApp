package com.example.FinanceApp.repository;

import com.example.FinanceApp.model.Budget;
import com.example.FinanceApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.YearMonth;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Optional<Budget> findByUserAndMonth(User user, YearMonth month);
}
