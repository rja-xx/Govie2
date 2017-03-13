package se.rj.govie.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Profile extends FirebaseObject {


    private final String uid;

    private final String avatarUrl;

    private final String backdropUrl;

    private final String name;

    private final Integer followers;

    private final Integer follows;

    private final Integer rates;

    private final Integer cinemas;

    @JsonCreator
    public Profile(@JsonProperty("uid") String uid,
                   @JsonProperty("avatarUrl") String avatarUrl,
                   @JsonProperty("name") String name,
                   @JsonProperty("handle") String handle) {
        this.uid = uid;
        this.avatarUrl = avatarUrl;
        this.name = name;
        this.backdropUrl = "";
        this.followers = 0;
        this.follows = 0;
        this.rates = 0;
        this.cinemas = 0;
    }

    public String getUid() {
        return uid;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getBackdropUrl() {
        return backdropUrl;
    }

    public String getName() {
        return name;
    }

    public Integer getFollowers() {
        return followers;
    }

    public Integer getFollows() {
        return follows;
    }

    public Integer getRates() {
        return rates;
    }

    public Integer getCinemas() {
        return cinemas;
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
        Profile rhs = (Profile) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.uid, rhs.uid)
                .append(this.avatarUrl, rhs.avatarUrl)
                .append(this.backdropUrl, rhs.backdropUrl)
                .append(this.name, rhs.name)
                .append(this.followers, rhs.followers)
                .append(this.follows, rhs.follows)
                .append(this.rates, rhs.rates)
                .append(this.cinemas, rhs.cinemas)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(uid)
                .append(avatarUrl)
                .append(backdropUrl)
                .append(name)
                .append(followers)
                .append(follows)
                .append(rates)
                .append(cinemas)
                .toHashCode();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("uid", uid)
                .append("avatarUrl", avatarUrl)
                .append("backdropUrl", backdropUrl)
                .append("name", name)
                .append("followers", followers)
                .append("follows", follows)
                .append("rates", rates)
                .append("cinemas", cinemas)
                .toString();
    }
}
