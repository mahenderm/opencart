/* tslint:disable */
import { Injectable, Inject, OnInit } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { Observer } from 'rxjs/Observer';

import { Store } from '@ngrx/store';
import { AppStore } from '../models/appstore.model';
import { Pricing, ActiveStatus } from '../models/pricing.model';
import { Breadcrumb } from '../models/breadcrumb.model';
import { Logger } from '../logging/default-log.service';
import { AuthService } from './auth.service';



/**  
 * PricingService for
 *     getting the price list for SDWAN
 */
@Injectable()
export class PricingService implements OnInit {
    pricing: Observable<Array<Pricing>>;
    constructor(
        private http: Http, 
        public store: Store<AppStore>,
        public authService: AuthService
    ) {
        this.pricing = <Observable<Array<Pricing>>>store.select('prices');
    }

    ngOnInit() {

    }

    loadPrices(): Observable<Pricing> {
        let jwtToken = this.authService.getJWTToken();
        return this.http.get(BASE_URL_PRICES,this.authService.setRequestOptions('',jwtToken))
            .map(res => res.json())
            .catch(this.handleError);
    }

    // this could also be a private method of the component class
    private handleError(error: any) {
        // log error
        // could be something more sofisticated
        let errMsg = (error.message) ? error.message :
            error.status ? `${error.status} - ${error.statusText}` : 'Server error';

        // throw an application level error
        return Observable.throw(errMsg);
    }

    setActiveSelection(item: ActiveStatus) {
        this.store.dispatch({ type: 'UPDATE_ACTIVE_SELECTION', payload: item });
        let breadcrumbs: Breadcrumb[];
        breadcrumbs = (item.mode === 'own') ? [{
            isRoute: false,
            name: 'Locations',
            routerLink: ''
        }
        ] : [{
            isRoute: false,
            name: 'ContactUs',
            routerLink: '',
            from: 'ctl'
        }
        ];
        this.store.dispatch({ type: 'UPDATE_BREADCRUMB', payload: breadcrumbs });
    }
}
