package ktpm.uet.vnu.movie_booking.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomResponse {
    private Long id;
    private String name;
    private Integer capacity;
}