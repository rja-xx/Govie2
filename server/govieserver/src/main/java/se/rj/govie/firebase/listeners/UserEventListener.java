package se.rj.govie.firebase.listeners;

import com.google.firebase.database.DataSnapshot;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.rj.govie.firebase.FirebaseAgent;
import se.rj.govie.model.Profile;
import se.rj.govie.model.User;
import se.rj.govie.search.index.UserIndex;

@Component
public class UserEventListener extends ChildEventAdapter {

    private static Logger logger = Logger.getLogger(UserEventListener.class);

    @Autowired
    private UserIndex userIndex;

    @Autowired
    private FirebaseAgent firebaseAgent;

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        logger.info("add user to index " + dataSnapshot);
        User user = User.fromDataSnapshot(dataSnapshot, User.class);
        userIndex.add(user);
        firebaseAgent.pushProfile(new Profile(user.getUid(), user.getAvatarUrl(), user.getName(), user.getName()));
    }
}
