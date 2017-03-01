package se.rj.govie.firebase;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.rj.govie.firebase.listeners.UserEventListener;
import se.rj.govie.firebase.listeners.search.SearchCinemaRequestListener;
import se.rj.govie.firebase.listeners.search.SearchMovieRequestListener;
import se.rj.govie.firebase.listeners.search.SearchUserRequestListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Component
public class FirebaseAgent {

    @Autowired
    private UserEventListener userEventListener;

    @Autowired
    private SearchUserRequestListener searchUserRequestListener;

    @Autowired
    private SearchMovieRequestListener searchMovieRequestListener;

    @Autowired
    private SearchCinemaRequestListener searchCinemaRequestListener;

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
    }

    public void disconnect() {

    }

    public void pushResponse(String recipient, RequestType requestType, Object result) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.getReference("govie/response/" + requestType.getResponseQueue() + "/" + recipient).setValue(result);
    }
}
