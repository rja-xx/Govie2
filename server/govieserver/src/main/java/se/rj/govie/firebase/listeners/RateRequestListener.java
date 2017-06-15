package se.rj.govie.firebase.listeners;

import com.google.firebase.database.DataSnapshot;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.rj.govie.firebase.FirebaseAgent;
import se.rj.govie.request.RateRequest;

@Component
public class RateRequestListener extends ChildEventAdapter {

    private static Logger logger = Logger.getLogger(RateRequestListener.class);

    @Autowired
    private FirebaseAgent firebaseAgent;

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        dataSnapshot.getRef().removeValue();
        RateRequest request = RateRequest.fromDataSnapshot(dataSnapshot, RateRequest.class);
        logger.info("Executing rate " + request);
        firebaseAgent.pushRate(request);
    }
}
