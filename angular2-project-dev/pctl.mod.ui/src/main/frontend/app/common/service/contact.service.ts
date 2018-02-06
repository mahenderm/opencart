/* tslint:disable */
import { Http, Headers, RequestOptions } from '@angular/http';
import { Injectable, Inject, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { ContactUs } from '../models/contact-us.model';
import { Logger } from '../logging/default-log.service';
import { AuthService } from '../service/auth.service';
import { BillingApiResponse } from '../models/billing.model';

//const BASE_URL_CONTACTUS = 'http://localhost:3001/contactus/';

@Injectable()
export class ContactService implements OnInit {

    constructor(private http: Http,
        private logger: Logger,
        private authService: AuthService) { }

    ngOnInit() { }

    createLead(item: ContactUs,existingCustomer?: boolean): Observable<BillingApiResponse> {
        if ('production' === ENV)
            return;
        //this.logger.log(BASE_URL_CONTACTUS + JSON.stringify(item));
        let jwtToken = this.authService.getJWTToken();
        let requestOptions = this.authService.setRequestOptions('', jwtToken);
        return this.http.post(BASE_URL_CONTACTUS, JSON.stringify(item), requestOptions)
            .map(res => res.json())
    }

}
