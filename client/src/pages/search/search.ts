import { Component, ChangeDetectorRef} from '@angular/core';
import { UserService } from "../../providers/session/UserService";
import { NavController } from 'ionic-angular';
import {Events} from "ionic-angular/index";
import {User} from "../../model/user";
import {MovieService} from "../../providers/movie/MovieService";
import {Movie} from "../../model/movie";
//import {MomentModule} from 'angular2-moment/moment.module';

@Component({
    selector: 'page-search',
    templateUrl: 'search.html',
    providers: [UserService, MovieService],

})
export class SearchPage {

    constructor(public navCtrl:NavController,
                cd:ChangeDetectorRef,
                public events:Events,
                public userService:UserService,
                public movieService:MovieService) {
        this.users = [];
        this.movies = [];
        this.searchQuery = '';
        this.searchType = 'movies';
        events.subscribe('user:search:result', (result) => {
            console.log(result);
            this.users = result;
            cd.detectChanges();
        });
        events.subscribe('movie:search:result', (result) => {
            console.log(result);
            this.movies = result.map(function (val) {
                return new Movie(val.id, val.posterPath, val.backdropPath, val.title, val.releaseDate);
            });
            cd.detectChanges();
        });
        this.getItems(null);
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
        console.log(user);//todo
    }

    chooseMovie(movie) {
        console.log(movie);//todo
    }

}
