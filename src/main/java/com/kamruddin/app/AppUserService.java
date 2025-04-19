package com.kamruddin.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;

    public AppUser createUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    public AppUser getUserById(Long id) {
        return appUserRepository.findById(id).orElse(null);
    }
    public AppUser getUserByUserName(String userName) {
        return appUserRepository.findByUserName(userName);
    }
    public AppUser getUserByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }
    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }
}
