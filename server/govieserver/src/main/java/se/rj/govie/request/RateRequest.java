package se.rj.govie.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import se.rj.govie.model.FirebaseObject;

import java.util.List;

public class RateRequest extends FirebaseObject {

    private final String uid;

    private final String movieId;

    private final String cinemaId;

    private final List<String> friends;

    private final String comment;

    private final Integer rating;

    private final Boolean facebook;

    private final Boolean twitter;

    @JsonCreator
    public RateRequest(@JsonProperty("uid") String uid,
                       @JsonProperty("movieId") String movieId,
                       @JsonProperty("cinemaId") String cinemaId,
                       @JsonProperty("friends") List<String> friends,
                       @JsonProperty("comment") String comment,
                       @JsonProperty("rating") Integer rating,
                       @JsonProperty("facebook") Boolean facebook,
                       @JsonProperty("twitter") Boolean twitter) {
        this.uid = uid;
        this.movieId = movieId;
        this.cinemaId = cinemaId;
        this.friends = friends;
        this.comment = comment;
        this.rating = rating;
        this.facebook = facebook;
        this.twitter = twitter;
    }

    public String getUid() {
        return uid;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getCinemaId() {
        return cinemaId;
    }

    public List<String> getFriends() {
        return friends;
    }

    public String getComment() {
        return comment;
    }

    public Integer getRating() {
        return rating;
    }

    public Boolean getFacebook() {
        return facebook;
    }

    public Boolean getTwitter() {
        return twitter;
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
        RateRequest rhs = (RateRequest) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.uid, rhs.uid)
                .append(this.movieId, rhs.movieId)
                .append(this.cinemaId, rhs.cinemaId)
                .append(this.friends, rhs.friends)
                .append(this.comment, rhs.comment)
                .append(this.rating, rhs.rating)
                .append(this.facebook, rhs.facebook)
                .append(this.twitter, rhs.twitter)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(uid)
                .append(movieId)
                .append(cinemaId)
                .append(friends)
                .append(comment)
                .append(rating)
                .append(facebook)
                .append(twitter)
                .toHashCode();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("uid", uid)
                .append("movieId", movieId)
                .append("cinemaId", cinemaId)
                .append("friends", friends)
                .append("comment", comment)
                .append("rating", rating)
                .append("facebook", facebook)
                .append("twitter", twitter)
                .toString();
    }
}