package se.rj.govie.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class User extends IndexableObject {

    public static final String USER_TYPE = "user";

    private final String uid;

    private final String name;

    private final String avatarUrl;

    @JsonCreator
    public User(@JsonProperty("id") String id,
                @JsonProperty("uid") String uid,
                @JsonProperty("name") String name,
                @JsonProperty("avatarUrl") String avatarUrl) {
        super(id);
        this.uid = uid;
        this.name = name;
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getUid() {
        return uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;

        return new EqualsBuilder()
                .append(getUid(), user.getUid())
                .append(getName(), user.getName())
                .append(getAvatarUrl(), user.getAvatarUrl())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getUid())
                .append(getName())
                .append(getAvatarUrl())
                .toHashCode();
    }

    @JsonIgnore
    @Override
    public String getType() {
        return USER_TYPE;
    }

    @JsonIgnore
    @Override
    public String getId() {
        return uid;
    }
}
