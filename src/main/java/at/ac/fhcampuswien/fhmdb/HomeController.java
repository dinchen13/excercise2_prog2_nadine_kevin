package at.ac.fhcampuswien.fhmdb;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class HomeController implements Initializable {
    @FXML
    private VBox home;
    @FXML
    private ComboBox<Genre> genreBox;
    @FXML
    private ComboBox<Object> ratingBox;
    @FXML
    private TextField yearBox;
    @FXML
    private ComboBox<String> sortBox;
    @FXML
    private TextField searchField;
    @FXML
    private AnchorPane slider;
    @FXML
    ImageView menu;
    @FXML
    ImageView menuclose;
    private List<Movie> allMovies = new ArrayList<>(Movie.initializeMoviesNew());
    private List<Movie> selectedMovies = allMovies;
    String query = null;
    String genre = null;
    String year = null;
    String rating = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        slider.setTranslateX(-176);
        menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-176);
            slide.setOnFinished((ActionEvent e) -> {
                menu.setVisible(false);
                menuclose.setVisible(true);
            });
        });

        menuclose.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-176);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e) ->{
                menu.setVisible(true);
                menuclose.setVisible(false);
            });


        });

        genreBox.getItems().addAll(Genre.values());
        sortBox.getItems().addAll("A-Z", "Z-A");
        //yearBox.getItems().addAll("no filter", 2020, 2010, 2000, 1990, 1980, 1970, 1960, 1950, 1940);
        ratingBox.getItems().addAll("no filter", 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
        loadingMovies(allMovies);
    }

    // __________________ Node active actions _______________________//
    public void onActionFilterMovies() {
        if (searchField.getText() != null) {
            query = searchField.getText();
        }
        if (genreBox.getValue() != null) {
            genre = genreBox.getValue().toString();
        }
        if (yearBox.getText() != null) {
            year = yearBox.getText().toString();
        }
        if (ratingBox.getValue() != null) {
            rating = ratingBox.getValue().toString();
        }
        if (genreBox.getValue() == Genre.ALL) {
            genre = null;
        }
        if (yearBox.getText() == "no filter") {
            year = null;
        }
        if (ratingBox.getValue() == "no filter") {
            rating = null;
        }
        System.out.println(query + genre + year + rating);
        selectedMovies = MovieAPI.getMovies(query, genre, year, rating);
        if (sortBox.getValue() != null) {
            selectedMovies = sortMovies(sortBox.getValue(), selectedMovies);
        }
        loadingMovies(selectedMovies);
    }

    public void OnActiveSortMovies() {
        selectedMovies = sortMovies(sortBox.getValue(), selectedMovies);
        loadingMovies(selectedMovies);
    }

    public static List<Movie> sortMovies(String sortAlgo, List<Movie> movies) {
        if (sortAlgo.equals("A-Z")) {
            movies.sort(Comparator.comparing(Movie::getTitle));
        } else if (sortAlgo.equals("Z-A")) {
            movies.sort(Comparator.comparing(Movie::getTitle).reversed());
        }
        return movies;
    }

    public static String getMostPopularActor(List<Movie> movies) {  //gibt jene Person zurück, die am öftesten im mainCast der übergebenen Filme vorkommt.

        return movies.stream()
                .flatMap(movie -> movie.getMainCast().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
    public static int getLongestMovieTitle(List<Movie> movies) {   //filtert auf den längsten Filmtitel der übergebenen Filme und gibt die Anzahl der Buchstaben des Titels zurück
        return movies.stream()
                .map(Movie::getTitle)
                .max(Comparator.comparing(String::length))
                .orElse("").length();

    }
    public static long countMoviesFrom(List<Movie> movies, String director) { //gibt die Anzahl der Filme eines bestimmten Regisseurs zurück.
        return movies.stream()
                .flatMap(movie -> movie.getDirectors().stream())
                .filter(director::equals)
                .count();
    }
    public static List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) { //gibt jene Filme zurück, die zwischen zwei gegebenen Jahren veröffentlicht wurden.
        return movies.stream()
                .filter(movie -> movie.getReleaseYear() > startYear && movie.getReleaseYear() < endYear)
                .toList();
    }

    // ____________________________ LOADING SCREEN ________________________________//
    public void loadingMovies(List<Movie> movies) {
        home.getChildren().clear();
        try {
            for (Movie movie : movies) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("movie_card.fxml"));
                VBox movieSpace = fxmlLoader.load();
                MovieController movieController = fxmlLoader.getController();
                movieController.setData(movie);
                home.getChildren().add(movieSpace);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
            //Just to see if the above implemented Methods do what they should
            out.println("most Popular Actors = " + getMostPopularActor(movies));
            out.println("longest title character count = " + getLongestMovieTitle(movies));
            //mit random Paramentern
            out.println("count Movies from Director1 = " + countMoviesFrom(movies, "Frank Darabont"));
            out.println("Movies zwischen 1972 und 1980 = " + getMoviesBetweenYears(movies, 1972, 1980));
    */
}
