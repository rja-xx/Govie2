package se.rj.govie.firebase;

public enum RequestType {
    USER_SEARCH("search/user");

    private final String responseQueue;

    RequestType(String responseQueue) {
        this.responseQueue = responseQueue;
    }

    public String getResponseQueue() {
        return responseQueue;
    }

}
