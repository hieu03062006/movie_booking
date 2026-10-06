package ktpm.uet.vnu.movie_booking.repository;

import ktpm.uet.vnu.movie_booking.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}