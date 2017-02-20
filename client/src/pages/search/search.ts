import { Component, ChangeDetectorRef} from '@angular/core';
import { UserService } from "../../providers/session/UserService";
import { NavController } from 'ionic-angular';
import {Events} from "ionic-angular/index";

@Component({
    selector: 'page-search',
    templateUrl: 'search.html',
    providers: [UserService],

})
export class SearchPage {

    constructor(public navCtrl:NavController,
                cd:ChangeDetectorRef,
                public events:Events,
                public userService:UserService) {
        this.items = [];
        this.searchQuery = '';
        events.subscribe('user:search:result', (result) => {
            console.log(result);
            this.items = result;
            cd.detectChanges();
        });
    }

    searchQuery:string;
    items:string[];

    getItems(ev:any) {
        let val = ev.target.value;
        if (val && val.trim() != '') {
            console.log('searching for ' + val.toLowerCase());
            this.userService.searchUser(val.toLowerCase(), this.events);
        }
    }

}
