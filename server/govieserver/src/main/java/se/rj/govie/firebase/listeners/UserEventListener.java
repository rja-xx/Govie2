package se.rj.govie.firebase.listeners;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.rj.govie.model.User;
import se.rj.govie.search.index.UserIndex;

import java.util.Map;

@Component
public class UserEventListener implements ChildEventListener {

    public static final GenericTypeIndicator<Map<String, String>> TYPE = new GenericTypeIndicator<Map<String, String>>() {
    };

    private static Logger logger = Logger.getLogger(UserEventListener.class);

    @Autowired
    private UserIndex userIndex;

    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        logger.info("add user to index " + s);
        userIndex.add(User.fromDataSnapshot(dataSnapshot, User.class));
    }

    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        logger.info("changed");
        logger.info(dataSnapshot.getKey());
        logger.info(dataSnapshot.getValue());
        logger.info(s);
    }

    public void onChildRemoved(DataSnapshot dataSnapshot) {
        logger.info("removed");
        logger.info(dataSnapshot.getKey());
        logger.info(dataSnapshot.getValue());
    }

    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
        logger.info(dataSnapshot.getKey());
        logger.info(dataSnapshot.getValue());
        logger.info(s);
    }

    public void onCancelled(DatabaseError databaseError) {
        logger.info(databaseError.getMessage());
    }
}
