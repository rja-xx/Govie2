package se.rj.govie.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Movie {

    private final String tmdbid;

    private final String posterPath;

    private final String backdropPath;

    private final String name;


    @JsonCreator
    public Movie(@JsonProperty("tmdbid") String tmdbid,
                 @JsonProperty("posterPath") String posterPath,
                 @JsonProperty("backdropPath") String backdropPath,
                 @JsonProperty("name") String name) {
        this.tmdbid = tmdbid;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.name = name;
    }

    public String getTmdbid() {
        return tmdbid;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Movie rhs = (Movie) obj;
        return new EqualsBuilder()
                .append(this.tmdbid, rhs.tmdbid)
                .append(this.posterPath, rhs.posterPath)
                .append(this.backdropPath, rhs.backdropPath)
                .append(this.name, rhs.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(tmdbid)
                .append(posterPath)
                .append(backdropPath)
                .append(name)
                .toHashCode();
    }
}
