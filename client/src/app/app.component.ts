import { Component } from '@angular/core';
import { Platform } from 'ionic-angular';
//import { StatusBar, Splashscreen } from 'ionic-native';

import {Splash} from "../pages/splash/splash";
import firebase from 'firebase';
import {TabsPage} from "../pages/tabs/tabs";
import {Events} from "ionic-angular/index";


@Component({
    templateUrl: 'app.html'
})
export class MyApp {
    rootPage = Splash;

    constructor(platform:Platform,
                public event:Events) {
        platform.ready().then(() => {
            // Okay, so the platform is ready and our plugins are available.
            // Here you can do any higher level native things you might need.
            //StatusBar.styleDefault();
            //Splashscreen.hide();
            const fbConf = {
                apiKey: "AIzaSyD_cBbjm_HfFCWDnTheHSK9AK37ipDdcW4",
                authDomain: "govie-9bbfa.firebaseapp.com",
                databaseURL: "https://govie-9bbfa.firebaseio.com",
                storageBucket: "govie-9bbfa.appspot.com"
            };

            firebase.initializeApp(fbConf);
            console.log('Firebase initiated!');
            this.event.publish('event:firebase:initiated', true);
        });
    }
}
