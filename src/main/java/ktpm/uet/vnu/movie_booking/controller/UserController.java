package ktpm.uet.vnu.movie_booking.controller;

import ktpm.uet.vnu.movie_booking.dto.response.UserResponse;
import ktpm.uet.vnu.movie_booking.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    public UserResponse me(
        @AuthenticationPrincipal UserDetails principal
    ) {
        var user = userRepository.findByEmail(principal.getUsername())
            .orElseThrow(() ->
                new IllegalStateException("Tài khoản không còn tồn tại")
            );

        return new UserResponse(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getRole().name()
        );
    }
}