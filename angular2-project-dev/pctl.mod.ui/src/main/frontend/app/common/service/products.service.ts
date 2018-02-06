/* tslint:disable */
import { Injectable, Inject, OnInit } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { Observer } from 'rxjs/Observer';

import { Store } from '@ngrx/store';
import { AppStore } from '../models/appstore.model';
import { Product } from '../models/product.model';
import { AuthService } from './auth.service';




/** ProductsService Service for
 *     1) Storing the current auth token (if loggedIn)
 *     2) Handle login and logout methods
*/
@Injectable()
export class ProductsService implements OnInit {
    product: Observable<Product>;
    baseURL: string;
    constructor(
        private http: Http, 
        public store: Store<AppStore>,
        public authService: AuthService
    ) {
        this.product = <Observable<Product>>store.select('products');
    }

    ngOnInit() {

    }

    loadProduct(): Observable<Product> {
        let jwtToken = this.authService.getJWTToken();
        return this.http.get(BASE_URL_PRODUCTS,
            this.authService.setRequestOptions('',jwtToken))
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

}
