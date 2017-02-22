package se.rj.govie.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchUserRequest extends SearchRequest {

    public SearchUserRequest(@JsonProperty("term") String term, @JsonProperty("user") String user) {
        super(term, user);
    }
}
