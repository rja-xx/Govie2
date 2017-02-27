package se.rj.govie.search.repository;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import se.rj.govie.model.Movie;
import se.rj.govie.model.tmdbresponse.TMDBSearchResponse;
import se.rj.govie.request.SearchMovieRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static se.rj.govie.search.repository.TMDBRequestType.now_playing;

@Component
public class TMDBMovieRepository implements MovieRepository {

    public static final Locale REGION = Locale.forLanguageTag("SE");

    private static Logger logger = Logger.getLogger(TMDBMovieRepository.class);


    @Override
    public List<Movie> searchInCinemas(SearchMovieRequest request) {
        logger.info("searching for movies");
        String url = new TMDBRequestBuilder(now_playing).withRegion(REGION).build();
        String res = getTMDBResult(url);
        TMDBSearchResponse result = TMDBSearchResponse.fromJson(res, TMDBSearchResponse.class);
        logger.info(res);
        return result.getResults();
    }

    @Override
    public List<Movie> listInCinemas(int pages) {
        logger.info("Listing movies in theaters");
        List<Movie> result = new ArrayList<>();
        for (int i = 1; i <= pages; i++) {
            String url = new TMDBRequestBuilder(now_playing).withRegion(REGION).withPage(i).build();
            String res = getTMDBResult(url);
            TMDBSearchResponse r = TMDBSearchResponse.fromJson(res, TMDBSearchResponse.class);
            result.addAll(r.getResults());
        }
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
