package se.rj.govie.search.repository.google;

import org.testng.Assert;
import org.testng.annotations.Test;
import se.rj.govie.model.Cinema;
import se.rj.govie.model.SearchCinemaRequest;

import java.util.List;

public class GoogleCinemaProviderTest {

    @Test
    public void givenRequestForCincemasNearBy_whenProcessed_thenReturnItResults() throws Exception {
        List<Cinema> cinemas = new GoogleCinemaProvider().findNearBy(new SearchCinemaRequest("", "user", 59.8572422, 17.6343226));
        Assert.assertFalse(cinemas.isEmpty());
    }
}