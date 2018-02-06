import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Billing, BillingApiResponse } from '../common/models/billing.model';
import { Observable } from 'rxjs/Rx';
import { AuthService } from '../common/service/auth.service';
import { Logger } from '../common/logging/default-log.service';

//const BASE_URL_ADD_VAL = 'http://localhost:8080/api/address-service/address/validation/';
//const BASE_URL_ORDER_SUBMIT = 'http://localhost:8080/api/order-service/order/events';
/**
 * Order Service
 * Billing service is added
 * Billing location and Order Place - API call
 */
@Injectable()
export class OrderService {

    constructor(
        private http: Http,
        public authService: AuthService,
        private logger: Logger) { }

    /**
     * Billing address validation API call
     */
    validLocation(item: Billing, call: String): Observable<BillingApiResponse> {
        let jwtToken = this.authService.getJWTToken();
        let requestOptions = this.authService.setRequestOptions('', jwtToken);
        if ('development' === ENV)
            return this.http.post(`${BASE_URL_ADD_VAL}`, JSON.stringify(item), requestOptions)
                .map((res: any) => res.json());
        else
            return this.http.post(`${BASE_URL_ADD_VAL}${call}`, JSON.stringify(item), requestOptions)
                .map((res: any) => res.json());
    }


    /**
     * Billing order API call with cartId, orderId, eventType
     * along with HqAddress and contact details
     */
    updateOrder(cartId: any, orderId: any, item: Billing): Observable<BillingApiResponse> {
        let billingEvent: any = this.getBillingEvent(cartId, orderId, item, 'UPDATED');
        let jwtToken = this.authService.getJWTToken();
        let requestOptions = this.authService.setRequestOptions('', jwtToken);
        // this.logger.log('order service '+BASE_URL_ORDER_SUBMIT)
        // this.logger.log('billingEvent '+ JSON.stringify(billingEvent))
        return this.http.post(`${BASE_URL_ORDER_SUBMIT}`, JSON.stringify(billingEvent), requestOptions)
            .map((res: any) => res.json());
    }

    /**
     * Billing order API call with cartId, orderId, eventType
     * along with HqAddress and contact details
     */
    placeOrder(cartId: any, orderId: any, item: Billing): Observable<BillingApiResponse> {
        let submitOrderEvent: any = this.getOrderSubmitEvent(orderId,'ORDERED');
        let jwtToken = this.authService.getJWTToken();
        let requestOptions = this.authService.setRequestOptions('', jwtToken);
        return this.http.post(`${BASE_URL_ORDER_SUBMIT}`, JSON.stringify(submitOrderEvent), requestOptions)
            .map((res: any) => res.json());
    }

    /**
     *   URL : /ui-service/order/events
     
        Sample Request : 
        
            {
                        "orderId": "cart11611140653311188",
                        "orderEventType": "ORDERED"
            }

    */
    private getOrderSubmitEvent(orderId: any, orderEventType: string): any {
        let submitOrderEvent: any = {
            orderId: orderId,
            orderEventType: orderEventType
        }
        return submitOrderEvent;
    }
    /**
       URL : /ui-service/order/events
    
        Sample Request : 
        
            {
                        "cartId": "123313213",
                        "orderId": "cart11611140653311188",
                        "companyName":"HelloInfoTech",
                        "orderEventType": "UPDATED",
                        "hqAddress": {
                                        "locationName": "location1",
                                        "addressLine": "address line1",
                                        "street": "street",
                                        "city": "city",
                                        "country": "country",
                                        "state": "state",
                                        "zipCode": "142134666"
                        },
            "billingContactDetails" : {
                        "firstName" : "krishna",
                        "lastName" : "R",
                        "phoneNumber" : "8528528525",
                        "phoneNumberExtn" : "8585",
                        "email" : "test@test.com",
                        "jobTitle" : "testJob"
                }        
            }

     */

    private getBillingEvent(cartId: any, orderId: any, item: Billing, orderEventType: string): any {
        let billingEvent: any = {
            cartId: cartId,
            orderId: orderId,
            orderEventType: orderEventType,
            companyName: item.companyName,
            hqAddress: item.hqaddress,
            billingContactDetails: item.billingContactDetails
        }
        return billingEvent;
    }

    getContract(): Observable<BillingApiResponse> {
        let jwtToken = this.authService.getJWTToken();
        let requestOptions = this.authService.setRequestOptions('', jwtToken);
        if ('development' === ENV)
            return this.http.get(`${BASE_URL_TNC}`, requestOptions)
                .map((res: any) => res.json());
        else
            return this.http.get(`${BASE_URL_TNC}`, requestOptions)
                .map((res: any) => res.json());
    }

}
