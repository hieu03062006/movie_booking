package ktpm.uet.vnu.movie_booking.service;

import ktpm.uet.vnu.movie_booking.dto.request.MovieRequest;
import ktpm.uet.vnu.movie_booking.dto.response.MovieResponse;
import ktpm.uet.vnu.movie_booking.entity.Movie;
import ktpm.uet.vnu.movie_booking.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public MovieResponse getMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                // Ném exception thuần túy của Java, Controller sẽ lo việc chuyển thành lỗi HTTP 404
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phim với ID: " + id));
        return mapToResponse(movie);
    }

    public MovieResponse createMovie(MovieRequest request) {
        Movie movie = Movie.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .director(request.getDirector())
                .duration(request.getDuration())
                .releaseDate(request.getReleaseDate())
                .posterUrl(request.getPosterUrl())
                .build();

        Movie savedMovie = movieRepository.save(movie);
        return mapToResponse(savedMovie);
    }

    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy phim với ID: " + id);
        }
        movieRepository.deleteById(id);
    }

    // Hàm mapper nội bộ giúp che giấu hoàn toàn Entity khỏi tầng API
    private MovieResponse mapToResponse(Movie movie) {
        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .director(movie.getDirector())
                .duration(movie.getDuration())
                .releaseDate(movie.getReleaseDate())
                .posterUrl(movie.getPosterUrl())
                .build();
    }
}