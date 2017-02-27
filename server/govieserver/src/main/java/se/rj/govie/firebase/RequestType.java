package se.rj.govie.firebase;

public enum RequestType {
    USER_SEARCH("search/user"), MOVIE_SEARCH("search/movie");

    private final String responseQueue;

    RequestType(String responseQueue) {
        this.responseQueue = responseQueue;
    }

    public String getResponseQueue() {
        return responseQueue;
    }

}
