import { Component, ChangeDetectorRef } from '@angular/core';

import { NavController } from 'ionic-angular';
import { UserService } from "../../providers/session/UserService";
import { Events } from 'ionic-angular';
import { ModalController } from 'ionic-angular';
import { LoginPage } from '../login/login';
import {User} from "../../model/user";
import { Moment} from 'moment';
import { Storage } from '@ionic/storage';
import {WallEntry} from "../../model/wallentry";
import {EventService} from "../../providers/event/EventService";


@Component({
    selector: 'page-wall',
    templateUrl: 'wall.html',
    providers: [UserService, EventService]
})
export class WallPage {

    wallEntries:Array<WallEntry>;
    persistedWallEntries:Array<any>;
    error:string;
    i:number;

    constructor(public navCtrl:NavController,
                public event:Events,
                public eventService:EventService,
                public cd:ChangeDetectorRef,
                public modalCtrl:ModalController,
                public userService:UserService,
                private storage:Storage) {


        this.i = 5;
        this.wallEntries = [];
        debugger;
        this.storage.get('goviewall').then((wall) => {
            debugger;
            var items = JSON.parse(wall);
            for (let a in items) {
                var event = items[a];
                this.persistedWallEntries.push(event);
                this.wallEntries.push(new WallEntry(
                    event.id,
                    event.name,
                    event.avatarUrl,
                    event.timestamp,
                    event.title,
                    event.info,
                    event.attendees,
                    event.date,
                    event.poster,
                    event.rater,
                    event.raterImg,
                    event.theater,
                    event.comment,
                    event.rating));
                cd.detectChanges();
            }
        });
        this.event.subscribe('event:wall:new', (event) => {
            debugger;
            this.persistedWallEntries.push(event);
            this.wallEntries.push(new WallEntry(
                event.id,
                event.name,
                event.avatarUrl,
                event.timestamp,
                event.title,
                event.info,
                event.attendees,
                event.date,
                event.poster,
                event.rater,
                event.raterImg,
                event.theater,
                event.comment,
                event.rating));
            var value = JSON.stringify(this.persistedWallEntries);
            this.storage.set('goviewall', value);
            cd.detectChanges();
        });
    }

    ionViewDidLoad() {
        debugger;
        this.event.subscribe('user:login', (user) => {
            console.log(user);
        });

        this.event.subscribe('user:logout', () => {
            this.promptLogin();
        });
        if (this.userService.currentUser() === null) {
            this.promptLogin();
        }
    }

    ionViewDidEnter() {
        debugger;
        this.eventService.subscribeToWall(this.event, this.userService.currentUser());
        console.log('subscribing to wall')
    }


    ionViewWillLeave() {
        debugger;
        this.event.unsubscribe('event:wall:new');
    }

    private promptLogin() {
        if (this.userService.currentUser() === null) {
            let modal = this.modalCtrl.create(LoginPage);
            modal.present();
        }
    };


}
