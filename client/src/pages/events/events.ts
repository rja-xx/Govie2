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
import { Storage } from '@ionic/storage';
import _ from 'underscore';


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
    persistedEvents:Array<any>;

    constructor(public navCtrl:NavController,
                public eventService:EventService,
                private event:Events,
                private storage:Storage,
                public userService:UserService,
                cd:ChangeDetectorRef) {
        this.events = [];
        this.persistedEvents = [];
        this.storage.get('govieevents').then((events) => {
            var items = JSON.parse(events);
            for (let a in items) {
                var event = items[a];
                this.persistedEvents.push(event);
                this.events.push(new Event(event.name, event.avatarUrl, event.date, event.type, "yeah"));
                cd.detectChanges();
            }
        });
        this.event.subscribe('event:event:new', (event) => {
            this.persistedEvents.push(event);
            this.events.push(new Event(event.name, event.avatarUrl, event.date, event.type, "yeah"));
            var value = JSON.stringify(this.persistedEvents);
            this.storage.set('govieevents', value);
            cd.detectChanges();
        });
    }

    ionViewDidEnter() {
        this.eventService.subscribeToEvents(this.event, this.userService.currentUser());
    }

    ionViewWillLeave() {
        this.event.unsubscribe('event:event:new');
    }

}
