package se.rj.govie.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie extends IndexableObject {


    public static final String MOVIE_TYPE = "movie";

    private final String id;

    private final String posterPath;

    private final String backdropPath;

    private final String title;

    private final Date releaseDate;

    @JsonCreator
    public Movie(@JsonProperty("id") String id,
                 @JsonProperty("poster_path") String posterPath,
                 @JsonProperty("backdrop_path") String backdropPath,
                 @JsonProperty("title") String title,
                 @JsonProperty("release_date") Date releaseDate) {
        super(id);
        this.id = id;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.title = title;
        this.releaseDate = releaseDate;
    }

    public String getId() {
        return id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getTitle() {
        return title;
    }

    public Date getReleaseDate() {
        return releaseDate;
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
                .appendSuper(super.equals(obj))
                .append(this.id, rhs.id)
                .append(this.posterPath, rhs.posterPath)
                .append(this.backdropPath, rhs.backdropPath)
                .append(this.title, rhs.title)
                .append(this.releaseDate, rhs.releaseDate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(id)
                .append(posterPath)
                .append(backdropPath)
                .append(title)
                .append(releaseDate)
                .toHashCode();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("id", id)
                .append("posterPath", posterPath)
                .append("backdropPath", backdropPath)
                .append("title", title)
                .append("releaseDate", releaseDate)
                .toString();
    }

    @Override
    public String getType() {
        return MOVIE_TYPE;
    }
}
