package ch.heigvd.amt.project2.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class ScreeningEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long screening_id;
    @Column(nullable=false)
    private Long user_id;
    private String time;
    @Column(nullable=false)
    private String room;
    @Column(nullable=false)
    private String property;
}
