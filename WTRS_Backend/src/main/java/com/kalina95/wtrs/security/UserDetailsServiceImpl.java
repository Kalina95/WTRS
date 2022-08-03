package com.kalina95.wtrs.security;

import com.kalina95.wtrs.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String ROLE_PREFIX = "ROLE_";
    private final UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username)
                .map(user -> CurrentUser.builder()
                .login(user.getLogin())
                .password(user.getPassword())
                .role(ROLE_PREFIX+user.getRole())
                .build())
        .orElseThrow(()->new UsernameNotFoundException(username));
    }
}
