package at.ac.fhcampuswien.fhmdb;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

public class Database {
    public static final String DB_URL = "jdbc:h2:file: ./db/moviedb";
    private static final String user = "user";
    private static final String password = "pass";

    private static ConnectionSource connectionSource;
    private Dao<MovieEntity, Long> dao;
    private static Database instance;

    public Dao<MovieEntity, Long> getDao() {
        return dao;
    }

    private Database() {
        try {
            createConnectionSource();
            dao = DaoManager.createDao(connectionSource, MovieEntity.class);
            createTables();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void testDB() throws SQLException {
        //MovieEntity movie = new MovieEntity("hi", List.of(Genre.ADVENTURE),192,"qw","asa",123,List.of("was"),List.of("as"),List.of("as"),12.0);
        //dao.create(movie);
    }
    public static Database getDatabase() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    private static void createTables() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, MovieEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, GenreEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, DirectorEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, WriterEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, MainCastEntity.class);
    }

    private static void createConnectionSource() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
       connectionSource = new JdbcConnectionSource(DB_URL, user, password);
    }

}
