package com.example.bookmyshowjun24.repositories;

import com.example.bookmyshowjun24.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    List<ShowSeat> findAllById(List<Long> showSeatIds);

    @Override
    ShowSeat save(ShowSeat showSeat);
}
