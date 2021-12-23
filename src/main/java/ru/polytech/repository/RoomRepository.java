package ru.polytech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.polytech.entity.Room;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query(value = "select distinct r from Room r left join Booking b on r.id = b.room.id " +
            "where not (b.startDate <= :startDate and b.endDate >= :endDate " +
            "or b.startDate <= :startDate and b.endDate >= :startDate " +
            "or b.startDate >= :startDate and b.endDate <= :endDate " +
            "or b.startDate <= :endDate and b.endDate >= :endDate) or b is null")
    List<Room> findAllByDateInterval(OffsetDateTime startDate, OffsetDateTime endDate);
}
