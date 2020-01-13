package ch.heigvd.amt.project2.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;
    @Column(nullable=false)
    private String title;
    @Column(nullable=false)
    private String release_date;
    @Column(nullable=false)
    private String category;
}
