import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserProfileComponent } from './user-profile.component';


export const routes: Routes = [
  {
    path: '',
    component: UserProfileComponent
  }
];

export const userProfileComponentRouting : ModuleWithProviders = RouterModule.forChild(routes);
