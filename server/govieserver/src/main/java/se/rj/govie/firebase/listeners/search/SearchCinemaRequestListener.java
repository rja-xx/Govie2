package se.rj.govie.firebase.listeners.search;

import com.google.firebase.database.DataSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.rj.govie.firebase.RequestType;
import se.rj.govie.model.Cinema;
import se.rj.govie.model.SearchCinemaRequest;
import se.rj.govie.search.repository.CinemaRepository;

import java.util.List;

import static se.rj.govie.firebase.RequestType.CINEMA_SEARCH;

@Component
public class SearchCinemaRequestListener extends SearchRequestListener<SearchCinemaRequest, Cinema> {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    protected RequestType getSearchType() {
        return CINEMA_SEARCH;
    }

    @Override
    protected List<Cinema> search(DataSnapshot dataSnapshot) {
        return cinemaRepository.findNearBy(getSearchRequest(dataSnapshot));
    }
}
