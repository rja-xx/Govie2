import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import {UserService} from "../../providers/session/UserService";
import { Events } from 'ionic-angular';

/*
 Generated class for the Profile page.

 See http://ionicframework.com/docs/v2/components/#navigation for more info on
 Ionic pages and navigation.
 */
@Component({
    selector: 'page-profile',
    templateUrl: 'profile.html',
    providers: [UserService]
})
export class ProfilePage {

    constructor(public navCtrl:NavController, public navParams:NavParams, public userService:UserService, public events:Events) {
    }

    ionViewDidLoad() {
        console.log('ionViewDidLoad ProfilePage');
    }

    logout() {
        this.userService.logout(this.events);
    }

}
