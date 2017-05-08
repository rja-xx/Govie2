import { NgModule } from '@angular/core';
import { IonicModule } from 'ionic-angular';
import { RatingList } from './rating-list';

@NgModule({
  declarations: [
    RatingList,
  ],
  imports: [
    IonicModule.forRoot(RatingList),
  ],
  exports: [
    RatingList
  ]
})
export class RatingListModule {}
