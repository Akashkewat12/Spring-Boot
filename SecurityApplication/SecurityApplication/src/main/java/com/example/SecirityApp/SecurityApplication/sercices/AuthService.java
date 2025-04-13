package com.example.SecirityApp.SecurityApplication.sercices;

import com.example.SecirityApp.SecurityApplication.dto.LoginDTO;
import com.example.SecirityApp.SecurityApplication.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public String login(LoginDTO loginDTO) {


        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );
        User user=(User) authentication.getPrincipal();
        return jwtService.generateToken(user);
    }
}
