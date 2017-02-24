package se.rj.govie.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchMovieRequest extends SearchRequest {


    public SearchMovieRequest(@JsonProperty("term") String term,
                              @JsonProperty("user") String user) {
        super(term, user);
    }
}
