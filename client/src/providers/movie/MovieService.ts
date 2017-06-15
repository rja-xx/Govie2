import {Injectable} from '@angular/core';
import firebase from 'firebase';
import {Movie} from "../../model/movie";
import {Rating} from "../../model/rating";

@Injectable()
export class MovieService {

    searchMovies(term:string, events, user) {
        firebase.database().ref("govie/response/search/movie/" + user.uid).on('value', function fn(snapshot) {
            if (snapshot.val() !== null) {
                snapshot.ref.off('value');
                snapshot.ref.remove();
                var val = snapshot.val();
                events.publish('movie:search:result', val);
            }
        });
        firebase.database().ref("govie/request/search/movie").push({user: user.uid, term: term});
    }

    submitRating(rating:Rating) {
        firebase.database().ref("govie/request/rate").push(rating.toObject());
    }
}

