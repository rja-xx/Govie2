package se.rj.govie.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.firebase.database.DataSnapshot;

import java.io.IOException;

public abstract class FirebaseObject extends GovieObject {

    public static <T extends FirebaseObject> T fromDataSnapshot(DataSnapshot dataSnapshot, Class<T> clazz) {
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
}
