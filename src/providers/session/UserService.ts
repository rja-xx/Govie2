import {Injectable} from '@angular/core';
import firebase from 'firebase';
import {User} from "../../model/user";

@Injectable()
export class UserService {

    private twitterProvider:any;
    public user:User;

    init() {
        this.twitterProvider = new firebase.auth.TwitterAuthProvider();
    }

    login(events) {
        if (this.currentUser() === null) {
            return firebase.auth().signInWithPopup(this.twitterProvider).then(function (authData) {
                events.publish("user:login", new User(authData.user.uid, authData.user.photoURL, authData.user.displayName));
            }).catch(function (error) {
                console.log(error);
                events.publish("error:login");
            });
        }
    }

    logout(events) {
        if (this.currentUser()) {
            return firebase.auth().signOut().then(function () {
                events.publish("user:logout");
            }, function (error) {
                console.log(error);
                events.publish("error:logout");
            });
        }
    }

    currentUser() {
        return firebase.auth().currentUser;
    }
}

