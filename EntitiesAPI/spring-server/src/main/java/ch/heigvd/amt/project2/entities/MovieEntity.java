package ch.heigvd.amt.project2.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;
    @Column(nullable=false)
    private String title;
    @Column(nullable=false)
    private String release_date;
    @Column(nullable=false)
    private String category;
    /*
    @OneToMany(mappedBy="movieEntity")
    private List<ScreeningEntity> screeningEntities;
     */
    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy ="movieEntity",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ScreeningEntity> screeningEntities;
}
