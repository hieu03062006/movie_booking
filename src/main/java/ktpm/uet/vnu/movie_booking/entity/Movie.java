package ktpm.uet.vnu.movie_booking.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title; // Tên phim

    @Column(columnDefinition = "TEXT")
    private String description; // Mô tả phim

    @Column(nullable = false)
    private String director; // Đạo diễn

    @Column(nullable = false)
    private Integer duration; // Thời lượng (phút)

    @Column(name = "release_date")
    private LocalDate releaseDate; // Ngày khởi chiếu

    @Column(name = "poster_url")
    private String posterUrl; // Link ảnh poster

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Tự động lưu thời gian tạo

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Tự động cập nhật thời gian sửa
}