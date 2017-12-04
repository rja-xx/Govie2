import {Injectable} from '@angular/core';
import firebase from 'firebase';
import {Event} from "../../model/event";

@Injectable()
export class EventService {

    subscribeToEvents(events, user) {
        firebase.database().ref("govie/event/" + user.uid).on('value', function fn(snapshot) {
            var value = snapshot.val();
            if (value !== null) {
                for (let i in value) {
                    events.publish('event:event:new', snapshot.child(i).val());
                }
            }
        });
    }


    subscribeToWall(events, user) {
        firebase.database().ref("govie/wall/" + user.uid).on('value', function fn(snapshot) {
            var value = snapshot.val();
            if (value !== null) {
                for (let i in value) {
                    events.publish('event:wall:new', snapshot.child(i).val());
                }
            }
        });
    }

}

