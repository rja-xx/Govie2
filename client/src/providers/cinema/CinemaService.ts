import {Injectable} from '@angular/core';
import firebase from 'firebase';
import {Movie} from "../../model/movie";

@Injectable()
export class CinemaService {

    searchCinemaInVicinity(term:string, lat:number, lon:number, events, user) {
        firebase.database().ref("govie/response/search/cinema/" + user.uid).on('value', function fn(snapshot) {
            if (snapshot.val() !== null) {
                snapshot.ref.off('value');
                snapshot.ref.remove();
                var val = snapshot.val();
                events.publish('cinema:search:result', val);
            }
        });
        firebase.database().ref("govie/request/search/cinema").push({user: user.uid, term: term, lon: lon, lat: lat});
    }

}

