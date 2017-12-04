import { Component } from '@angular/core';
import { TabsPage } from '../tabs/tabs';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { Events } from 'ionic-angular';

/**
 * Generated class for the Splash page.
 *
 * See http://ionicframework.com/docs/components/#navigation for more info
 * on Ionic pages and navigation.
 */
@Component({
    selector: 'page-splash',
    templateUrl: 'splash.html',
})
export class Splash {

    constructor(public navCtrl:NavController, public navParams:NavParams, public event:Events) {
        this.event.subscribe('event:firebase:initiated', (event) => {
            this.navCtrl.push(TabsPage);
        });
    }

    ionViewDidLoad() {
        console.log('ionViewDidLoad Splash');
    }

    ionViewDidEnter() {
        console.log('splash entered')
    }
}
