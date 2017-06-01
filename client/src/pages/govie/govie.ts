import { Component, ChangeDetectorRef } from '@angular/core';

import { NavController } from 'ionic-angular';
import {Rating} from "../../model/rating";
import {MovieService} from "../../providers/movie/MovieService";
import {Events} from "ionic-angular/index";
import {UserService} from "../../providers/session/UserService";
import {Movie} from "../../model/movie";
import { Geolocation } from '@ionic-native/geolocation';
import {CinemaService} from "../../providers/cinema/CinemaService";
import {Cinema} from "../../model/cinema";
import {User} from "../../model/user";

@Component({
    selector: 'page-govie',
    templateUrl: 'govie.html',
    providers: [UserService, MovieService, CinemaService],
})
export class GoviePage {

    movieQuery:string;
    movies:Movie[];
    movieTitle:string;
    movieId:string;

    cinemaName:string;
    cinemas:Cinema[];
    friends:User[];
    friendName:string;
    shareOnFacebook:boolean;
    shareOnTwitter:boolean;

    constructor(public navCtrl:NavController,
                private movieService:MovieService,
                private events:Events,
                private userService:UserService,
                private cd:ChangeDetectorRef,
                public geolocation:Geolocation,
                public cinemaService:CinemaService) {
        this.shareOnFacebook = false;
        this.shareOnTwitter = false;
    }

    ionViewDidEnter() {
        this.movies = [];
        this.cinemas = [];
        this.events.subscribe('movie:search:result', (result) => {
            console.log(result);
            this.movies = result.map(function (val) {
                return new Movie(val.id, val.posterPath, val.backdropPath, val.title, val.releaseDate);
            });
            this.cd.detectChanges();
        });
        this.events.subscribe('cinema:search:result', (result) => {
            console.log(result);
            this.cinemas = result.map(function (val) {
                return new Cinema(val.reference, val.address, val.name);
            });
            this.cd.detectChanges();
        });
        this.events.subscribe('user:search:result', (result) => {
            console.log(result);
            this.friends = result;
            this.cd.detectChanges();
        });
    }

    ionViewWillLeave() {
        this.events.unsubscribe('movie:search:result');
        this.events.unsubscribe('cinema:search:result');
        this.events.unsubscribe('user:search:result');
    }

    searchMovies(ev:any) {
        let val = ev ? ev.target.value : this.movieQuery;
        this.movieQuery = val;
        console.log('searching for ' + val.toLowerCase());
        this.movieService.searchMovies(val, this.events, this.userService.currentUser());
    }


    chooseMovie(movie) {
        this.movies = [];
        console.log(movie);
        this.movieTitle = movie.title;
        this.movieId = movie.id;
        this.cd.detectChanges();
    }

    searchCinema(ev:any) {
        let val = ev ? ev.target.value : this.cinemaName;
        this.cinemaName = val;
        this.geolocation.getCurrentPosition().then(res => {
            var lat = res.coords.latitude;
            var lon = res.coords.longitude;
            this.cinemaService.searchCinemaInVicinity(this.cinemaName, lat, lon, this.events, this.userService.currentUser());
            console.log("found positino " + res.coords);
        }).catch((error) => {
            console.log('Error getting location', error);
        });

        console.log(this.shareOnFacebook);
        console.log(this.shareOnTwitter);
    }

    chooseCinema(cinema) {
        console.log("chose cinema: " + cinema);
    }

    searchFriend(ev:any) {
        let val = ev ? ev.target.value : this.friendName;
        if (val && val.trim() != '') {
            this.userService.searchUser(val.toLowerCase(), this.events);
        }
    }


}
