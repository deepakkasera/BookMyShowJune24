package com.example.bookmyshowjun24.repositories;

import com.example.bookmyshowjun24.models.Show;
import com.example.bookmyshowjun24.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {
    List<ShowSeatType> findAllByShow(Show show);
}



//100.30 => 10030