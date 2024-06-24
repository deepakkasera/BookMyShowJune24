package com.example.bookmyshowjun24.services;

import com.example.bookmyshowjun24.exceptions.ShowNotFoundException;
import com.example.bookmyshowjun24.exceptions.UserNotFoundException;
import com.example.bookmyshowjun24.models.*;
import com.example.bookmyshowjun24.repositories.ShowRepository;
import com.example.bookmyshowjun24.repositories.ShowSeatRepository;
import com.example.bookmyshowjun24.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private PriceCalculatorService priceCalculatorService;

    public BookingService(UserRepository userRepository,
                          ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository,
                          PriceCalculatorService priceCalculatorService) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalculatorService = priceCalculatorService;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userId, Long showId, List<Long> showSeatIds) throws UserNotFoundException, ShowNotFoundException {
        /*
        1. Get the user with the userId.
        2. Get the show with the showId.
        3. Get the list of show seats with the showSeatIds.
        4. Check if all the seats are available or not.
        5. If yes, proceed with the booking.
        6. If not, throw an exception.
        ----------TAKE A LOCK---------
        7. Check if all the seats are available or not.
        8. Change the seat status to BLOCKED.
        ----------RELEASE THE LOCK----------
        9. Create the booking and move the payment page.
         */

        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User with id" + userId + " doesn't exist");
        }

        User user = optionalUser.get();

        Optional<Show> optionalShow = showRepository.findById(showId);

        if (optionalShow.isEmpty()) {
            throw new ShowNotFoundException("Show with id: " + showId + " doesn't exist");
        }

        Show show = optionalShow.get();

        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        for (ShowSeat showSeat : showSeats) {
            if (!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) {
                throw new RuntimeException("ShowSeat with id" + showSeat.getId() + " isn't available");
            }
        }

        for (ShowSeat showSeat : showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            //change the status in DB as well.
            showSeatRepository.save(showSeat);
        }

        //Create the booking and Move to the payment page.
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShowSeats(showSeats);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setCreatedAt(new Date());
        booking.setAmount(priceCalculatorService.calculatePrice(showSeats, show));

        //Save booking in the DB.

        return booking;
    }
}
