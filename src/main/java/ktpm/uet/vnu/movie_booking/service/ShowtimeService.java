package ktpm.uet.vnu.movie_booking.service;

import ktpm.uet.vnu.movie_booking.dto.request.ShowtimeRequest;
import ktpm.uet.vnu.movie_booking.dto.response.ShowtimeResponse;
import ktpm.uet.vnu.movie_booking.entity.Movie;
import ktpm.uet.vnu.movie_booking.entity.Room;
import ktpm.uet.vnu.movie_booking.entity.Showtime;
import ktpm.uet.vnu.movie_booking.exception.BadRequestException;
import ktpm.uet.vnu.movie_booking.exception.ResourceNotFoundException;
import ktpm.uet.vnu.movie_booking.repository.MovieRepository;
import ktpm.uet.vnu.movie_booking.repository.RoomRepository;
import ktpm.uet.vnu.movie_booking.repository.ShowtimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowtimeService {

    private final ShowtimeRepository showtimeRepository;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;

    @Transactional
    public ShowtimeResponse create(ShowtimeRequest request) {
        Movie movie = findMovie(request.getMovieId());
        Room room = findRoom(request.getRoomId());
        validateTime(request, null);

        Showtime showtime = Showtime.builder()
                .movie(movie)
                .room(room)
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .price(request.getPrice())
                .build();

        return ShowtimeResponse.from(showtimeRepository.save(showtime));
    }

    @Transactional
    public ShowtimeResponse update(Long id, ShowtimeRequest request) {
        Showtime showtime = findShowtime(id);
        Movie movie = findMovie(request.getMovieId());
        Room room = findRoom(request.getRoomId());
        validateTime(request, id);

        showtime.setMovie(movie);
        showtime.setRoom(room);
        showtime.setStartTime(request.getStartTime());
        showtime.setEndTime(request.getEndTime());
        showtime.setPrice(request.getPrice());

        return ShowtimeResponse.from(showtimeRepository.save(showtime));
    }

    @Transactional
    public void delete(Long id) {
        Showtime showtime = findShowtime(id);
        showtimeRepository.delete(showtime);
    }

    @Transactional(readOnly = true)
    public ShowtimeResponse getById(Long id) {
        return ShowtimeResponse.from(findShowtime(id));
    }

    @Transactional(readOnly = true)
    public List<ShowtimeResponse> getAll() {
        return showtimeRepository.findAll().stream()
                .map(ShowtimeResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ShowtimeResponse> getByMovie(Long movieId) {
        findMovie(movieId);
        return showtimeRepository.findByMovieId(movieId).stream()
                .map(ShowtimeResponse::from)
                .toList();
    }


    private void validateTime(ShowtimeRequest request, Long excludeId) {
        if (!request.getEndTime().isAfter(request.getStartTime())) {
            throw new BadRequestException("Giờ kết thúc phải sau giờ bắt đầu");
        }
        boolean trung = showtimeRepository.existsOverlap(
                request.getRoomId(),
                request.getStartTime(),
                request.getEndTime(),
                excludeId);
        if (trung) {
            throw new BadRequestException("Phòng đã có suất chiếu trong khoảng thời gian này");
        }
    }

    private Showtime findShowtime(Long id) {
        return showtimeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy suất chiếu id = " + id));
    }

    private Movie findMovie(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phim id = " + id));
    }

    private Room findRoom(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phòng id = " + id));
    }
}