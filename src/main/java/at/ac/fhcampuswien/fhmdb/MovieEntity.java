package at.ac.fhcampuswien.fhmdb;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "movies")
public class MovieEntity {
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField()
    private String title;

    @DatabaseField()
    private int releaseYear;

    @DatabaseField()
    private String imgUrl;

    public MovieEntity(){};
    public MovieEntity(String title, int releaseYear, String imgUrl) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.imgUrl = imgUrl;
    }

}