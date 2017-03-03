package se.rj.govie.search.repository.google;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import se.rj.govie.model.Cinema;
import se.rj.govie.model.GovieObject;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CinemasNearByResponse extends GovieObject {

    private final List<Cinema> results;

    @JsonCreator
    public CinemasNearByResponse(@JsonProperty("results") List<Cinema> results) {
        this.results = results;
    }

    public List<Cinema> getResults() {
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
        CinemasNearByResponse rhs = (CinemasNearByResponse) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.results, rhs.results)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(results)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("results", results)
                .toString();
    }
}
