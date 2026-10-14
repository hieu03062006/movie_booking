package ktpm.uet.vnu.movie_booking.controller;

import jakarta.validation.Valid;
import ktpm.uet.vnu.movie_booking.dto.request.LoginRequest;
import ktpm.uet.vnu.movie_booking.dto.request.RegisterRequest;
import ktpm.uet.vnu.movie_booking.dto.response.LoginResponse;
import ktpm.uet.vnu.movie_booking.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}