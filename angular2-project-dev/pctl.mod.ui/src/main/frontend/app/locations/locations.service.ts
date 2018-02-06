import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { EnterpriseAddress, ServiceAddressValidation } from '../common/models/cart.model';
import { Observable } from 'rxjs/Rx';
import { AuthService } from '../common/service/auth.service';

const HEADER = { headers: new Headers({ 'Content-Type': 'application/json' }) };
@Injectable()
export class LocationsService {

    constructor(private http: Http,
    public authService: AuthService) {
    }

    validLocation(item: EnterpriseAddress, call: String): Observable<ServiceAddressValidation> {
        let jwtToken = this.authService.getJWTToken();
        let requestOptions = this.authService.setRequestOptions('',jwtToken);
        return this.http.post(`${BASE_URL_ADD_VAL}${call}`, JSON.stringify(item), requestOptions)
            .map((res: any) => res.json());
    }

}
