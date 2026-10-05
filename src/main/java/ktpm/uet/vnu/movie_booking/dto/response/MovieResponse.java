package ktpm.uet.vnu.movie_booking.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class MovieResponse {
    private Long id;
    private String title;
    private String description;
    private String director;
    private Integer duration;
    private LocalDate releaseDate;
    private String posterUrl;
}