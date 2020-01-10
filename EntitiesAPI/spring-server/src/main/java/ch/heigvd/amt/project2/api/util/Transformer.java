package ch.heigvd.amt.project2.api.util;

import ch.heigvd.amt.project2.api.model.Cinema;
import ch.heigvd.amt.project2.api.model.Movie;
import ch.heigvd.amt.project2.api.model.Screening;
import ch.heigvd.amt.project2.entities.CinemaEntity;
import ch.heigvd.amt.project2.entities.MovieEntity;
import ch.heigvd.amt.project2.entities.ScreeningEntity;

public class Transformer {
    public static CinemaEntity toCinemaEntity(Cinema cinema) {
        CinemaEntity entity = new CinemaEntity();
        entity.setCinema_id(cinema.getCinemaId());
        entity.setName(cinema.getName());
        entity.setCity(cinema.getCity());
        entity.setPrice(cinema.getPrice());
        return entity;
    }

    public static Cinema toCinema(CinemaEntity entity) {
        Cinema cinema = new Cinema();
        cinema.setCinemaId(entity.getCinema_id());
        cinema.setName(entity.getName());
        cinema.setCity(entity.getCity());
        cinema.setPrice(entity.getPrice());
        return cinema;
    }

    public static ScreeningEntity toScrenningEntity(Screening screening) {
        ScreeningEntity entity = new ScreeningEntity();
        entity.setScreening_id(screening.getScreeningId());
        entity.setUser_id(screening.getUserId());
        entity.setTime(screening.getTime());
        entity.setRoom(screening.getRoom());
        entity.setProperty(screening.getProperty());
        return entity;
    }

    public static Screening toScreening(ScreeningEntity entity) {
        Screening screening = new Screening();
        screening.setScreeningId(entity.getScreening_id());
        screening.setUserId(entity.getUser_id());
        screening.setTime(entity.getTime());
        screening.setRoom(entity.getRoom());
        screening.setProperty(entity.getProperty());
        return screening;
    }

    public static MovieEntity toMovieEntity(Movie movie) {
        MovieEntity entity = new MovieEntity();
        entity.setMovie_id(movie.getMovieId());
        entity.setTitle(movie.getTitle());
        entity.setRelease_date(movie.getReleaseDate());
        entity.setCategory(movie.getCategory());
        return entity;
    }

    public static Movie toMovie(MovieEntity entity) {
        Movie movie = new Movie();
        movie.setMovieId(entity.getMovie_id());
        movie.setTitle(entity.getTitle());
        movie.setReleaseDate(entity.getRelease_date());
        movie.setCategory(entity.getCategory());
        return movie;
    }
}
