package se.rj.govie.search.repository.google;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.rj.govie.model.Cinema;
import se.rj.govie.model.SearchCinemaRequest;
import se.rj.govie.search.index.CinemaIndex;
import se.rj.govie.search.repository.CinemaRepository;

import java.util.List;
import java.util.function.Function;

import static se.rj.govie.search.repository.google.GoogleMapsSearchTypes.movie_theater;
import static se.rj.govie.util.Memoizer.memoize;

@Component
public class GoogleCinemaProvider implements CinemaRepository {

    private static final int RADIUS = 1000;

    private static Logger logger = Logger.getLogger(GoogleCinemaProvider.class);

    @Autowired
    private CinemaIndex cinemaIndex;

    private Function<String, String> memoizedLookup = memoize(this::getCinemasInVicinity);

    @Override
    public List<Cinema> findNearBy(SearchCinemaRequest searchRequest) {
        List<Cinema> cinemas;
        Double lon = searchRequest.getLon();
        Double lat = searchRequest.getLat();
        if (lat == null && lon == null) {
            cinemas = cinemaIndex.searchByName(searchRequest.getTerm());
        } else {
            String url = new GoogleMapsRequestBuilder(movie_theater).withLocation(lon, lat).withRadius(RADIUS).build();
            String cinemasInVicinityJSON = memoizedLookup.apply(url);
            CinemasNearByResponse res = CinemasNearByResponse.fromJson(cinemasInVicinityJSON, CinemasNearByResponse.class);
            cinemas = res.getResults();
            logger.info("Found " + cinemas.size() + " cinemas near by");
            cinemas.forEach(cinemaIndex::add);
        }
        return cinemas;
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
