package g33k.limited.igdb.core.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sambains on 19/12/2016.
 */

public class Game {

    @SerializedName("name")
    private String name;

    @SerializedName("summary")
    private String summary;

    @SerializedName("storyline")
    private String storyline;

    @SerializedName("rating")
    private double rating;

    public String getName() {
        return name;
    }

    public String getSummary() {
        return summary;
    }

    public String getStoryline() {
        return storyline;
    }

    public double getRating() {
        return rating;
    }
}
