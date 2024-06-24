package com.example.bookmyshowjun24;

import com.example.bookmyshowjun24.controllers.UserController;
import com.example.bookmyshowjun24.dtos.SignUpRequestDto;
import com.example.bookmyshowjun24.dtos.SignUpResponseDto;
import com.example.bookmyshowjun24.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.sound.midi.Soundbank;

@SpringBootApplication
@EnableJpaAuditing
public class BookMyShowJun24Application implements CommandLineRunner {
    @Autowired
    private UserController userController;

    public static void main(String[] args) {
        SpringApplication.run(BookMyShowJun24Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        SignUpRequestDto requestDto = new SignUpRequestDto();
        requestDto.setName("Sasi");
        requestDto.setEmail("sasi2@gmail.com");
        requestDto.setPassword("scaler");

        SignUpResponseDto responseDto = userController.signUp(requestDto);
    }
}
