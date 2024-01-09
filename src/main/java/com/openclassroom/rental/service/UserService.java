package com.openclassroom.rental.service;

import com.openclassroom.rental.dto.RegisterDto;
import com.openclassroom.rental.dto.UserDto;

public interface UserService {

    UserDto saveUser(RegisterDto registerDto);
}
