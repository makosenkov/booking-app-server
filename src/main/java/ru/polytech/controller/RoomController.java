package ru.polytech.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.polytech.generated.api.RoomApi;
import ru.polytech.generated.model.Room;
import ru.polytech.mapping.Mapper;
import ru.polytech.repository.RoomRepository;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/rest")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RoomController implements RoomApi {

    private final RoomRepository roomRepository;
    private final Mapper mapper;

    public RoomController(RoomRepository roomRepository,
                          Mapper mapper) {
        this.roomRepository = roomRepository;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        roomRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<Room>> findAll() {
        return ResponseEntity.ok(roomRepository.findAll().stream()
                .map(p -> mapper.map(p, Room.class))
                .collect(Collectors.toList())
        );
    }

    @Override
    public ResponseEntity<List<Room>> findAllByDates(
            @DateTimeFormat(pattern = "yyyy-MM-dd") @Valid Date startDate,
            @DateTimeFormat(pattern = "yyyy-MM-dd") @Valid Date endDate) {
        OffsetDateTime now = OffsetDateTime.now();
        return ResponseEntity.ok(roomRepository.findAllByDateInterval(
                        startDate.toInstant().atOffset(now.getOffset()),
                        endDate.toInstant().atOffset(now.getOffset()))
                .stream().map(r -> mapper.map(r, Room.class))
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<Boolean> isAvailable(Long id,
                                               @DateTimeFormat(pattern = "yyyy-MM-dd") @Valid Date startDate,
                                               @DateTimeFormat(pattern = "yyyy-MM-dd") @Valid Date endDate) {
        OffsetDateTime now = OffsetDateTime.now();
        return ResponseEntity.ok(roomRepository.findAllByDateInterval(
                        startDate.toInstant().atOffset(now.getOffset()),
                        endDate.toInstant().atOffset(now.getOffset()))
                .stream().anyMatch(r -> r.getId().equals(id)));
    }

    @Override
    public ResponseEntity<Void> save(Room body) {
        roomRepository.save(mapper.map(body, ru.polytech.entity.Room.class));
        return ResponseEntity.ok().build();
    }

}
