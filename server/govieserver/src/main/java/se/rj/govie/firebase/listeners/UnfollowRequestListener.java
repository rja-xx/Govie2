package se.rj.govie.firebase.listeners;

import com.google.firebase.database.DataSnapshot;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.rj.govie.firebase.FirebaseAgent;
import se.rj.govie.request.FollowRequest;

@Component
public class UnfollowRequestListener extends ChildEventAdapter {

    private static Logger logger = Logger.getLogger(UnfollowRequestListener.class);

    @Autowired
    private FirebaseAgent firebaseAgent;

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        logger.info("Executing unfollow");
        dataSnapshot.getRef().removeValue();
        FollowRequest request = FollowRequest.fromDataSnapshot(dataSnapshot, FollowRequest.class);
        firebaseAgent.pushUnFollow(request.getUser(), request.getFollow());
    }
}
