package com.ecommerce.rest.services;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.ecommerce.rest.exception.UserNotFoundException;
import com.ecommerce.rest.model.User;
import com.ecommerce.rest.repository.UserRepository;

@Service
public class UserDetail implements UserDetailsService {
    @Autowired    
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String name)throws UserNotFoundException {
        User user = userRepo.findByNameContaining(name);
        if(user==null){
            throw new UserNotFoundException("User: \"" + name + "\" not found");
        }

        Set<GrantedAuthority> authorities = user.getRoles()
            .stream()
            .map((role) -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(name, user.getPassword(), authorities);
    }
}
