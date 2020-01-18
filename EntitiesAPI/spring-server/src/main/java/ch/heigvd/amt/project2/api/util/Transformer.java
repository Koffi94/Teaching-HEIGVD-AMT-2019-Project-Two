package ch.heigvd.amt.project2.api.util;

import ch.heigvd.amt.project2.api.model.*;
import ch.heigvd.amt.project2.entities.CinemaEntity;
import ch.heigvd.amt.project2.entities.MovieEntity;
import ch.heigvd.amt.project2.entities.ScreeningEntity;
import java.io.Serializable;

public class Transformer implements Serializable {

    // Cinemas conversions
    public static CinemaEntity toCinemaEntity(CinemaFull cinema) {
        CinemaEntity entity = new CinemaEntity();
        entity.setCinemaId(cinema.getCinemaId());
        entity.setName(cinema.getName());
        entity.setCity(cinema.getCity());
        entity.setPrice(cinema.getPrice());
        return entity;
    }

    public static CinemaEntity toCinemaEntity(CinemaManage cinema) {
        CinemaEntity entity = new CinemaEntity();
        entity.setName(cinema.getName());
        entity.setCity(cinema.getCity());
        entity.setPrice(cinema.getPrice());
        return entity;
    }

    public static CinemaFull toCinemaFull(CinemaEntity entity) {
        CinemaFull cinema = new CinemaFull();
        cinema.setCinemaId(entity.getCinemaId());
        cinema.setName(entity.getName());
        cinema.setCity(entity.getCity());
        cinema.setPrice(entity.getPrice());
        return cinema;
    }

    public static CinemaManage toCinemaManage(CinemaEntity entity) {
        CinemaManage cinema = new CinemaManage();
        cinema.setName(entity.getName());
        cinema.setCity(entity.getCity());
        cinema.setPrice(entity.getPrice());
        return cinema;
    }

    // Screenings conversion
    public static ScreeningEntity toScrenningEntity(ScreeningFull screening) {
        ScreeningEntity entity = new ScreeningEntity();
        entity.setScreeningId(screening.getScreeningId());
        entity.setUserId(screening.getUserId());
        entity.setTime(screening.getTime());
        entity.setRoom(screening.getRoom());
        entity.setProperty(screening.getProperty());
        return entity;
    }

    public static ScreeningEntity toScrenningEntity(ScreeningManage screening) {
        ScreeningEntity entity = new ScreeningEntity();
        entity.setTime(screening.getTime());
        entity.setRoom(screening.getRoom());
        entity.setProperty(screening.getProperty());
        return entity;
    }

    public static ScreeningFull toScreeningFull(ScreeningEntity entity) {
        ScreeningFull screening = new ScreeningFull();
        screening.setScreeningId(entity.getScreeningId());
        screening.setUserId(entity.getUserId());
        screening.setCinemaId(entity.getCinemaEntity().getCinemaId());
        screening.setMovieId(entity.getMovieEntity().getMovieId());
        screening.setTime(entity.getTime());
        screening.setRoom(entity.getRoom());
        screening.setProperty(entity.getProperty());
        return screening;
    }

    public static ScreeningManage toScreeningManage(ScreeningEntity entity) {
        ScreeningManage screening = new ScreeningManage();
        screening.setCinemaId(entity.getCinemaEntity().getCinemaId());
        screening.setMovieId(entity.getMovieEntity().getMovieId());
        screening.setTime(entity.getTime());
        screening.setRoom(entity.getRoom());
        screening.setProperty(entity.getProperty());
        return screening;
    }

    // Movies conversion
    public static MovieEntity toMovieEntity(MovieFull movie) {
        MovieEntity entity = new MovieEntity();
        entity.setMovieId(movie.getMovieId());
        entity.setTitle(movie.getTitle());
        entity.setRelease_date(movie.getReleaseDate());
        entity.setCategory(movie.getCategory());
        return entity;
    }

    public static MovieEntity toMovieEntity(MovieManage movie) {
        MovieEntity entity = new MovieEntity();
        entity.setTitle(movie.getTitle());
        entity.setRelease_date(movie.getReleaseDate());
        entity.setCategory(movie.getCategory());
        return entity;
    }

    public static MovieFull toMovieFull(MovieEntity entity) {
        MovieFull movie = new MovieFull();
        movie.setMovieId(entity.getMovieId());
        movie.setTitle(entity.getTitle());
        movie.setReleaseDate(entity.getRelease_date());
        movie.setCategory(entity.getCategory());
        return movie;
    }

    public static MovieManage toMovieManage(MovieEntity entity) {
        MovieManage movie = new MovieManage();
        movie.setTitle(entity.getTitle());
        movie.setReleaseDate(entity.getRelease_date());
        movie.setCategory(entity.getCategory());
        return movie;
    }
}
