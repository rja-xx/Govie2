import { Component, ChangeDetectorRef} from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { Events } from 'ionic-angular';
import { ModalController } from 'ionic-angular';
import {EditProfilePage} from "../edit-profile/edit-profile";
import {UserService} from "../../providers/session/UserService";
import {Profile} from "../../model/profile";
import {Movie} from "../../model/movie";

@Component({
    selector: 'page-profile',
    templateUrl: 'profile.html',
    providers: [UserService]
})
export class ProfilePage {

    uid:string;

    constructor(public navCtrl:NavController,
                public navParams:NavParams,
                public userService:UserService,
                public events:Events,
                public modalCtrl:ModalController,
                cd:ChangeDetectorRef) {
        this.uid = this.userService.currentUser().uid;
    }

    ionViewDidLoad() {
        console.log('ionViewDidLoad ProfilePage');
    }

}
