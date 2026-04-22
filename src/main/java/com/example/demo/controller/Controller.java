package com.example.demo.controller;

import java.util.List;
import com.example.demo.service.UserService;
import com.example.demo.User;
import com.example.demo.dto.LoginRequest;
import com.example.demo.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class Controller {
    
    @Autowired
    private JwtUtil jwtUtil;

    private final UserService service;

    public Controller(UserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        boolean isValid = service.validateUser(
                request.getUsername(),
                request.getPassword()
        );

        if (!isValid) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(request.getUsername());
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

    @PatchMapping("/{id}")
    public String patch(@PathVariable Long id, @RequestBody User s) {
        service.patch(id, s);
        return "Patch updated Successfully!";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "User deleted Successfully!";
    }
}