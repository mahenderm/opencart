import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BillingComponent } from './billing.component';
import { UnsavedGuard } from '../common/service/unsaved.guard';


export const routes: Routes = [
  {
    path: '',
    canDeactivate:[UnsavedGuard],
    component: BillingComponent
  }
];

export const billingComponentRouting : ModuleWithProviders = RouterModule.forChild(routes);
