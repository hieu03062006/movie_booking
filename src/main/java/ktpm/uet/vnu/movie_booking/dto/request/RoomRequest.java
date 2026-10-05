package ktpm.uet.vnu.moviebooking.dto.request;

import lombok.Data;

@Data
public class RoomRequest {
    private String name;
    private Integer capacity;
}