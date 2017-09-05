import { Component } from '@angular/core';

import { SearchPage } from '../search/search';
import { ProfilePage } from '../profile/profile';
import { GoviePage } from '../govie/govie';
import { WallPage } from '../wall/wall';
import { EventsPage } from '../events/events';
import { Storage } from '@ionic/storage';

@Component({
    templateUrl: 'tabs.html'
})
export class TabsPage {

    // this tells the tabs component which Pages
    // should be each tab's root Page
    tab1Root:any = WallPage;
    tab2Root:any = SearchPage;
    tab3Root:any = GoviePage;
    tab4Root:any = EventsPage;
    tab5Root:any = ProfilePage;

    constructor(private storage:Storage) {
        console.log('starting!')
        storage.clear();
    }
}
