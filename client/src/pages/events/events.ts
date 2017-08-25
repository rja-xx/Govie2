import { Component, ChangeDetectorRef } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { Moment} from 'moment';
import {DataService} from "../../providers/data/DataService";
import {User} from "../../model/user";
import {Event} from "../../model/event";
import {Events} from "ionic-angular/index";
import {CinemaService} from "../../providers/cinema/CinemaService";
import {Movie} from "../../model/movie";
import {Cinema} from "../../model/cinema";
import {EventService} from "../../providers/event/EventService";
import {UserService} from "../../providers/session/UserService";

/*
 Generated class for the Events page.

 See http://ionicframework.com/docs/v2/components/#navigation for more info on
 Ionic pages and navigation.
 */
@Component({
    selector: 'page-events',
    templateUrl: 'events.html',
    providers: [EventService, UserService]
})
export class EventsPage {

    events:Array<Event>;

    constructor(public navCtrl:NavController,
                public eventService:EventService,
                private event:Events,
                public userService:UserService,
                cd:ChangeDetectorRef) {
        this.events = [];
        this.event.subscribe('event:event:new', (event) => {
            console.log(event);
            this.events.push(new Event(event.name, event.avatarUrl, event.date, event.type, "yeah"));
            cd.detectChanges();
        });
    }

    ionViewDidEnter() {
        this.events = [];
        this.eventService.subscribeToEvents(this.event, this.userService.currentUser());
    }

    ionViewWillLeave() {
        this.event.unsubscribe('event:event:new');
    }

}
