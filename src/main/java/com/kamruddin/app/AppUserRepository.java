package com.kamruddin.app;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUserName(String userName);
    AppUser findByEmail(String email);
    AppUser findByPhoneNumber(String phoneNumber);
    AppUser findByAddress(String address);
}
