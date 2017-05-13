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
    mayFollow:boolean;
    mayUnFollow:boolean;

    constructor(public navCtrl:NavController,
                public navParams:NavParams,
                public userService:UserService,
                public events:Events,
                public modalCtrl:ModalController,
                public cd:ChangeDetectorRef) {
        console.log('Hello ProfileView Component');
        this.profile = new Profile({});
        this.ownProfile = false;
        this.mayFollow = false;
        this.mayUnFollow = false;
        this.events.subscribe('profile:change', value => {
            console.log('detected profile change');
            if (value.key === 'followers') {
                this.profile.followers = value.val;
            } else if (value.key === 'follows') {
                this.profile.follows = value.val;
            } else {
                return;
            }
            this.cd.detectChanges();
        });
    }

    ngAfterViewInit() {
        console.log("init called with " + this.uid);
        this.ownProfile = this.uid === this.userService.currentUser().uid;
        this.userService.getProfileByUID(this.uid).then(res => {
            this.profile = new Profile(res);
            this.cd.detectChanges();
        });
        this.userService.getIsFollowing(this.uid).then(res => {
            if (res) {
                this.mayFollow = false;
                this.mayUnFollow = !this.ownProfile;
            } else {
                this.mayFollow = !this.ownProfile;
                this.mayUnFollow = false;
            }
            this.cd.detectChanges();
        });
    }

    ngOnDestroy() {
        console.log("on destroy called");
    }

    promptEdit() {
        if (this.userService.currentUser() !== null) {
            let modal = this.modalCtrl.create(EditProfilePage);
            modal.present();
        }
    };

    follow() {
        console.log(this.userService.currentUser().uid + " follow " + this.profile.uid)
        this.userService.follow(this.profile.uid);
        this.mayFollow = false;
        this.mayUnFollow = true;
        this.profile.followers = this.profile.followers + 1;
        this.cd.detectChanges();
    }

    unfollow() {
        console.log(this.userService.currentUser().uid + " unfollow " + this.profile.uid)
        this.userService.unfollow(this.profile.uid);
        this.mayFollow = true;
        this.mayUnFollow = false;
        this.profile.followers = this.profile.followers - 1;
        this.cd.detectChanges();
    }
}

