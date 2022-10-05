package com.auth.service.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.service.dto.AuthUserDto;
import com.auth.service.dto.TokenDto;
import com.auth.service.dto.UserDto;
import com.auth.service.models.User;
import com.auth.service.repositories.AuthUserRepository;
import com.auth.service.security.JwtProvider;

@Service
public class AuthUserService {

    @Autowired
    AuthUserRepository authUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    public User save(UserDto userDto) {
        Optional<User> user = authUserRepository.findByEmail(userDto.getEmail());
        if(user.isPresent())
            return null;
        
        String password = passwordEncoder.encode(userDto.getPassword());
        User authUser = User.builder()
                .name(userDto.getName())
                .first_name(userDto.getFirst_name())
                .last_name(userDto.getLast_name())
                .cellphone(userDto.getCellphone())
                .email(userDto.getEmail())
                .password(password)
                .status(userDto.getStatus())
                .role_id(1L) // Rol User
				//.create_at(LocalDateTime.now());
        		//.update_at(LocalDateTime.now());
                .build();
                

        authUser.setCreate_at(LocalDateTime.now());
        authUser.setUpdate_at(LocalDateTime.now());
        
        return authUserRepository.save(authUser);
    }

    public TokenDto login(AuthUserDto dto) {
        Optional<User> user = authUserRepository.findByEmail(dto.getEmail());
        if(!user.isPresent())
            return null;
        if(passwordEncoder.matches(dto.getPassword(), user.get().getPassword()))
            return new TokenDto(jwtProvider.createToken(user.get()));
        return null;
    }

    public TokenDto validate(String token) {
        if(!jwtProvider.validate(token))
            return null;
        String email = jwtProvider.getEmailFromToken(token);
        if(!authUserRepository.findByEmail(email).isPresent())
            return null;
        return new TokenDto(token);
    }
}