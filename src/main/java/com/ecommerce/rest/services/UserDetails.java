package com.ecommerce.rest.services;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.ecommerce.rest.exception.UserNotFoundException;
import com.ecommerce.rest.model.User;
import com.ecommerce.rest.repository.UserRepository;

@Service
public class UserDetail implements UserDetailsService {
@Autowired    
UserRepository userRepo;    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
        User user = userRepo.findByUserNameOrEmail(username, username);
        if(user==null){
            throw new UsernameNotFoundException("User not exists by Username");
        }
           
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),authorities);
    }
}
