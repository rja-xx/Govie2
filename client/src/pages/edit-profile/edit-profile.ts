import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import {UserService} from "../../providers/session/UserService";
import {Events} from "ionic-angular/index";
import {ViewController} from "ionic-angular/index";

/*
 Generated class for the EditProfile page.

 See http://ionicframework.com/docs/v2/components/#navigation for more info on
 Ionic pages and navigation.
 */
@Component({
    selector: 'page-edit-profile',
    templateUrl: 'edit-profile.html',
    providers: [UserService]
})
export class EditProfilePage {

    constructor(public navCtrl:NavController, public navParams:NavParams, public userService:UserService, public events:Events,
                public viewCtrl:ViewController) {

        events.subscribe('user:logout', (_) => {
            this.viewCtrl.dismiss();
        });
    }

    ionViewDidLoad() {
        console.log('ionViewDidLoad EditProfilePage');
    }


    logout() {
        console.log('logging out');
        this.userService.logout(this.events);
    }

}
