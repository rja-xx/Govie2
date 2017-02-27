package se.rj.govie.firebase.listeners;

import com.google.firebase.database.DataSnapshot;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.rj.govie.firebase.FirebaseAgent;
import se.rj.govie.firebase.RequestType;
import se.rj.govie.request.SearchMovieRequest;
import se.rj.govie.request.SearchUserRequest;
import se.rj.govie.search.repository.MovieRepository;

@Component
public class SearchMovieRequestListener extends ChildEventAdapter {

    private static Logger logger = Logger.getLogger(SearchMovieRequestListener.class);

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private FirebaseAgent firebaseAgent;

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        SearchMovieRequest request = SearchUserRequest.fromDataSnapshot(dataSnapshot, SearchMovieRequest.class);
        dataSnapshot.getRef().removeValue();
        firebaseAgent.pushResponse(request.getUser(), RequestType.MOVIE_SEARCH, movieRepository.searchInCinemas(request));
        logger.info("Executed movie search for " + request.getTerm());
    }
}
