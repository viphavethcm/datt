package com.nhatduy.at14.datt.service;

import com.nhatduy.at14.datt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findOneWithAuthoritiesByEmail(email)
                .map(this::createSpringSecurityUser)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }

    public User createSpringSecurityUser(com.nhatduy.at14.datt.domain.User userEntity){
        List<GrantedAuthority> grantedAuthorities =
                Collections.singletonList(new SimpleGrantedAuthority(userEntity.getRole().getRoleName()));
        return new org.springframework.security.core.userdetails.User(
                userEntity.getEmail(), userEntity.getPassword(),grantedAuthorities);
    }

}