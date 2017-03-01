package se.rj.govie.search.repository;

import se.rj.govie.model.Cinema;
import se.rj.govie.model.SearchCinemaRequest;

import java.util.List;

public interface CinemaRepository {
    List<Cinema> findNearBy(SearchCinemaRequest searchRequest);
}
