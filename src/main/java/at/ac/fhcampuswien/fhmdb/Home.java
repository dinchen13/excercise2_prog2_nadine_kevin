package at.ac.fhcampuswien.fhmdb;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import org.kordamp.bootstrapfx.BootstrapFX;


public class Home extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1215,790);
       // scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setTitle("MovieCampus");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        try {
            Databasemanager dbManager = Databasemanager.getDatabasemanager();
            dbManager.testDB();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        launch();
    }

}