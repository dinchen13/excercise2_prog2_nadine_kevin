package at.ac.fhcampuswien.fhmdb;

public enum Screen {
    HOME("/home.fmxl");

   public final String path;

    Screen(String path) {
        this.path = path;
    }
}
