package com.example.bookmyshowjun24.dtos;

import com.example.bookmyshowjun24.models.Booking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMovieResponseDto {
    private Booking booking;
    private ResponseStatus responseStatus;
}
