package ch.heigvd.amt.project2.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// Best practices : https://thoughts-on-java.org/best-practices-many-one-one-many-associations-mappings/

@Getter
@Setter
@Entity
public class ScreeningEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer screeningId;
    @Column(nullable=false)
    private Integer userId;
    //@MapsId("movieId")
    //@JoinColumn(name = "movie_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_cinema", nullable = false)
    private CinemaEntity cinemaEntity;

    //@MapsId("cinemaId")
    //@JoinColumn(name = "cinema_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_movie", nullable = false)
    private MovieEntity movieEntity;

    private String time;
    @Column(nullable=false)
    private String room;
    @Column(nullable=false)
    private String property;
}
