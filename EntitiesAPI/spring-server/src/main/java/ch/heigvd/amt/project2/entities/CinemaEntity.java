package ch.heigvd.amt.project2.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class CinemaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer cinemaId;
    @Column(nullable=false)
    private String name;
    @Column(nullable=false)
    private String city;
    @Column(nullable=false)
    private String price;
    /*
    @OneToMany(mappedBy="cinemaEntity")
    private List<ScreeningEntity> screeningEntities;
     */
    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy ="cinemaEntity",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ScreeningEntity> screeningEntities;
}
