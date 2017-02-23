package se.rj.govie.search.repository;

import org.testng.annotations.Test;
import se.rj.govie.request.SearchMovieRequest;

public class TMDBMovieRepositoryTest {

    @Test
    public void given_when_then() throws Exception {
        new TMDBMovieRepository().searchInCinemas(new SearchMovieRequest("hei", "hopp"));
    }
}