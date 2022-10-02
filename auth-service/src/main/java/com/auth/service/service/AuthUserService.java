package com.auth.service.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.service.dto.AuthUserDto;
import com.auth.service.dto.TokenDto;
import com.auth.service.models.AuthUser;
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

    public AuthUser save(AuthUserDto dto) {
        Optional<AuthUser> user = authUserRepository.findByEmail(dto.getEmail());
        if(user.isPresent())
            return null;
        
        String password = passwordEncoder.encode(dto.getPassword());
        AuthUser authUser = AuthUser.builder()
                .name(dto.getName())
                .first_name(dto.getFirst_name())
                .last_name(dto.getLast_name())
                .cellphone(dto.getCellphone())
                .email(dto.getEmail())
                .password(password)
                .status(dto.getStatus())
				//.create_at(LocalDateTime.now());
        		//.update_at(LocalDateTime.now());
                .build();
                

        authUser.setCreate_at(LocalDateTime.now());
        authUser.setUpdate_at(LocalDateTime.now());
        
        return authUserRepository.save(authUser);
    }

    public TokenDto login(AuthUserDto dto) {
        Optional<AuthUser> user = authUserRepository.findByEmail(dto.getEmail());
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