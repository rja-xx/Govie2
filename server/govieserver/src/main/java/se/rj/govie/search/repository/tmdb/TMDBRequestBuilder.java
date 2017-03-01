package se.rj.govie.search.repository.tmdb;

import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TMDBRequestBuilder {

    private static final String TMDB_MOVIE_BASE_PATH = "https://api.themoviedb.org/3/movie/";

    private static final String API_KEY = "f01976dbf3d2f2e2929368fe98ef4aea";

    private final TMDBRequestType type;

    private Map<String, String> params;

    public TMDBRequestBuilder(TMDBRequestType type) {
        this.type = type;
        params = new HashMap<>();
    }

    public TMDBRequestBuilder withPage(int page) {
        params.put("page", String.valueOf(page));
        return this;
    }

    public TMDBRequestBuilder withRegion(Locale region) {
        params.put("region", region.getISO3Country());
        return this;
    }

    public TMDBRequestBuilder withLanguage(Locale locale) {
        params.put("language", locale.getDisplayLanguage());
        return this;
    }

    public String build() {
        StringBuilder sb = new StringBuilder();
        sb.append(TMDB_MOVIE_BASE_PATH).append(type);
        sb.append("?");
        if (!CollectionUtils.isEmpty(params)) {
            params.entrySet().forEach(e -> sb.append(e.getKey()).append("=").append(e.getValue()).append("&"));
        }
        sb.append("api_key=").append(API_KEY);
        return sb.toString();
    }
}
