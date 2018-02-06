import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LocationsComponent } from './locations.component';
import { UnsavedGuard } from '../common/service/unsaved.guard';

export const routes: Routes = [
  {
    path: '',
    canDeactivate:[UnsavedGuard],
    component: LocationsComponent
  }
];

export const locationsRouting : ModuleWithProviders = RouterModule.forChild(routes);
