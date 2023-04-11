package at.ac.fhcampuswien.fhmdb;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.*;

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
    private List<Movie> allMovies = new ArrayList<>(Movie.initializeMoviesNew());
    private List<Movie> selectedMovies = allMovies;
    String query = null;
    String genre = null;
    String year = null;
    String rating =null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        if (genreBox.getValue() != null){
            genre = genreBox.getValue().toString();
        }
        if(yearBox.getText()!=null){
            year = yearBox.getText().toString();
        }
        if(ratingBox.getValue()!=null) {
            rating = ratingBox.getValue().toString();
        }
        if(genreBox.getValue()==Genre.ALL){
            genre=null;
        }
        if(yearBox.getText()=="no filter"){
            year=null;
        }
        if(ratingBox.getValue()=="no filter"){
            rating=null;
        }
        System.out.println(query+genre+year+rating);
        loadingMovies( MovieAPI.getMovies(query, genre, year, rating));
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
            throw new RuntimeException(e);
        }
    }
}
