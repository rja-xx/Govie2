package se.rj.govie.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class GovieEvent {

    private final String uid;

    private final String name;

    private final String avatarUrl;

    private final EventType type;

    private final Date date;

    GovieEvent(String uid, String name, String avatarUrl, EventType type, Date date) {
        this.uid = uid;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.type = type;
        this.date = date;
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

    public Date getDate() {
        return date;
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
        GovieEvent rhs = (GovieEvent) obj;
        return new EqualsBuilder()
                .append(this.uid, rhs.uid)
                .append(this.name, rhs.name)
                .append(this.avatarUrl, rhs.avatarUrl)
                .append(this.type, rhs.type)
                .append(this.date, rhs.date)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(uid)
                .append(name)
                .append(avatarUrl)
                .append(type)
                .append(date)
                .toHashCode();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uid", uid)
                .append("name", name)
                .append("avatarUrl", avatarUrl)
                .append("type", type)
                .append("date", date)
                .toString();
    }
}
