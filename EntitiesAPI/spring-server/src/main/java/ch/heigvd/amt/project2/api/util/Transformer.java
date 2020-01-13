package ch.heigvd.amt.project2.api.util;

import ch.heigvd.amt.project2.api.model.*;
import ch.heigvd.amt.project2.entities.CinemaEntity;
import ch.heigvd.amt.project2.entities.MovieEntity;
import ch.heigvd.amt.project2.entities.ScreeningEntity;

public class Transformer {
    // Cinemas conversions
    public static CinemaEntity toCinemaEntity(CinemaFull cinema) {
        CinemaEntity entity = new CinemaEntity();
        entity.setCinema_id(cinema.getCinemaId());
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
        cinema.setCinemaId(entity.getCinema_id());
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
        entity.setScreening_id(screening.getScreeningId());
        entity.setUser_id(screening.getUserId());
        entity.setTime(screening.getTime());
        entity.setRoom(screening.getRoom());
        entity.setProperty(screening.getProperty());
        return entity;
    }

    public static ScreeningEntity toScrenningEntity(ScreeningManage screening) {
        ScreeningEntity entity = new ScreeningEntity();
        entity.setUser_id(screening.getUserId());
        entity.setTime(screening.getTime());
        entity.setRoom(screening.getRoom());
        entity.setProperty(screening.getProperty());
        return entity;
    }

    public static ScreeningFull toScreeningFull(ScreeningEntity entity) {
        ScreeningFull screening = new ScreeningFull();
        screening.setScreeningId(entity.getScreening_id());
        screening.setUserId(entity.getUser_id());
        screening.setTime(entity.getTime());
        screening.setRoom(entity.getRoom());
        screening.setProperty(entity.getProperty());
        return screening;
    }

    public static ScreeningManage toScreeningManage(ScreeningEntity entity) {
        ScreeningManage screening = new ScreeningManage();
        screening.setUserId(entity.getUser_id());
        screening.setTime(entity.getTime());
        screening.setRoom(entity.getRoom());
        screening.setProperty(entity.getProperty());
        return screening;
    }

    // Movies conversion
    public static MovieEntity toMovieEntity(MovieFull movie) {
        MovieEntity entity = new MovieEntity();
        entity.setMovie_id(movie.getMovieId());
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
        movie.setMovieId(entity.getMovie_id());
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
