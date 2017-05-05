import { NgModule } from '@angular/core';
import { IonicModule } from 'ionic-angular';
import { ViewProfile } from './view-profile';

@NgModule({
  declarations: [
    ViewProfile,
  ],
  imports: [
    IonicModule.forRoot(ViewProfile),
  ],
  exports: [
    ViewProfile
  ]
})
export class ViewProfileModule {}
