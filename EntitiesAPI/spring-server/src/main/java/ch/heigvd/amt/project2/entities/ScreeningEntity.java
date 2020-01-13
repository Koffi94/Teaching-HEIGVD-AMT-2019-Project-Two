package ch.heigvd.amt.project2.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class ScreeningEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long screeningId;
    @Column(nullable=false)
    private Long userId;
    private String time;
    @Column(nullable=false)
    private String room;
    @Column(nullable=false)
    private String property;
}
