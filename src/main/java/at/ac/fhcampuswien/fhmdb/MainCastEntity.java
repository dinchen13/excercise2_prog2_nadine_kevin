package at.ac.fhcampuswien.fhmdb;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "main_cast")
public class MainCastEntity {
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField(foreign = true)
    private MovieEntity movie;
    @DatabaseField()
    private String actor;

}
