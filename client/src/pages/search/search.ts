import { Component, ChangeDetectorRef} from '@angular/core';
import { UserService } from "../../providers/session/UserService";
import { NavController } from 'ionic-angular';
import {Events} from "ionic-angular/index";
import {User} from "../../model/user";
import {MovieService} from "../../providers/movie/MovieService";
import {Movie} from "../../model/movie";
import {ProfilePage} from "../profile/profile";
import {ModalController} from "ionic-angular/index";
import {ViewProfile} from "../view-profile/view-profile";
import {Profile} from "../../model/profile";
//import {MomentModule} from 'angular2-moment/moment.module';

@Component({
    selector: 'page-search',
    templateUrl: 'search.html',
    providers: [UserService, MovieService],

})
export class SearchPage {

    constructor(public navCtrl:NavController,
                private cd:ChangeDetectorRef,
                public events:Events,
                public modalCtrl:ModalController,
                public userService:UserService,
                public movieService:MovieService) {
        this.events = events;
        this.users = [];
        this.movies = [];
        this.searchQuery = '';
        this.searchType = 'movies';

        this.getItems(null);
    }

    ionViewDidEnter() {
        this.events.subscribe('user:search:result', (result) => {
            console.log(result);
            this.users = result;
            this.cd.detectChanges();
        });
        this.events.subscribe('movie:search:result', (result) => {
            console.log(result);
            this.movies = result.map(function (val) {
                return new Movie(val.id, val.posterPath, val.backdropPath, val.title, val.releaseDate);
            });
            this.cd.detectChanges();
        });
    }

    ionViewWillLeave() {
        this.events.unsubscribe('movie:search:result');
        this.events.unsubscribe('user:search:result');
    }


    searchQuery:string;
    movies:Movie[];
    users:string[];
    searchType:string;

    getItems(ev:any) {
        let val = ev ? ev.target.value : this.searchQuery;
        this.searchQuery = val;
        console.log('searching for ' + val.toLowerCase());
        if (this.searchType == 'movies') {
            this.movieService.searchMovies(val, this.events, this.userService.currentUser());
        } else {
            if (val && val.trim() != '') {
                this.userService.searchUser(val.toLowerCase(), this.events);
            } else {
                this.users = [];
            }
        }
    }

    chooseUser(user) {
        console.log(user);
        let modal = this.modalCtrl.create(ViewProfile, {uid: user.uid});
        modal.present();
    }

    chooseMovie(movie) {
        console.log(movie);//todo
    }

}
