import { Component } from '@angular/core';
import { Platform } from 'ionic-angular';
//import { StatusBar, Splashscreen } from 'ionic-native';
import { TabsPage } from '../pages/tabs/tabs';
import firebase from 'firebase';


@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  rootPage = TabsPage;

  constructor(platform: Platform) {
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
    });
  }
}
