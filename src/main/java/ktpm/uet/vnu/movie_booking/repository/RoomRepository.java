package ktpm.uet.vnu.movie_booking.repository;

import ktpm.uet.vnu.movie_booking.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}