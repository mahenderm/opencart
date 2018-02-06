import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ContactUsComponent } from './contact-us.component';
import { UnsavedGuard } from '../service/unsaved.guard';


export const routes: Routes = [
  {
    path: '',
    canDeactivate:[UnsavedGuard],    
    component: ContactUsComponent
  }
];

export const contactUsComponentRouting : ModuleWithProviders = RouterModule.forChild(routes);
