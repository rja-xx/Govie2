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
import {Profile} from "../../model/profile";

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
    cinemaId:string;
    cinemas:Cinema[];
    friends:User[];
    addedFriends:string[];
    friendName:string;
    shareOnFacebook:boolean;
    shareOnTwitter:boolean;
    comment:string;
    rating:number;
    uid:string;

    constructor(public navCtrl:NavController,
                private movieService:MovieService,
                private events:Events,
                private userService:UserService,
                private cd:ChangeDetectorRef,
                public geolocation:Geolocation,
                public cinemaService:CinemaService) {
        this.shareOnFacebook = false;
        this.shareOnTwitter = false;
        this.addedFriends = [];
        this.uid = this.userService.currentUser().uid;
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
                return new Cinema(val.id, val.address, val.name);
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
        this.cinemaService.searchCinema(this.cinemaName, this.events, this.userService.currentUser());
    }

    cinemasInVicinity(ev:any) {
        this.geolocation.getCurrentPosition().then(res => {
            var lat = res.coords.latitude;
            var lon = res.coords.longitude;
            this.cinemaService.searchCinemaInVicinity(lat, lon, this.events, this.userService.currentUser());
            console.log("found position " + res.coords);
        }).catch((error) => {
            console.log('Error getting location', error);
        });
    }

    chooseCinema(cinema) {
        this.cinemas = [];
        console.log(cinema);
        this.cinemaName = cinema.name;
        this.cinemaId = cinema.id;
        this.cd.detectChanges();
    }

    searchFriend(ev:any) {
        let val = ev ? ev.target.value : this.friendName;
        console.log("search friend: " + val);
        if (val && val.trim() != '') {
            this.userService.searchUser(val.toLowerCase(), this.events);
        }
    }

    chooseFriend(friend) {
        this.friends = [];
        this.addedFriends.push(friend.uid);
        console.log(friend);
        this.friendName = '';
        this.cd.detectChanges();
    }

    clearFriends(ev) {
        this.addedFriends = [];
        this.friendName = '';
        this.cd.detectChanges();
    }

    rate(evt) {
        var rating = new Rating(
            this.uid,
            this.movieId,
            this.cinemaId,
            this.addedFriends,
            this.comment,
            this.rating,
            this.shareOnFacebook,
            this.shareOnTwitter);
        this.movieService.submitRating(rating);
        this.navCtrl.parent.select(0);
    }
}
