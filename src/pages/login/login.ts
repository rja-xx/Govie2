import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { Events } from 'ionic-angular';
import {ViewController} from "ionic-angular/index";
import {UserService} from "../../providers/session/UserService";

/*
 Generated class for the Login page.

 See http://ionicframework.com/docs/v2/components/#navigation for more info on
 Ionic pages and navigation.
 */
@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
  providers: [UserService]
})
export class LoginPage {

  constructor(public navCtrl:NavController,
              public navParams:NavParams,
              public userService:UserService,
              public viewCtrl:ViewController,
              public events:Events) {
    this.userService.init();
    events.subscribe('user:login', (user) => {
      this.viewCtrl.dismiss();
    });
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad LoginPage');
  }

  login() {
    this.userService.login(this.events);
  }

}
