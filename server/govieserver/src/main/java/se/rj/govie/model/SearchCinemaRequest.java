package se.rj.govie.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import se.rj.govie.request.SearchRequest;

public class SearchCinemaRequest extends SearchRequest {

    private final Double lon;

    private final Double lat;

    public SearchCinemaRequest(@JsonProperty("term") String term,
                               @JsonProperty("user") String user,
                               @JsonProperty("lon") Double lon,
                               @JsonProperty("lat") Double lat
    ) {
        super(term, user);
        this.lon = lon;
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public Double getLat() {
        return lat;
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
        SearchCinemaRequest rhs = (SearchCinemaRequest) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.lon, rhs.lon)
                .append(this.lat, rhs.lat)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(lon)
                .append(lat)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("lon", lon)
                .append("lat", lat)
                .toString();
    }
}
