import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OrderPlacedComponent }   from './order-placed.component';


export const routes: Routes = [
  {
    path: '',
    component: OrderPlacedComponent
  }
];

export const orderPlacedRouting : ModuleWithProviders = RouterModule.forChild(routes);
