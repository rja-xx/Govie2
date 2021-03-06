package se.rj.govie.search.repository.tmdb;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import se.rj.govie.model.Movie;
import se.rj.govie.model.tmdbresponse.TMDBSearchResponse;
import se.rj.govie.request.SearchMovieRequest;
import se.rj.govie.search.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class TMDBMovieRepository implements MovieRepository {

    private static final Locale REGION = Locale.forLanguageTag("SE");

    private static Logger logger = Logger.getLogger(TMDBMovieRepository.class);

    @Override
    public List<Movie> searchInCinemas(SearchMovieRequest request) {
        logger.info("searching for movies");
        String url = new TMDBRequestBuilder(TMDBRequestType.now_playing).withRegion(REGION).build();
        String res = getTMDBResult(url);
        TMDBSearchResponse result = TMDBSearchResponse.fromJson(res, TMDBSearchResponse.class);
        logger.info(res);
        return result.getResults();
    }

    @Override
    public List<Movie> listInCinemas(int page) {
        logger.info("Listing movies in theaters");
        List<Movie> result = new ArrayList<>();
        String url = new TMDBRequestBuilder(TMDBRequestType.now_playing).withRegion(REGION).withPage(page).build();
        String res = getTMDBResult(url);
        TMDBSearchResponse r = TMDBSearchResponse.fromJson(res, TMDBSearchResponse.class);
        result.addAll(r.getResults());
        return result;
    }

    private String getTMDBResult(String url) {
        try {
            logger.info(url);
            HttpResponse<String> response = Unirest.get(url).asString();
            return response.getBody();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }
}
