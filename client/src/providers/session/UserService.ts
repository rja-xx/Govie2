import {Injectable} from '@angular/core';
import firebase from 'firebase';
import {User} from "../../model/user";

@Injectable()
export class UserService {

    private twitterProvider:any;

    init() {
        this.twitterProvider = new firebase.auth.TwitterAuthProvider();
    }

    login(events) {
        if (this.currentUser() === null) {
            return firebase.auth().signInWithPopup(this.twitterProvider).then(authData=> {
                this.persistUserDetails(authData, events);
            }).catch(function (error) {
                console.log(error);
                events.publish("error:login");
            });
        }
    }

    private persistUserDetails(authData, events) {
        var user = new User(authData.user.uid, authData.user.photoURL, authData.user.displayName);
        debugger;
        firebase.database()
            .ref("govie/users/")
            .child(authData.user.uid).transaction(
            function (currentUserData) {
                if (currentUserData === null) {
                    return user;
                }
                events.publish("user:login", user);
            });
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

    getProfile() {
        var uid = this.currentUser().uid;
        return this.getProfileByUID(uid);
    }

    getProfileByUID(uid) {
        return new Promise(resolve => {
            firebase.database().ref("govie/profile/" + uid).on('value', function fn(snapshot) {
                if (snapshot.val() !== null) {
                    snapshot.ref.off('value');
                    resolve(snapshot.val());
                }
            });
        });
    }

    follow(uid) {
        firebase.database().ref("govie/request/follow").push({user: this.currentUser().uid, follow: uid});
    }

    unfollow(uid) {
        firebase.database().ref("govie/request/unfollow").push({user: this.currentUser().uid, follow: uid});
    }

    getNextProfileChange(uid, callback) {
        firebase.database().ref("govie/profile/" + uid).on('child_changed', function fn(snapshot) {
            if (snapshot.val() !== null) {
                callback(snapshot.key, snapshot.val());
            }
        });
    }

    getIsFollowing(uid) {
        debugger;
        //var value = firebase.database().ref("govie/followers/" + this.currentUser().uid + "/" + uid);
        //firebase.database().ref("govie/followers/" + this.currentUser().uid + "/" + uid).on('child_changed', function fn(snapshot) {
        //    if (snapshot.val() !== null) {
        //        callback(snapshot.key, snapshot.val());
        //    }
        //});
        return false;
    }
}

