module at.ac.fhcampuswien.fhmdb.fhmdb {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires com.google.gson;


    opens at.ac.fhcampuswien.fhmdb to javafx.fxml,com.google.gson;
    exports at.ac.fhcampuswien.fhmdb;
}