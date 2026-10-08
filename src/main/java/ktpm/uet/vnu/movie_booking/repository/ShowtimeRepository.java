package ktpm.uet.vnu.movie_booking.repository;

import ktpm.uet.vnu.movie_booking.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

    List<Showtime> findByMovieId(Long movieId);

    @Query("""
        SELECT COUNT(s) > 0 FROM Showtime s
        WHERE s.room.id = :roomId
          AND s.startTime < :endTime
          AND s.endTime > :startTime
          AND (:excludeId IS NULL OR s.id <> :excludeId)
        """)
    boolean existsOverlap(@Param("roomId") Long roomId,
                          @Param("startTime") LocalDateTime startTime,
                          @Param("endTime") LocalDateTime endTime,
                          @Param("excludeId") Long excludeId);
}