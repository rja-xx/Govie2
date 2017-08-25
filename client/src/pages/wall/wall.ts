import { Component, ChangeDetectorRef } from '@angular/core';

import { NavController } from 'ionic-angular';
import { UserService } from "../../providers/session/UserService";
import { Events } from 'ionic-angular';
import { ModalController } from 'ionic-angular';
import { LoginPage } from '../login/login';
import {User} from "../../model/user";
import { Moment} from 'moment';
import {WallEntry} from "../../model/wallentry";


@Component({
    selector: 'page-wall',
    templateUrl: 'wall.html',
    providers: [UserService]
})
export class WallPage {

    wallEntries:Array<WallEntry>
    error:string;
    i:number;

    constructor(public navCtrl:NavController,
                public events:Events,
                public cd:ChangeDetectorRef,
                public modalCtrl:ModalController,
                public userService:UserService) {


        this.i = 5;
        this.wallEntries = [];
    }


    ionViewDidLoad() {
        this.events.subscribe('user:login', (user) => {
            console.log(user);
        });

        this.events.subscribe('user:logout', () => {
            this.promptLogin();
        });

        this.events.subscribe('wall:entry:added', (entry) => {
            this.wallEntries.push(entry)
            this.cd.detectChanges();
        });

        if (this.userService.currentUser() === null) {
            this.promptLogin();
        }
    }

    private promptLogin() {
        if (this.userService.currentUser() === null) {
            let modal = this.modalCtrl.create(LoginPage);
            modal.present();
        }
    };


}
