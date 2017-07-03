package se.rj.govie.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class GovieEvent {

    private final String uid;

    private final String name;

    private final String avatarUrl;

    private final EventType type;

    GovieEvent(String uid, String name, String avatarUrl, EventType type) {
        this.uid = uid;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public EventType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof GovieEvent)) {
            return false;
        }

        GovieEvent that = (GovieEvent) o;

        return new EqualsBuilder()
                .append(getUid(), that.getUid())
                .append(getName(), that.getName())
                .append(getAvatarUrl(), that.getAvatarUrl())
                .append(getType(), that.getType())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getUid())
                .append(getName())
                .append(getAvatarUrl())
                .append(getType())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uid", uid)
                .append("name", name)
                .append("avatarUrl", avatarUrl)
                .append("type", type)
                .toString();
    }
}
