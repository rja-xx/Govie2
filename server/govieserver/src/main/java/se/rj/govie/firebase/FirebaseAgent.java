package se.rj.govie.firebase;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.rj.govie.firebase.listeners.UserEventListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Component
public class FirebaseAgent {

    @Autowired
    private UserEventListener listener;

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
        DatabaseReference searchUserRequest = firebaseDatabase.getReference("govie/users");
        searchUserRequest.addChildEventListener(listener);
    }

    public void disconnect() {

    }
}
