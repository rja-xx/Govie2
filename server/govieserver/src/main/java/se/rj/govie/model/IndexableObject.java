package se.rj.govie.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.database.DataSnapshot;

import java.io.IOException;

import static java.lang.String.valueOf;

public abstract class IndexableObject {

    private final static ObjectMapper MAPPER = new ObjectMapper();

    private String id;

    public IndexableObject(String id) {
        this.id = id;
    }

    public static <T extends IndexableObject> T fromJson(byte[] json, Class<T> clazz) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends IndexableObject> T fromDataSnapshot(DataSnapshot dataSnapshot, Class<T> clazz) {
        try {
            return MAPPER.readValue(MAPPER.writeValueAsBytes(dataSnapshot.getValue()), clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] toJson() {
        try {
            return MAPPER.writeValueAsBytes(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return valueOf(toJson());
    }

    public abstract String getIndex();

    public abstract String getType();

    public abstract String getId();
}
