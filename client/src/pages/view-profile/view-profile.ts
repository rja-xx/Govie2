import { Component, ChangeDetectorRef } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import {ViewController} from "ionic-angular/index";
import {Profile} from "../../model/profile";
import {Events} from "ionic-angular/index";
import {UserService} from "../../providers/session/UserService";
import {User} from "../../model/user";

/**
 * Generated class for the ViewProfile page.
 *
 * See http://ionicframework.com/docs/components/#navigation for more info
 * on Ionic pages and navigation.
 */
@IonicPage()
@Component({
    selector: 'page-view-profile',
    templateUrl: 'view-profile.html',
})
export class ViewProfile {

    uid:string;

    constructor(public navCtrl:NavController,
                public navParams:NavParams,
                public events:Events,
                public viewCtrl:ViewController,
                cd:ChangeDetectorRef) {
        this.uid = navParams.get('uid');
    }

    ionViewDidLoad() {
        console.log('ionViewDidLoad ViewProfile');
    }

    close() {
        console.log('close')
        this.viewCtrl.dismiss();
    }
}
