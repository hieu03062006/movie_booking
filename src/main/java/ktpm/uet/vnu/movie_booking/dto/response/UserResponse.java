package ktpm.uet.vnu.movie_booking.dto.response;

public record UserResponse(
    Long id,
    String username,
    String email,
    String role
) {}