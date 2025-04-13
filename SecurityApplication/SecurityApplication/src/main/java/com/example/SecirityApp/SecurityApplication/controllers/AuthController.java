package com.example.SecirityApp.SecurityApplication.controllers;

import com.example.SecirityApp.SecurityApplication.dto.LoginDTO;
import com.example.SecirityApp.SecurityApplication.dto.SignUpDTO;
import com.example.SecirityApp.SecurityApplication.dto.UserDTO;
import com.example.SecirityApp.SecurityApplication.sercices.AuthService;
import com.example.SecirityApp.SecurityApplication.sercices.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO) {
     UserDTO  userDTO=userService.signUp(signUpDTO);
     return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    private ResponseEntity<String> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request,
                                         HttpServletResponse response) {

        String token = authService.login(loginDTO);

        Cookie cookie=new Cookie("token", token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }

}
