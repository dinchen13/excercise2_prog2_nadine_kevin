package at.ac.fhcampuswien.fhmdb;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "genres")
public class GenreEntity {
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField(foreign = true)
    private MovieEntity movie;
    @DatabaseField()
    private Genre genre;

}
