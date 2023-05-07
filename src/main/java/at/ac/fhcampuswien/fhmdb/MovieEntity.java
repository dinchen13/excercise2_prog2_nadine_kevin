package at.ac.fhcampuswien.fhmdb;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;
import java.util.List;

@DatabaseTable(tableName = "movies")
public class MovieEntity {
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField()
    private String title;
    @ForeignCollectionField(eager = true)
    private Collection<GenreEntity> genres;
    @DatabaseField()
    private int releaseYear;
    @DatabaseField()
    private String description;
    @DatabaseField()
    private String imgUrl;
    @DatabaseField()
    private int lengthInMinutes;
    @ForeignCollectionField(eager = true)
    private Collection<DirectorEntity> directors;
    @ForeignCollectionField(eager = true)
    private Collection<WriterEntity> writers;
    @ForeignCollectionField(eager = true)
    private Collection<MainCastEntity> mainCast;
    @DatabaseField()
    private double rating;


}

