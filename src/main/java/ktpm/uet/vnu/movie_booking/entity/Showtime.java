package ktpm.uet.vnu.movie_booking.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "showtimes")
@Getter
@Setter
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,  optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY,  optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime start_time;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime end_time;

    @Column(name = "price", nullable = false)
    private BigDecimal price;
}
