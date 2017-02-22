package se.rj.govie.firebase;

import com.google.firebase.database.DataSnapshot;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.rj.govie.firebase.listeners.ChildEventAdapter;
import se.rj.govie.request.SearchUserRequest;
import se.rj.govie.search.index.UserIndex;

@Component
public class SearchUserRequestListener extends ChildEventAdapter {

    private static Logger logger = Logger.getLogger(SearchUserRequestListener.class);

    @Autowired
    private UserIndex userIndex;

    @Autowired
    private FirebaseAgent firebaseAgent;

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        SearchUserRequest request = SearchUserRequest.fromDataSnapshot(dataSnapshot, SearchUserRequest.class);
        dataSnapshot.getRef().removeValue();
        firebaseAgent.pushResponse(request.getUser(), RequestType.USER_SEARCH, userIndex.searchByName(request));
        logger.info("Executed user search for " + request.getTerm());
    }
}
