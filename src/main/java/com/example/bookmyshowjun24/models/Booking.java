package com.example.bookmyshowjun24.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel { //Ticket
    @ManyToMany
    private List<ShowSeat> showSeats;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;

    private int amount; // 20050 => 200.50

    @OneToMany
    private List<Payment> payments;
}

/*

   1           M
Booking ---- ShowSeat => M:M
   M            1

  1           1
Booking ---- User => M:1
  M           1

   1           M
Booking ---- Payment => 1:M
   1           1

 */
