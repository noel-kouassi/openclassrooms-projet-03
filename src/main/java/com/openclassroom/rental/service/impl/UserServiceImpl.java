package com.openclassroom.rental.service.impl;

import com.openclassroom.rental.dto.LoginDto;
import com.openclassroom.rental.dto.RegisterDto;
import com.openclassroom.rental.dto.UserDto;
import com.openclassroom.rental.entity.Role;
import com.openclassroom.rental.entity.User;
import com.openclassroom.rental.exception.RentalException;
import com.openclassroom.rental.exception.ResourceNotFoundException;
import com.openclassroom.rental.repository.RoleRepository;
import com.openclassroom.rental.repository.UserRepository;
import com.openclassroom.rental.security.JwtTokenProvider;
import com.openclassroom.rental.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.openclassroom.rental.util.DateFormatter.formatDate;

@Service
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.modelMapper = modelMapper;
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getLogin(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public String saveUser(RegisterDto registerDto) {

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new RentalException(HttpStatus.BAD_REQUEST, "This email is already in database");
        }
        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found in database with name ROLE_USER"));
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);
        return "User successfully created !";
    }

    @Override
    public UserDto getUserFromToken(HttpServletRequest httpServletRequest) {

        String token = jwtTokenProvider.getTokenFromRequest(httpServletRequest);
        if (token != null && !token.equalsIgnoreCase("jwt")) {

            if (jwtTokenProvider.validateToken(token)) {
                String login = jwtTokenProvider.getUsername(token);
                User user = userRepository.findByEmail(login)
                        .orElseThrow(() -> new ResourceNotFoundException(String.format("User not found with email %s", login)));
                UserDto userDto = modelMapper.map(user, UserDto.class);
                userDto.setCreatedAt(formatDate(user.getCreatedAt()));
                userDto.setUpdatedAt(formatDate(user.getUpdatedAt()));
                return userDto;
            }
        }
        return null;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        UserDto userDto = modelMapper.map(user, UserDto.class);
        userDto.setCreatedAt(formatDate(user.getCreatedAt()));
        userDto.setUpdatedAt(formatDate(user.getUpdatedAt()));
        return userDto;
    }
}
