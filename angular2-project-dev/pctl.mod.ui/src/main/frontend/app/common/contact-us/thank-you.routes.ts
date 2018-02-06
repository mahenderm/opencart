import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ThankYouComponent } from './thank-you.component';


export const routes: Routes = [
  {
    path: '',
    component: ThankYouComponent
  }
];

export const thankYouComponentRouting : ModuleWithProviders = RouterModule.forChild(routes);
