package ch.heigvd.amt.project2.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    @Column(nullable=false)
    private String username;
    @Column(nullable=false)
    private String password;
    @Column(unique=true, nullable=false)
    private String email;
    @Column(nullable=false)
    private String role;
}