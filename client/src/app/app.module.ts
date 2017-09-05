import { NgModule, ErrorHandler } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { Ionic2RatingModule } from 'ionic2-rating';
import { MyApp } from './app.component';
import { TabsPage } from '../pages/tabs/tabs';
import { SearchPage } from '../pages/search/search';
import { ProfilePage } from '../pages/profile/profile';
import { GoviePage } from '../pages/govie/govie';
import { WallPage } from '../pages/wall/wall';
import { LoginPage } from '../pages/login/login';
import { EventsPage } from '../pages/events/events';
import { EditProfilePage } from "../pages/edit-profile/edit-profile";
import {ViewProfile} from "../pages/view-profile/view-profile";
import {ProfileView} from "../components/profile-view/profile-view";
import {RatingList} from "../components/rating-list/rating-list";
import { Geolocation } from '@ionic-native/geolocation';
import { IonicStorageModule } from '@ionic/storage';


@NgModule({
    declarations: [
        MyApp,
        SearchPage,
        GoviePage,
        ProfilePage,
        WallPage,
        EventsPage,
        TabsPage,
        LoginPage,
        ViewProfile,
        EditProfilePage,
        ProfileView,
        RatingList,
    ],
    imports: [
        IonicModule.forRoot(MyApp),
        BrowserModule,
        Ionic2RatingModule,
        IonicStorageModule.forRoot()
    ],
    bootstrap: [IonicApp],
    entryComponents: [
        MyApp,
        SearchPage,
        GoviePage,
        WallPage,
        ProfilePage,
        EventsPage,
        TabsPage,
        LoginPage,
        ViewProfile,
        EditProfilePage
    ],
    providers: [
        Geolocation,
        {provide: ErrorHandler, useClass: IonicErrorHandler}]
})
export class AppModule {
}
