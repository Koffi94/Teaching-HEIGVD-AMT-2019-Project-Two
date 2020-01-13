package ch.heigvd.amt.project2.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class CinemaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cinemaId;
    @Column(nullable=false)
    private String name;
    @Column(nullable=false)
    private String city;
    @Column(nullable=false)
    private String price;
}
