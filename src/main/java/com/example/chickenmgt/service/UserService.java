package com.example.chickenmgt.service;

import com.example.chickenmgt.model.User;
import com.example.chickenmgt.model.Village;
import com.example.chickenmgt.repository.UserRepository;
import com.example.chickenmgt.repository.VillageRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final VillageRepository villageRepository;

    public UserService(UserRepository userRepository, VillageRepository villageRepository) {
        this.userRepository = userRepository;
        this.villageRepository = villageRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User save(User user, String villageIdentifier) {
        if (user.getId() == null && userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists: " + user.getEmail());
        }

        if (villageIdentifier != null) {
            Village village = villageRepository.findByCode(villageIdentifier)
                    .orElseGet(() -> villageRepository.findByName(villageIdentifier)
                            .orElseThrow(() -> new RuntimeException("Village not found: " + villageIdentifier)));
            user.setVillage(village);
        }

        return userRepository.save(user);
    }

    public boolean checkEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public List<User> findByProvince(String identifier) {
        return userRepository.findByProvince(identifier);
    }

    public List<User> findByDistrict(String identifier) {
        return userRepository.findByDistrict(identifier);
    }

    public List<User> findBySector(String identifier) {
        return userRepository.findBySector(identifier);
    }

    public List<User> findByCell(String identifier) {
        return userRepository.findByCell(identifier);
    }

    public List<User> findByVillage(String identifier) {
        return userRepository.findByVillage(identifier);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
