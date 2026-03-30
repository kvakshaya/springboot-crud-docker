package com.example.demo.controller;

import java.util.List;
import com.example.demo.service.UserService;
import com.example.demo.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class Controller {
    private final UserService service;

    public Controller(UserService service) {
        this.service = service;
    }

    @PostMapping
    public String create(@RequestBody User s) {
        service.create(s);
        return "User added Successfully!";
    }

    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody User s) {
        service.update(id, s);
        return "User updated Successfully!";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "User deleted Successfully!";
    }
}