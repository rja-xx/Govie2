import {Injectable} from '@angular/core';
import firebase from 'firebase';

@Injectable()
export class DataService {

  public db:any;

  init() {
    this.db = firebase.database().ref('/');
  }

}

