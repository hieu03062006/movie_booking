package ktpm.uet.vnu.movie_booking.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class ShowtimeRequest {

    @NotNull(message = "movieId không được để trống")
    @Positive(message = "movieId phải lớn hơn 0")
    private Long movieId;

    @NotNull(message = "roomId không được để trống")
    @Positive(message = "roomId phải lớn hơn 0")
    private Long roomId;

    @NotNull(message = "Giờ bắt đầu không được để trống")
    @Future(message = "Giờ bắt đầu phải ở tương lai")
    private LocalDateTime startTime;

    @NotNull(message = "Giờ kết thúc không được để trống")
    private LocalDateTime endTime;

    @NotNull(message = "Giá vé không được để trống")
    @DecimalMin(value = "0.0", message = "Giá vé không được âm")
    private BigDecimal price;
}