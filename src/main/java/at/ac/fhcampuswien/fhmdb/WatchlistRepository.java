package at.ac.fhcampuswien.fhmdb;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class WatchlistRepository {

    Dao<MovieEntity, Long> dao;
    public WatchlistRepository(){
        this.dao = Database.getDatabase().getDao();
    }

    public void addToWatchlist(Movie movie) throws SQLException {
        dao.create(MovieToMovieEntity(movie));
    }
    public void removeFromWatchlist (Movie movie) throws SQLException {
        dao.delete(MovieToMovieEntity(movie));
    }
    public List<MovieEntity> readAllMovies()throws SQLException{
        return dao.queryForAll();
    }
    private MovieEntity MovieToMovieEntity(Movie movie){
        return new MovieEntity();
    }

}
