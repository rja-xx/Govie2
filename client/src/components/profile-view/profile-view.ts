import { Component, Input, ChangeDetectorRef } from '@angular/core';
import {Profile} from "../../model/profile";
import {EditProfilePage} from "../../pages/edit-profile/edit-profile";
import {ModalController} from "ionic-angular/index";
import {Events} from "ionic-angular/index";
import {UserService} from "../../providers/session/UserService";
import {NavParams} from "ionic-angular/index";
import {NavController} from "ionic-angular/index";
import User = firebase.User;

/**
 * Generated class for the ProfileView component.
 *
 * See https://angular.io/docs/ts/latest/api/core/index/ComponentMetadata-class.html
 * for more info on Angular Components.
 */
@Component({
    selector: 'profile-view',
    templateUrl: 'profile-view.html',
    providers: [UserService]
})
export class ProfileView {

    @Input('uid') uid:string;

    profile:Profile;
    ownProfile:boolean;

    constructor(public navCtrl:NavController,
                public navParams:NavParams,
                public userService:UserService,
                public events:Events,
                public modalCtrl:ModalController,
                public cd:ChangeDetectorRef) {
        console.log('Hello ProfileView Component');
        this.profile = new Profile({});
        this.ownProfile = false;
    }

    ngAfterViewInit() {

        console.log(this.uid);
        this.ownProfile = this.uid === this.userService.currentUser().uid;
        this.userService.getProfileByUID(this.uid).then(res => {
            this.profile = new Profile(res);
            this.cd.detectChanges();
        });
    }

    promptEdit() {
        if (this.userService.currentUser() !== null) {
            let modal = this.modalCtrl.create(EditProfilePage);
            modal.present();
        }
    };

}

