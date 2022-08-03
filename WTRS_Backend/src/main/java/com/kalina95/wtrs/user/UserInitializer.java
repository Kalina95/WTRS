package com.kalina95.wtrs.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInitializer {

    public UserInitializer(UserRepository repository, PasswordEncoder encoder) {
        User admin = User.builder()
                .login("admin")
                .password(encoder.encode("admin"))
                .role("ADMIN")
                .build();

        repository.save(admin);
    }
}
