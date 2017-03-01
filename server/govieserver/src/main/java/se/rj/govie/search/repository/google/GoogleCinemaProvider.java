package se.rj.govie.search.repository.google;

import org.springframework.stereotype.Component;
import se.rj.govie.model.Cinema;
import se.rj.govie.model.SearchCinemaRequest;
import se.rj.govie.search.repository.CinemaRepository;

import java.util.List;

@Component
public class GoogleCinemaProvider implements CinemaRepository {

    @Override
    public List<Cinema> findNearBy(SearchCinemaRequest searchRequest) {
        return null;
    }
}
