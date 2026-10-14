package ktpm.uet.vnu.movie_booking.service;

import ktpm.uet.vnu.movie_booking.dto.request.LoginRequest;
import ktpm.uet.vnu.movie_booking.dto.request.RegisterRequest;
import ktpm.uet.vnu.movie_booking.dto.response.LoginResponse;
import ktpm.uet.vnu.movie_booking.entity.Role;
import ktpm.uet.vnu.movie_booking.entity.User;
import ktpm.uet.vnu.movie_booking.exception.BadRequestException;
import ktpm.uet.vnu.movie_booking.repository.UserRepository;
import ktpm.uet.vnu.movie_booking.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtService jwtService;

    public AuthService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        AuthenticationManager authenticationManager,
        CustomUserDetailsService userDetailsService,
        JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    public void register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new BadRequestException("Email đã được sử dụng");
        }

        if (userRepository.existsByUsername(request.username())) {
            throw new BadRequestException("Username đã được sử dụng");
        }

        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.USER);

        userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
            )
        );

        UserDetails userDetails =
            userDetailsService.loadUserByUsername(request.email());

        String token = jwtService.generateToken(userDetails);
        return new LoginResponse(token, "Bearer");
    }
}