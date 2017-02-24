package se.rj.govie.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public abstract class GovieObject {

    protected final static ObjectMapper MAPPER = new ObjectMapper();

    public static <T extends GovieObject> T fromJson(byte[] json, Class<T> clazz) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends GovieObject> T fromJson(String res, Class<T> clazz) {
        return fromJson(res.getBytes(), clazz);
    }
}
