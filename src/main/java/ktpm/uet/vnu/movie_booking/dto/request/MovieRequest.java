package ktpm.uet.vnu.movie_booking.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MovieRequest {
    private String title;
    private String description;
    private String director;
    private Integer duration;
    private LocalDate releaseDate;
    private String posterUrl;
}