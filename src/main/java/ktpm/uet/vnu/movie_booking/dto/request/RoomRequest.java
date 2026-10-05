package ktpm.uet.vnu.movie_booking.dto.request;

import lombok.Data;

@Data
public class RoomRequest {
    private String name;
    private Integer capacity;
}