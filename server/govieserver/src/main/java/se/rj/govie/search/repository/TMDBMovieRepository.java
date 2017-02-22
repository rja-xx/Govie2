package se.rj.govie.search.repository;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import se.rj.govie.model.Movie;
import se.rj.govie.request.SearchMovieRequest;

import java.util.Collections;
import java.util.List;

@Component
public class TMDBMovieRepository implements MovieRepository {

    private static Logger logger = Logger.getLogger(TMDBMovieRepository.class);

    @Override
    public List<Movie> searchInCinemas(SearchMovieRequest request) {
        logger.info("searching for movies");
        return Collections.emptyList();
    }
}
