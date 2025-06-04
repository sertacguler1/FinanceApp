package com.example.FinanceApp.controller;

import com.example.FinanceApp.model.Budget;
import com.example.FinanceApp.model.User;
import com.example.FinanceApp.service.BudgetService;
import com.example.FinanceApp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetService service;
    private final UserService userService;

    public BudgetController(BudgetService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping
    public Budget create(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails,
                         @RequestParam String month,
                         @RequestParam Double limit) {
        User user = userService.findByUsername(userDetails.getUsername());
        Budget budget = new Budget();
        budget.setUser(user);
        budget.setMonth(YearMonth.parse(month));
        budget.setLimitAmount(limit);
        return service.save(budget);
    }

    @GetMapping("/{month}")
    public ResponseEntity<Budget> get(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails,
                                      @PathVariable String month) {
        User user = userService.findByUsername(userDetails.getUsername());
        return service.find(user, YearMonth.parse(month))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
