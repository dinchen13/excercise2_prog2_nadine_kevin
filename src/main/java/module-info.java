module at.ac.fhcampuswien.fhmdb {
    requires javafx.controls;

    requires javafx.fxml;
    requires okhttp3;
    requires com.google.gson;
    requires ormlite.jdbc;
    requires java.sql;
    requires org.kordamp.bootstrapfx.core;
    requires com.jfoenix;
    //requires io.coil_kt.coil;

    opens at.ac.fhcampuswien.fhmdb to ormlite.jdbc,javafx.fxml,com.google.gson;
    exports at.ac.fhcampuswien.fhmdb;
}