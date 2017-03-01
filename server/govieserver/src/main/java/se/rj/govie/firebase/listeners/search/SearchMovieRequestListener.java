package se.rj.govie.firebase.listeners.search;

import com.google.firebase.database.DataSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.rj.govie.firebase.RequestType;
import se.rj.govie.model.Movie;
import se.rj.govie.request.SearchMovieRequest;
import se.rj.govie.search.index.MovieIndex;

import java.util.List;

@Component
public class SearchMovieRequestListener extends SearchRequestListener<SearchMovieRequest, Movie> {

    @Autowired
    private MovieIndex movieIndex;

    @Override
    protected RequestType getSearchType() {
        return RequestType.MOVIE_SEARCH;
    }

    @Override
    protected List<Movie> search(DataSnapshot dataSnapshot) {
        return movieIndex.searchByTitle(getSearchRequest(dataSnapshot));
    }
}
