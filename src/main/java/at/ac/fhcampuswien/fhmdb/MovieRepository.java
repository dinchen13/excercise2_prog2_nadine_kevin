package at.ac.fhcampuswien.fhmdb;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class MovieRepository {


    Dao<MovieEntity, Long> dao;
    public MovieRepository(){
        this.dao = Databasemanager.getDatabasemanager().getDao();
    }

    public void addToMovies(Movie e) throws SQLException {
        dao.create(eToMovie(e));
    }
    public void removeFromMovies (Movie e) throws SQLException {
        dao.delete(eToMovie(e));
    }
    public List<MovieEntity> readAllMovies()throws SQLException{
        return dao.queryForAll();
    }
    private MovieEntity eToMovie(Movie e){
        return new MovieEntity(e.getTitle(),e.getReleaseYear(),e.getImgUrl());
    }

}
