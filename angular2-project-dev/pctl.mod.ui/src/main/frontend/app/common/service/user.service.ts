/* tslint:disable */
import { Injectable, Inject, OnInit } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { Observer } from 'rxjs/Observer';

import { Store } from '@ngrx/store';
import { AppStore } from '../models/appstore.model';
import { Product } from '../models/product.model';
import { AuthService } from './auth.service';
import { User } from '../models/user.model';




/** ProductsService Service for
 *     1) Storing the current auth token (if loggedIn)
 *     2) Handle login and logout methods
*/
@Injectable()
export class UserService implements OnInit {
    user: Observable<User>;
    constructor(
        private http: Http, 
        public store: Store<AppStore>,
        public authService: AuthService
    ) {
        
    }

    ngOnInit() {

    }


}
