package se.rj.govie.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cinema extends IndexableObject {

    public static final String CINEMA_TYPE = "cinema_type";

    private final String name;

    private final Double lat;

    private final Double lon;

    private final String address;

    @JsonCreator
    public Cinema(@JsonProperty("id") String id,
                  @JsonProperty("name") String name,
                  @JsonProperty("geometry.location.lat") Double lat,
                  @JsonProperty("geometry.location.lng") Double lon,
                  @JsonProperty("vicinity") String address) {
        super(id);
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    public String getAddress() {
        return address;
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
        Cinema rhs = (Cinema) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.name, rhs.name)
                .append(this.lat, rhs.lat)
                .append(this.lon, rhs.lon)
                .append(this.address, rhs.address)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(name)
                .append(lat)
                .append(lon)
                .append(address)
                .toHashCode();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("name", name)
                .append("lat", lat)
                .append("lon", lon)
                .append("address", address)
                .toString();
    }

    @Override
    public String getType() {
        return CINEMA_TYPE;
    }

    @Override
    public String getId() {
        return id;
    }
}
