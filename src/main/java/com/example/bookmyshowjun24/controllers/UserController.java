package com.example.bookmyshowjun24.controllers;

import com.example.bookmyshowjun24.dtos.ResponseStatus;
import com.example.bookmyshowjun24.dtos.SignUpRequestDto;
import com.example.bookmyshowjun24.dtos.SignUpResponseDto;
import com.example.bookmyshowjun24.models.User;
import com.example.bookmyshowjun24.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignUpResponseDto signUp(SignUpRequestDto requestDto) {
        SignUpResponseDto responseDto = new SignUpResponseDto();

        try {
            User user = userService.signUp(requestDto.getName(),
                    requestDto.getEmail(),
                    requestDto.getPassword());

            responseDto.setUser(user);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDto;
    }
}
