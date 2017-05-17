import { Component, ChangeDetectorRef } from '@angular/core';

import { NavController } from 'ionic-angular';
import {Rating} from "../../model/rating";
import {MovieService} from "../../providers/movie/MovieService";
import {Events} from "ionic-angular/index";
import {UserService} from "../../providers/session/UserService";
import {Movie} from "../../model/movie";

@Component({
    selector: 'page-govie',
    templateUrl: 'govie.html',
    providers: [UserService, MovieService],
})
export class GoviePage {

    movieQuery:String;
    movies:Movie[];
    movieTitle:String;
    movieId:String;


    constructor(public navCtrl:NavController,
                private movieService:MovieService,
                private events:Events,
                private userService:UserService,
                private cd:ChangeDetectorRef) {

    }

    ionViewDidEnter() {
        this.movies = [];
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

}
