import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { Moment} from 'moment';
import {DataService} from "../../providers/data/DataService";
import {User} from "../../model/user";
import {Event} from "../../model/event";

/*
 Generated class for the Events page.

 See http://ionicframework.com/docs/v2/components/#navigation for more info on
 Ionic pages and navigation.
 */
@Component({
  selector: 'page-events',
  templateUrl: 'events.html',
  providers: [DataService]
})
export class EventsPage {

  events:Array<Event>;

  constructor(public navCtrl:NavController,
              public navParams:NavParams,
              data:DataService) {
    var user = new User('roger', 'assets/icons/govie_gubbe_red.png', 'Roger');
    data.init();
    this.events = [
      new Event(user, new Date(), 'Liked post', 'Roger liked your post on Terminator 2'),
      new Event(user, new Date(), 'Liked post', 'Roger liked your post on Terminator 2'),
      new Event(user, new Date(), 'Liked post', 'Roger liked your post on Terminator 2'),
      new Event(user, new Date(), 'Liked post', 'Roger liked your post on Terminator 2'),
      new Event(user, new Date(), 'Liked post', 'Roger liked your post on Terminator 2'),
      new Event(user, new Date(), 'Liked post', 'Roger liked your post on Terminator 2'),
      new Event(user, new Date(), 'Liked post', 'Roger liked your post on Terminator 2'),
      new Event(user, new Date(), 'Liked post', 'Roger liked your post on Terminator 2'),
      new Event(user, new Date(), 'Liked post', 'Roger liked your post on Terminator 2'),
      new Event(user, new Date(), 'Liked post', 'Roger liked your post on Terminator 2'),
      new Event(user, new Date(), 'Liked post', 'Roger liked your post on Terminator 2'),
      new Event(user, new Date(), 'Liked post', 'Roger liked your post on Terminator 2'),
      new Event(user, new Date(), 'Liked post', 'Roger liked your post on Terminator 2'),
      new Event(user, new Date(), 'Liked post', 'Roger liked your post on Terminator 2')
    ];

  }



  ionViewDidLoad() {
    console.log('ionViewDidLoad EventsPage');
    //this.data.db.child('staticData').on('value', data => {
    //  this.events[1].info = data.val().text;
    //});
  }

}
