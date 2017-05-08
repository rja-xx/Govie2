import { NgModule } from '@angular/core';
import { IonicModule } from 'ionic-angular';
import { ProfileView } from './profile-view';

@NgModule({
  declarations: [
    ProfileView,
  ],
  imports: [
    IonicModule.forRoot(ProfileView),
  ],
  exports: [
    ProfileView
  ]
})
export class ProfileViewModule {}
