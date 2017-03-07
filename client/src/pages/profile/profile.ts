import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { Events } from 'ionic-angular';
import { ModalController } from 'ionic-angular';
import {EditProfilePage} from "../edit-profile/edit-profile";
import {UserService} from "../../providers/session/UserService";

@Component({
    selector: 'page-profile',
    templateUrl: 'profile.html',
    providers: [UserService]
})
export class ProfilePage {

    constructor(public navCtrl:NavController, public navParams:NavParams, public userService:UserService, public modalCtrl:ModalController) {
    }

    ionViewDidLoad() {
        console.log('ionViewDidLoad ProfilePage');
    }

    promptEdit() {
        if (this.userService.currentUser() !== null) {
            let modal = this.modalCtrl.create(EditProfilePage);
            modal.present();
        }
    };

}
