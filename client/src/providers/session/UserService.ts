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
        debugger;
        if (this.currentUser() === null) {
            return firebase.auth().signInWithPopup(this.twitterProvider).then(function (authData) {
                var user = new User(authData.user.uid, authData.user.photoURL, authData.user.displayName);
                events.publish("user:login", user);
                firebase.database().ref("govie/users").push(user);
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

    searchUser(val:string, events) {
        if (this.currentUser() !== null) {
            var uid = this.currentUser().uid;
            firebase.database().ref("govie/response/search/user/" + uid).on('value', function fn(snapshot) {
                if (snapshot.val() !== null) {
                    snapshot.ref.off('value');
                    snapshot.ref.remove();
                    var val = snapshot.val();
                    events.publish('user:search:result', val);
                }
            });
            firebase.database().ref("govie/request/search/user").push({user: uid, term: val});
        }
    }
}

