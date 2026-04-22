package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.User;
import com.example.demo.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }
    public User create(User s) {
        return repo.save(s);
    }

    public List<User> getAll() {
        return repo.findAll();
    }

    public User getById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public User update(Long id, User s) {
        User existing = repo.findById(id).orElseThrow();
        existing.setName(s.getName());
        existing.setEmail(s.getEmail());
        return repo.save(existing);
    }

    public User patch(Long id, User s) {
        User existing = repo.findById(id).orElseThrow();
        if (s.getName() != null) {
             existing.setName(s.getName());
        }
        if(s.getEmail() != null) {
             existing.setEmail(s.getEmail());
        }
        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
    
    public boolean validateUser(String username, String password) {
        // DB check
        return true;
    }
}
