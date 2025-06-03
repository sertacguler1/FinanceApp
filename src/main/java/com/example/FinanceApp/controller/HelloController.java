package com.example.FinanceApp.controller;


import com.example.FinanceApp.model.TestEntity;
import com.example.FinanceApp.repository.TestEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private TestEntityRepository repository;

    @GetMapping("/test-db")
    public String testDb() {
        TestEntity entity = new TestEntity();
        entity.setMessage("Hello Database!");
        repository.save(entity);
        return "Saved entity with message: " + entity.getMessage();
    }
}
