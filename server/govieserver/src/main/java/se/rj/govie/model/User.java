package se.rj.govie.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;

public class User {

    private final String uid;

    private final String name;

    private final String avatarUrl;

    public User(Map<String, String> values) {
        this.uid = values.get("uid");
        this.avatarUrl = values.get("avatarUrl");
        this.name = values.get("name");
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


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("avatarUrl", avatarUrl)
                .toString();
    }
}
