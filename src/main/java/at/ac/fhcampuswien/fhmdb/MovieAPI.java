package at.ac.fhcampuswien.fhmdb;

import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MovieAPI {

    public static List<Movie> getMovies(String queryPart,String genrePart, String yearPart, String ratingPart){
        return initializeMovies(UrlBuilder(queryPart, genrePart, yearPart,ratingPart));
    }

    public static String UrlBuilder(String queryPart,String genrePart, String yearPart, String ratingPart){
        String totalURL = "http://localhost:8080/movies";
        if(queryPart!=null||genrePart!=null||yearPart!=null||ratingPart!=null){
            totalURL = totalURL.concat("?");
        }
        if (queryPart!=null){
            totalURL = totalURL.concat("query="+queryPart);
        }
        if (genrePart!=null){
            if(queryPart!=null){
                totalURL=totalURL.concat("&");
            }
            totalURL = totalURL.concat("genre="+genrePart);
        }
        if (yearPart!=null) {
            if(queryPart!=null||genrePart!=null){
                totalURL=totalURL.concat("&");
            }
            totalURL = totalURL.concat("releaseYear="+yearPart);
        }
        if (ratingPart!=null){
            if(queryPart!=null||genrePart!=null||yearPart!=null){
                totalURL=totalURL.concat("&");
            }
            totalURL = totalURL.concat("ratingFrom="+ratingPart);
        }
        System.out.println(totalURL);
        return totalURL;
    }

    public static List<Movie> initializeMovies(String url) {
        OkHttpClient client = new OkHttpClient();
        Request getRequest = new Request.Builder()
                .url(url)
                .build();

        Response getResponse = null;
        String jsonString = null;
        try {
            getResponse = client.newCall(getRequest).execute();
            jsonString = getResponse.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(jsonString);
        GsonBuilder builder = new GsonBuilder();
        List<Movie> movieArray = Arrays.asList(builder.create().fromJson(jsonString, Movie[].class));

        return movieArray;
    }



}
