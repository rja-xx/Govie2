package se.rj.govie.firebase.listeners;

import com.google.firebase.database.DataSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.rj.govie.firebase.FirebaseAgent;
import se.rj.govie.model.EventFactory;
import se.rj.govie.model.WallEntry;
import se.rj.govie.request.RateRequest;
import se.rj.govie.wall.WallEntryFactory;

@Component
public class RateEventListener extends ChildEventAdapter {

    @Autowired
    private WallEntryFactory wallEntryFactory;

    @Autowired
    private FirebaseAgent firebaseAgent;

    @Autowired
    private EventFactory eventFactory;

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        String ratingUserUID = dataSnapshot.getKey();
        RateRequest rateRequest = RateRequest.fromDataSnapshot(dataSnapshot, RateRequest.class);
        WallEntry wallEntry = wallEntryFactory.fromRateRequest(rateRequest);
        firebaseAgent.issueWallEntry(ratingUserUID, wallEntry);
        sendEventToFriends(rateRequest);
        if (rateRequest.getTwitter()) {
            //// TODO: 30/06/2017  
        }
        if (rateRequest.getFacebook()) {
            //// TODO: 30/06/2017
        }
        //todo
    }


    protected void sendEventToFriends(RateRequest rateRequest) {
        rateRequest.getFriends().forEach(friendUID -> {
            firebaseAgent.pushEvent(eventFactory.fromRateReuqest(rateRequest));
        });
    }
}
