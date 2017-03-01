package se.rj.govie.firebase;

public enum RequestType {
    USER_SEARCH("search/user"), MOVIE_SEARCH("search/movie"), CINEMA_SEARCH("search/cinema");

    private final String responseQueue;

    RequestType(String responseQueue) {
        this.responseQueue = responseQueue;
    }

    public String getResponseQueue() {
        return responseQueue;
    }

}
