package com.nhatduy.at14.datt.service;

import com.nhatduy.at14.datt.domain.User;
import com.nhatduy.at14.datt.repository.UserRepository;
import com.nhatduy.at14.datt.service.dto.UserDto;
import com.nhatduy.at14.datt.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username).get();
    }

    public boolean isUsernameUnique(String usr){
        return userRepository.existsByUsername(usr);
    }

    public boolean isUserIdValid(String userId){
        return userRepository.existsByUserId(userId);
    }

    public boolean isEmailUnique(String email){
        return userRepository.existsByEmail(email);
    }

    public UserDto getUserByEmail(String email){
        User userEntity = userRepository.findOneWithAuthoritiesByEmail(email).orElse(null);
        return MapperUtils.mapperObject(userEntity,UserDto.class);
    }

}