package com.college.users;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class JPAUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    public JPAUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findById(username)
                .orElseThrow(()->new UsernameNotFoundException("Username not found"));
    }
}
