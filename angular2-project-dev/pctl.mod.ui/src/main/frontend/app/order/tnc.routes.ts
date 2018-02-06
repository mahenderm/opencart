import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TnCComponent }   from './tnc.component';


export const routes: Routes = [
  {
    path: '',
    component: TnCComponent
  }
];

export const tncRouting : ModuleWithProviders = RouterModule.forChild(routes);
