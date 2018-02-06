import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Store } from '@ngrx/store';
import { Router } from '@angular/router';

import { AppStore } from '../common/models/appstore.model';
import { Variant } from '../common/models/product.model';
import { ProductsService } from '../common/service/products.service';
import { Breadcrumb } from '../common/models/breadcrumb.model';

@Component({
  selector: 'product-features',
  templateUrl: './product-features.component.html',
  styles: ['.card-block { margin-top:10px; } .new-list-view-header h3 {	color: #3cac30;	}']
})
export class ProductFeaturesComponent {
   options = ['Standard CPE', 'High Availability', 'Security Package', 'Tier 1 Support'];
   @Input() variants: Variant[];

  constructor(
    private _router: Router,
    public store: Store<AppStore>) { }


   contactUs() {
    let breadcrumbs: Breadcrumb[];
    breadcrumbs = [
    {
      isRoute: false,
      name: 'ContactUs',
      routerLink: '',
      from: 'landing'
    }];
    // console.log("contact brdcmrb " + JSON.stringify(breadcrumbs));
    this.store.dispatch({ type: 'UPDATE_BREADCRUMB', payload: breadcrumbs });
    this._router.navigate(['/contact-us']);
  }
}
