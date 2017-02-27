import { Component, ChangeDetectorRef} from '@angular/core';
import { UserService } from "../../providers/session/UserService";
import { NavController } from 'ionic-angular';
import {Events} from "ionic-angular/index";
import {User} from "../../model/user";
import {MovieService} from "../../providers/movie/MovieService";

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
            this.movies = result;
            cd.detectChanges();
        });
    }

    searchQuery:string;
    movies:string[];
    users:string[];
    searchType:string;

    getItems(ev:any) {
        let val = ev.target.value;
        if (val && val.trim() != '') {
            console.log('searching for ' + val.toLowerCase());
            if (this.searchType == 'movies') {
                this.movieService.searchMovies(val, this.events, this.userService.currentUser());
            } else {
                this.userService.searchUser(val.toLowerCase(), this.events);
            }
        }
    }

    chooseUser(user) {
        console.log(user);
    }

    chooseMovie(movie) {
        console.log(movie);
    }

}
