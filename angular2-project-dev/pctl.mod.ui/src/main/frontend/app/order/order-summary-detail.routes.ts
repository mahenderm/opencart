import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OrderSummaryDetailComponent }   from './order-summary-detail.component';


export const routes: Routes = [
  {
    path: '',
    component: OrderSummaryDetailComponent
  }
];

export const orderSummaryRouting : ModuleWithProviders = RouterModule.forChild(routes);
