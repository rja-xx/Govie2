package se.rj.govie.search.repository;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import se.rj.govie.model.Movie;
import se.rj.govie.request.SearchMovieRequest;

import java.util.ArrayList;
import java.util.List;

import static se.rj.govie.search.repository.TMDBRequestType.now_playing;

@Component
public class TMDBMovieRepository implements MovieRepository {

    private static Logger logger = Logger.getLogger(TMDBMovieRepository.class);


    @Override
    public List<Movie> searchInCinemas(SearchMovieRequest request) {
        logger.info("searching for movies");
        String url = new TMDBRequestBuilder(now_playing).build();
        JsonNode node = getTMDBResult(url);
        List<Movie> res = new ArrayList<>();
        return res;
    }

    private JsonNode getTMDBResult(String url) {
        try {
            logger.info(url);
            HttpResponse<JsonNode> response = Unirest.get(url).asJson();
            return response.getBody();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }
}
