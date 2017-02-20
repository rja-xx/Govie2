package se.rj.govie.firebase.listeners;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public abstract class ChildEventAdapter implements ChildEventListener {

    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
    }

    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
    }

    public void onChildRemoved(DataSnapshot dataSnapshot) {
    }

    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
    }

    public void onCancelled(DatabaseError databaseError) {
    }
}
