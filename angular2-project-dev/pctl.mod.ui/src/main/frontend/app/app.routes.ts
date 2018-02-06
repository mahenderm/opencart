import { Routes, RouterModule } from '@angular/router';
import { CtlHomeComponent } from './home/ctl-home.component';
//import { AboutComponent } from './about';
import { NoContentComponent } from './no-content';
//import { LocationsComponent } from './locations/locations.component';
//import { ContactUsComponent } from './common/contact-us/contact-us.component';
import { LoginComponent } from './user-management/login.component';
//import { UserProfileComponent } from './user-management/user-profile.component';
//import { ThankYouComponent } from './common/contact-us/thank-you.component';
//import { DataResolver } from './app.resolver';
import { AuthGuard } from './auth.guard';
import { NavigationGuard } from './navigation.guard';
//import { BillingComponent } from './billing/billing.component';
//import { TnCComponent } from './order/tnc.component';
//import { OrderSummaryDetailComponent }   from './order/order-summary-detail.component';
//import { OrderPlacedComponent }   from './order/order-placed.component';



export const ROUTES: Routes = [
  { path: '', component: CtlHomeComponent },
  { path: 'home', component: CtlHomeComponent },
  { path: 'contact-us', 
    loadChildren: 'app/common/contact-us/contact-us.module#ContactUsModule' },
  { path: 'login', component: LoginComponent },
  { path: 'user-profile', 
    loadChildren: 'app/user-management/user-profile.module#UserProfileModule' },
  { path: 'thanks', 
    loadChildren: 'app/common/contact-us/thank-you.module#ThankYouModule' },
  { path: 'order-summary-detail', 
    canActivate: [AuthGuard],
    loadChildren: 'app/order/order.module#OrderModule'
     },
  { path: 'order-placed', 
    canActivate: [AuthGuard,NavigationGuard],
    loadChildren: 'app/order/order-placed.module#OrderPlacedModule'
    },
  {
    path: 'locations',
    canActivate: [AuthGuard],
    loadChildren: 'app/locations/locations.module#LocationsModule'
  },
//  {
//    path: 'detail', loadChildren: () => System.import('./+detail').then((comp: any) => {
//      return comp.default;
//    })
//    ,
//  },
  { path: 'billing', 
    canActivate: [AuthGuard,NavigationGuard],
    loadChildren: 'app/billing/billing.module#BillingModule'
   },
  { path: 'tnc', 
    canActivate: [AuthGuard,NavigationGuard],
    loadChildren: 'app/order/tnc.module#TnCModule' },
  { path: '**', component: NoContentComponent },
];
