package se.rj.govie.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import se.rj.govie.model.FirebaseObject;

public class SearchRequest extends FirebaseObject {

    private final String term;

    private final String user;

    @JsonCreator
    public SearchRequest(@JsonProperty("term") String term, @JsonProperty("user") String user) {
        this.term = term;
        this.user = user;
    }

    public String getTerm() {
        return term;
    }

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("term", term)
                .append("user", user)
                .toString();
    }
}
