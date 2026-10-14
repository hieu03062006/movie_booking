package ktpm.uet.vnu.movie_booking.controller;

import jakarta.validation.Valid;
import ktpm.uet.vnu.movie_booking.dto.request.ShowtimeRequest;
import ktpm.uet.vnu.movie_booking.dto.response.ShowtimeResponse;
import ktpm.uet.vnu.movie_booking.service.ShowtimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/showtimes")
@RequiredArgsConstructor
public class ShowtimeController {

    private final ShowtimeService showtimeService;

    @GetMapping
    public ResponseEntity<List<ShowtimeResponse>> getAll() {
        return ResponseEntity.ok(showtimeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowtimeResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(showtimeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ShowtimeResponse> create(@Valid @RequestBody ShowtimeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(showtimeService.create(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        showtimeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}