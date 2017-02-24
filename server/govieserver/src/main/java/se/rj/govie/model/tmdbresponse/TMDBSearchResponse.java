package se.rj.govie.model.tmdbresponse;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import se.rj.govie.model.GovieObject;
import se.rj.govie.model.Movie;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TMDBSearchResponse extends GovieObject {

    private final Integer page;

    private final List<Movie> results;

    @JsonCreator
    public TMDBSearchResponse(@JsonProperty("page") Integer page, @JsonProperty("results") List<Movie> results) {
        this.page = page;
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public List<Movie> getResults() {
        return results;
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
        TMDBSearchResponse rhs = (TMDBSearchResponse) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.page, rhs.page)
                .append(this.results, rhs.results)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(page)
                .append(results)
                .toHashCode();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("page", page)
                .append("results", results)
                .toString();
    }
}
