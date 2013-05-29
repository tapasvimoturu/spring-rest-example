package imqs.example;

import org.codehaus.jackson.annotate.JsonProperty;

public final class Movie {

    @JsonProperty
    private String name;

    @JsonProperty
    private String rating;

    @JsonProperty
    private String director;

    public Movie() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    Movie(String n, String r, String d) {
        name = n;
        rating = r;
        director = d;
    }
}
