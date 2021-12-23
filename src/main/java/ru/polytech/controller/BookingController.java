package ru.polytech.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.polytech.entity.Booking;
import ru.polytech.entity.Room;
import ru.polytech.generated.api.BookingApi;
import ru.polytech.repository.BookingRepository;
import ru.polytech.repository.RoomRepository;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Optional;

@RequestMapping("/api/rest")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BookingController implements BookingApi {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    public BookingController(BookingRepository bookingRepository,
                             RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<Void> save(Long id,
                                     @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                     @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        Booking booking = new Booking();
        Optional<Room> roomOpt = roomRepository.findById(id);
        if (roomOpt.isPresent()) {
            OffsetDateTime now = OffsetDateTime.now();
            booking.setRoom(roomOpt.get());
            booking.setStartDate(startDate.toInstant().atOffset(now.getOffset()));
            booking.setEndDate(endDate.toInstant().atOffset(now.getOffset()));
            bookingRepository.save(booking);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            throw new RuntimeException("Такого номера не существует");
        }
    }


}
