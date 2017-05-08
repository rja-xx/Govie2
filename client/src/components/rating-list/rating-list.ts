import { Component } from '@angular/core';

/**
 * Generated class for the RatingList component.
 *
 * See https://angular.io/docs/ts/latest/api/core/index/ComponentMetadata-class.html
 * for more info on Angular Components.
 */
@Component({
  selector: 'rating-list',
  templateUrl: 'rating-list.html'
})
export class RatingList {

  text: string;

  constructor() {
    console.log('Hello RatingList Component');
    this.text = 'Hello World';
  }

}
