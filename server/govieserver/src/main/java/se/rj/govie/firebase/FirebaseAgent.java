package se.rj.govie.firebase;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.rj.govie.firebase.listeners.FollowRequestListener;
import se.rj.govie.firebase.listeners.RateRequestListener;
import se.rj.govie.firebase.listeners.UnfollowRequestListener;
import se.rj.govie.firebase.listeners.UserEventListener;
import se.rj.govie.firebase.listeners.search.SearchCinemaRequestListener;
import se.rj.govie.firebase.listeners.search.SearchMovieRequestListener;
import se.rj.govie.firebase.listeners.search.SearchUserRequestListener;
import se.rj.govie.model.Profile;
import se.rj.govie.request.RateRequest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import static com.google.firebase.database.Transaction.success;

@Component
public class FirebaseAgent {

    private static final Transaction.Handler INCREMENT_VALUE_BY_ONE = new Transaction.Handler() {

        @Override
        public Transaction.Result doTransaction(MutableData mutableData) {
            if (mutableData.getValue() == null) {
                mutableData.setValue(1);
            } else {
                mutableData.setValue(mutableData.getValue(Integer.class) + 1);
            }
            return success(mutableData);
        }

        @Override
        public void onComplete(DatabaseError databaseError, boolean commited, DataSnapshot dataSnapshot) {
            System.out.println("saved follow");
        }
    };

    private static final Transaction.Handler DECREMENT_VALUE_BY_ONE = new Transaction.Handler() {

        @Override
        public Transaction.Result doTransaction(MutableData mutableData) {
            if (mutableData.getValue() == null) {
                mutableData.setValue(0);
            } else {
                mutableData.setValue(mutableData.getValue(Integer.class) - 1);
            }
            return success(mutableData);
        }

        @Override
        public void onComplete(DatabaseError databaseError, boolean commited, DataSnapshot dataSnapshot) {
            System.out.println("saved follow");
        }
    };

    @Autowired
    private UserEventListener userEventListener;

    @Autowired
    private SearchUserRequestListener searchUserRequestListener;

    @Autowired
    private SearchMovieRequestListener searchMovieRequestListener;

    @Autowired
    private SearchCinemaRequestListener searchCinemaRequestListener;

    @Autowired
    private FollowRequestListener followRequestListener;

    @Autowired
    private UnfollowRequestListener unfollowRequestListener;

    @Autowired
    private RateRequestListener rateRequestListener;

    public void connect() {
        try {
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/firebase_cert.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
                    .setDatabaseUrl("https://govie-9bbfa.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Connecting to firebase instance failed!", e);
        }
    }

    public void setupListeners() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        setupUserEventListener(firebaseDatabase);
    }

    private void setupUserEventListener(FirebaseDatabase firebaseDatabase) {
        firebaseDatabase.getReference("govie/users").addChildEventListener(userEventListener);
        firebaseDatabase.getReference("govie/request/search/user").addChildEventListener(searchUserRequestListener);
        firebaseDatabase.getReference("govie/request/search/movie").addChildEventListener(searchMovieRequestListener);
        firebaseDatabase.getReference("govie/request/search/cinema").addChildEventListener(searchCinemaRequestListener);
        firebaseDatabase.getReference("govie/request/follow").addChildEventListener(followRequestListener);
        firebaseDatabase.getReference("govie/request/unfollow").addChildEventListener(unfollowRequestListener);
        firebaseDatabase.getReference("govie/request/rate").addChildEventListener(rateRequestListener);
    }

    public void disconnect() {

    }

    public void pushResponse(String recipient, RequestType requestType, Object result) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.getReference("govie/response/" + requestType.getResponseQueue() + "/" + recipient).setValue(result);
    }

    public void pushProfile(Profile profile) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.getReference("govie/profile/" + profile.getUid()).setValue(profile);
    }

    public void pushFollow(String uid, String follow) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.getReference("govie/profile/" + uid + "/follows").runTransaction(INCREMENT_VALUE_BY_ONE);
        firebaseDatabase.getReference("govie/profile/" + follow + "/followers").runTransaction(INCREMENT_VALUE_BY_ONE);
        HashMap<String, String> value = new HashMap<>();
        value.put("follows", "true");
        firebaseDatabase.getReference("govie/followers").child(uid).child(follow).setValue(value);
    }

    public void pushUnFollow(String uid, String follow) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.getReference("govie/profile/" + uid + "/follows").runTransaction(DECREMENT_VALUE_BY_ONE);
        firebaseDatabase.getReference("govie/profile/" + follow + "/followers").runTransaction(DECREMENT_VALUE_BY_ONE);
        firebaseDatabase.getReference("govie/followers").child(uid).child(follow).removeValue();
    }

    public void pushRate(RateRequest request) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.getReference("govie/rate/" + request.getUid()).setValue(request);
    }
}
