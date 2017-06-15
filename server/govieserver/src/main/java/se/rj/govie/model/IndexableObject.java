package se.rj.govie.model;

import static java.lang.String.valueOf;

public abstract class IndexableObject extends FirebaseObject {

    protected String id;

    public IndexableObject(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return valueOf(toJson());
    }

    public abstract String getType();

    public abstract String getId();
}
