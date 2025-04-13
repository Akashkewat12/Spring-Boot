package com.example.SecirityApp.SecurityApplication.sercices;

import com.example.SecirityApp.SecurityApplication.dto.SignUpDTO;
import com.example.SecirityApp.SecurityApplication.dto.UserDTO;
import com.example.SecirityApp.SecurityApplication.entities.User;
import com.example.SecirityApp.SecurityApplication.exceptions.ResourceNotFoundException;
import com.example.SecirityApp.SecurityApplication.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private  final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User with email "+username+"not found"));
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(()-> new BadCredentialsException("User with id "+userId+" not found"));
    }

    public UserDTO signUp(SignUpDTO signUpDTO) {

        Optional<User> user=userRepository.findByEmail(signUpDTO.getEmail());
        if(user.isPresent()) {
            throw new BadCredentialsException("User with email already exists" +signUpDTO.getEmail());
        }

        User  toBeCreatedUser=modelMapper.map(signUpDTO, User.class);
        toBeCreatedUser.setPassword(passwordEncoder.encode(toBeCreatedUser.getPassword()));

        User  savedUser=userRepository.save(toBeCreatedUser);
        return modelMapper.map(savedUser, UserDTO.class);
    }


}
