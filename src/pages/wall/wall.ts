import { Component, ChangeDetectorRef } from '@angular/core';

import { NavController } from 'ionic-angular';
import { UserService } from "../../providers/session/UserService";
import { Events } from 'ionic-angular';
import { ModalController } from 'ionic-angular';
import { LoginPage } from '../login/login';


@Component({
  selector: 'page-wall',
  templateUrl: 'wall.html'
})
export class WallPage {

  user:string;
  error:string;

  constructor(public navCtrl:NavController,
              public events:Events,
              cd:ChangeDetectorRef,
              public modalCtrl:ModalController) {
    this.user = 'hei';
    this.user = 'sann';
    events.subscribe('user:login', (user) => {
      console.log(user);
      this.user = user.name;
      cd.detectChanges();
    });

  }


  //logout(event) {
  //  console.log("log out " + event);
  //  this.userService.logout(this.events);
  //  this.user = '';
  //}

  ionViewDidLoad() {
    let modal = this.modalCtrl.create(LoginPage);
    modal.present();
  }

}
