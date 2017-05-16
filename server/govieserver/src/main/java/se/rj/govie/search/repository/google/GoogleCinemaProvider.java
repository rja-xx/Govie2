package se.rj.govie.search.repository.google;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.rj.govie.model.Cinema;
import se.rj.govie.model.SearchCinemaRequest;
import se.rj.govie.search.ElasticSearchAgent;
import se.rj.govie.search.index.CinemaIndex;
import se.rj.govie.search.repository.CinemaRepository;

import java.util.List;

import static se.rj.govie.search.repository.google.GoogleMapsSearchTypes.movie_theater;

@Component
public class GoogleCinemaProvider implements CinemaRepository {

    private static final int RADIUS = 10000;

    private static Logger logger = Logger.getLogger(GoogleCinemaProvider.class);

    @Autowired
    private ElasticSearchAgent elasticSearchAgent;

    @Autowired
    private CinemaIndex cinemaIndex;

    @Override
    public List<Cinema> findNearBy(SearchCinemaRequest searchRequest) {
        Double lon = searchRequest.getLon();
        Double lat = searchRequest.getLat();
        String url = new GoogleMapsRequestBuilder(movie_theater).withLocation(lon, lat).withRadius(RADIUS).build();
        CinemasNearByResponse res = CinemasNearByResponse.fromJson(getCinemasInVicinity(url), CinemasNearByResponse.class);
        logger.info("Found " + res.getResults().size() + " cinemas near by");
        elasticSearchAgent.addToIndex(cinemaIndex, res.getResults());
        return res.getResults();
    }


    private String getCinemasInVicinity(String url) {
        try {
            HttpResponse<String> response = Unirest.get(url).asString();
            return response.getBody();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }
}
