package ru.polytech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.polytech.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
