package at.ac.fhcampuswien.fhmdb;
import java.util.List;

public class Movie {

    private String id;
    private String title;
    private List<Genre> genres;
    private int releaseYear;
    private String description;
    private String imgUrl;
    private int lengthInMinutes;
    private List<String> directors;
    private List<String> writers;
    private List<String> mainCast;
    private double rating;

    //_________________________ GETTER & SETTER ______________________//

    public int getReleaseYear() {
        return releaseYear;
    }

    public double getRating() {
        return rating;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public List<String> getWriters() {
        return writers;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public List<String> getMainCast() {
        return mainCast;
    }

    public String getTitle() {
        return title;
    }

    public void setMainCast(List<String> mainCast) {
        this.mainCast = mainCast;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public void setWriters(List<String> writers) {
        this.writers = writers;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //_________________________ METHODS ______________________//
    public static List<Movie> initializeMoviesNew() {
        return MovieAPI.getMovies(null, null, null, null);
    }

    public static List<Movie> initializeMoviesTest() {
        return MovieAPI.getMovies("ni", null, null, null);
    }
}