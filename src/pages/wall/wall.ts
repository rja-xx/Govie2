import { Component, ChangeDetectorRef } from '@angular/core';

import { NavController } from 'ionic-angular';
import { UserService } from "../../providers/session/UserService";
import { Events } from 'ionic-angular';
import { ModalController } from 'ionic-angular';
import { LoginPage } from '../login/login';
import {User} from "../../model/user";
import { Moment} from 'moment';


@Component({
    selector: 'page-wall',
    templateUrl: 'wall.html',
    providers: [UserService]
})
export class WallPage {

    user:string;
    error:string;
    i:number;

    constructor(public navCtrl:NavController,
                public events:Events,
                cd:ChangeDetectorRef,
                public modalCtrl:ModalController,
                public userService:UserService,
                public moment:Moment) {
        this.user = 'hei';
        this.user = 'sann';

        events.subscribe('user:login', (user) => {
            console.log(user);
            this.user = user.name;
            cd.detectChanges();
        });

        events.subscribe('user:logout', () => {
            this.promptLogin();
            cd.detectChanges();
        });

        this.i = 5;
        setTimeout(_ => this.i = 8, 5000);
    }


    ionViewDidLoad() {
        this.promptLogin();
    }

    timeAgo() {
        return this.i;
    }

    private promptLogin() {
        if (this.userService.currentUser() === null) {
            let modal = this.modalCtrl.create(LoginPage);
            modal.present();
        }
    };


}
