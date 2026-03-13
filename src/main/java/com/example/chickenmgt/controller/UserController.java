package com.example.chickenmgt.controller;

import com.example.chickenmgt.model.User;
import com.example.chickenmgt.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public User create(@RequestBody User user, @RequestParam(required = false) String villageIdentifier) {
        return userService.save(user, villageIdentifier);
    }

    @GetMapping("/province/{identifier}")
    public List<User> getByProvince(@PathVariable String identifier) {
        return userService.findByProvince(identifier);
    }

    @GetMapping("/district/{identifier}")
    public List<User> getByDistrict(@PathVariable String identifier) {
        return userService.findByDistrict(identifier);
    }

    @GetMapping("/sector/{identifier}")
    public List<User> getBySector(@PathVariable String identifier) {
        return userService.findBySector(identifier);
    }

    @GetMapping("/cell/{identifier}")
    public List<User> getByCell(@PathVariable String identifier) {
        return userService.findByCell(identifier);
    }

    @GetMapping("/village/{identifier}")
    public List<User> getByVillage(@PathVariable String identifier) {
        return userService.findByVillage(identifier);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user, @RequestParam(required = false) String villageIdentifier) {
        User existing = userService.findById(id);
        user.setId(existing.getId());
        return ResponseEntity.ok(userService.save(user, villageIdentifier));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
