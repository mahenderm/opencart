/* tslint:disable */
import { Injectable, Inject, OnInit } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { Observer } from 'rxjs/Observer';

import { Store } from '@ngrx/store';
import { AppStore } from '../models/appstore.model';
import { TnCList, TnCInfo } from '../models/tnc.model';
import { AuthService } from './auth.service';




@Injectable()
export class TnCService implements OnInit {
    tncList: Observable<TnCList>;
    baseURL: string;
    constructor(
        private http: Http, 
        public store: Store<AppStore>,
        public authService: AuthService
    ) {
        this.tncList = <Observable<TnCList>>store.select('tnc');
    }

    ngOnInit() {

    }

    loadTnC(): void {
        let jwtToken = this.authService.getJWTToken();
        this.http.get(BASE_URL_TNC,
            this.authService.setRequestOptions('',jwtToken))
            .map(res => res.json())
            .catch(this.handleError)
            .subscribe( result => {
                if (result !== undefined && result.length !== undefined && result.length > 0) {
                        result.forEach((item, index) => {
                        // Add items to the tnc store
                        this.store.dispatch({ type: 'ADD_TNCINFO', payload: <TnCInfo>item })
                    });
                }
            });
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
