package se.rj.govie.search.repository;

import org.testng.Assert;
import org.testng.annotations.Test;
import se.rj.govie.model.Movie;
import se.rj.govie.request.SearchMovieRequest;
import se.rj.govie.search.repository.tmdb.TMDBMovieRepository;

import java.util.List;

public class TMDBMovieRepositoryTest {

    @Test
    public void given_when_then() throws Exception {
        List<Movie> movies = new TMDBMovieRepository().searchInCinemas(new SearchMovieRequest("hei", "hopp"));
        Assert.assertFalse(movies.isEmpty());
    }
}