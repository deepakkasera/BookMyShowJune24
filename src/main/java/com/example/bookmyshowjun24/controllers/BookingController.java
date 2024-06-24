package com.example.bookmyshowjun24.controllers;

import com.example.bookmyshowjun24.dtos.BookMovieRequestDto;
import com.example.bookmyshowjun24.dtos.BookMovieResponseDto;
import com.example.bookmyshowjun24.dtos.ResponseStatus;
import com.example.bookmyshowjun24.models.Booking;
import com.example.bookmyshowjun24.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookMovieResponseDto bookMovie(BookMovieRequestDto requestDto) {
        BookMovieResponseDto responseDto = new BookMovieResponseDto();
        try {
            Booking booking = bookingService.bookMovie(
                    requestDto.getUserId(),
                    requestDto.getShowId(),
                    requestDto.getShowSeatIds()
            );

            responseDto.setBooking(booking);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDto;
    }
}
