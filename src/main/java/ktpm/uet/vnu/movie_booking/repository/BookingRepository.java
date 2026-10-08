package ktpm.uet.vnu.movie_booking.repository;

import ktpm.uet.vnu.movie_booking.entity.Booking;
import ktpm.uet.vnu.movie_booking.entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUserId(Long userId);

    // Tổng số ghế đã đặt của một suất chiếu, không tính booking đã hủy
    @Query("""
        SELECT COALESCE(SUM(b.seatCount), 0L) FROM Booking b
        WHERE b.showtime.id = :showtimeId
          AND b.status <> :excludedStatus
        """)
    Long sumSeatsByShowtime(@Param("showtimeId") Long showtimeId,
                            @Param("excludedStatus") BookingStatus excludedStatus);
}