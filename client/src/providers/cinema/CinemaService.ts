import {Injectable} from '@angular/core';
import firebase from 'firebase';
import {Movie} from "../../model/movie";

@Injectable()
export class CinemaService {

    searchCinemaInVicinity(lat:number, lon:number, events, user) {
        firebase.database().ref("govie/response/search/cinema/" + user.uid).on('value', function fn(snapshot) {
            if (snapshot.val() !== null) {
                snapshot.ref.off('value');
                snapshot.ref.remove();
                var val = snapshot.val();
                events.publish('cinema:search:result', val);
            }
        });
        //firebase.database().ref("govie/request/search/cinema").push({user: user.uid, lon: lon, lat: lat});
        firebase.database().ref("govie/request/search/cinema").push({user: user.uid, lon: 59.8574924, lat: 17.6354814});
    }

    searchCinema(term, events, user) {
        firebase.database().ref("govie/response/search/cinema/" + user.uid).on('value', function fn(snapshot) {
            if (snapshot.val() !== null) {
                snapshot.ref.off('value');
                snapshot.ref.remove();
                var val = snapshot.val();
                events.publish('cinema:search:result', val);
            }
        });
        firebase.database().ref("govie/request/search/cinema").push({user: user.uid, term: term});
    }

}

