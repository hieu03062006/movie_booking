package ktpm.uet.vnu.movie_booking.dto.response;

public record LoginResponse(
    String token,
    String tokenType
) {}