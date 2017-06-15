package se.rj.govie.search.repository.google;

import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class GoogleMapsRequestBuilder {

    public static final double ROUND_FACTOR = 10000D;

    private static final String GOOGLE_MAPS_SEARCH_BASE_PATH = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";

    private static final String API_KEY = "AIzaSyBri-BATjtjQwT96yJ9HAerE6Kqy1Vmbxo";

    private Map<String, String> params;

    public GoogleMapsRequestBuilder(GoogleMapsSearchTypes... types) {
        params = new HashMap<>();
        Stream.of(types).forEach(t -> params.put("types", t.name()));
    }

    public GoogleMapsRequestBuilder withLocation(Double lon, Double lat) {
        double longitude = (double) Math.round(lon * ROUND_FACTOR) / ROUND_FACTOR;
        double latitude = (double) Math.round(lat * ROUND_FACTOR) / ROUND_FACTOR;
        params.put("location", longitude + "," + latitude);
        return this;
    }

    public GoogleMapsRequestBuilder withRadius(Integer radius) {
        params.put("radius", String.valueOf(radius));
        return this;
    }

    public GoogleMapsRequestBuilder withName(String name) {
        params.put("name", String.valueOf(name));
        return this;
    }

    public String build() {
        StringBuilder sb = new StringBuilder();
        sb.append(GOOGLE_MAPS_SEARCH_BASE_PATH);
        sb.append("?");
        if (!CollectionUtils.isEmpty(params)) {
            params.entrySet().forEach(e -> sb.append(e.getKey()).append("=").append(e.getValue()).append("&"));
        }
        sb.append("key=").append(API_KEY);
        return sb.toString();
    }
}
