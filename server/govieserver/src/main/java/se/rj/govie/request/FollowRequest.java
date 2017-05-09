package se.rj.govie.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import se.rj.govie.model.FirebaseObject;

public class FollowRequest extends FirebaseObject {

    private final String user;

    private final String follow;

    @JsonCreator
    public FollowRequest(@JsonProperty("user") String user, @JsonProperty("follow") String follow) {
        this.user = user;
        this.follow = follow;
    }

    public String getUser() {
        return user;
    }

    public String getFollow() {
        return follow;
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
        FollowRequest rhs = (FollowRequest) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.user, rhs.user)
                .append(this.follow, rhs.follow)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(user)
                .append(follow)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("user", user)
                .append("follow", follow)
                .toString();
    }
}
