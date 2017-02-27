package se.rj.govie.search.repository;

import se.rj.govie.model.Movie;
import se.rj.govie.request.SearchMovieRequest;

import java.util.List;

public interface MovieRepository {

    List<Movie> searchInCinemas(SearchMovieRequest request);

    List<Movie> listInCinemas(int pages);
}
