package ktpm.uet.vnu.movie_booking.controller;

import ktpm.uet.vnu.movie_booking.dto.request.MovieRequest;
import ktpm.uet.vnu.movie_booking.dto.response.MovieResponse;
import ktpm.uet.vnu.movie_booking.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies()); // Trả về HTTP 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id)); // Trả về HTTP 200
    }

    @PostMapping
    public ResponseEntity<MovieResponse> createMovie(@RequestBody MovieRequest request) {
        // Trả về HTTP 201 khi tạo mới thành công
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.createMovie(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build(); // Trả về HTTP 204 khi xóa thành công
    }
}