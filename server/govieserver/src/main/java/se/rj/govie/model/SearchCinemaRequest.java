package se.rj.govie.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import se.rj.govie.request.SearchRequest;

public class SearchCinemaRequest extends SearchRequest {

    public SearchCinemaRequest(@JsonProperty("term") String term, @JsonProperty("user") String user) {
        super(term, user);
    }
}
