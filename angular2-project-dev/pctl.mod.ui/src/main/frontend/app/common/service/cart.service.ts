/* tslint:disable */
import { Injectable, Inject, OnInit } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { Observer } from 'rxjs/Observer';

import { Store } from '@ngrx/store';
import { AppStore } from '../models/appstore.model';
import { CartInfo } from '../models/user.model';
import { ShoppingCart, LineItem, SDWANLocationInfo, LocationInfo } from '../models/cart.model';
import { FingerPrintService } from './fingerprint.service';
import { Logger } from '../logging/default-log.service';


@Injectable()
export class CartService implements OnInit {
    cart: Observable<ShoppingCart>;
    constructor(
        private http: Http,
        public store: Store<AppStore>,
        private logger: Logger
    ) {
        this.cart = <Observable<ShoppingCart>>store.select('cart');
    }

    ngOnInit() {

    }

    updateLocation(cartId: string, item: LocationInfo, requestOptions: any) {
        let locationEvent: any = this.getLocationEvent(cartId, item, 'UPDATE_LOCATION');
        this.http.post(`${BASE_URL_CART_EVENTS}?cartId=${cartId}&cartEventType=UPDATE_LOCATION`, JSON.stringify(locationEvent), requestOptions)
            .subscribe(action => this.store.dispatch({ type: 'UPDATE_LOCATION', payload: item }));
    }

    addLocation(cartId: string, item: LocationInfo, requestOptions: any) {
        let locationEvent: any = this.getLocationEvent(cartId, item, 'ADD_LOCATION');
        this.http.post(`${BASE_URL_CART_EVENTS}?cartId=${cartId}&cartEventType=ADD_LOCATION`, JSON.stringify(locationEvent), requestOptions)
            .subscribe(action => this.store.dispatch({ type: 'UPDATE_LOCATION', payload: item }));
    }


    deleteLocation(cartId: string, item: LocationInfo, requestOptions: any) {
        let locationEvent: any = this.getLocationEvent(cartId, item, 'REMOVE_LOCATION');
        this.http.post(`${BASE_URL_CART_EVENTS}?cartId=${cartId}&cartEventType=REMOVE_LOCATION`, JSON.stringify(locationEvent), requestOptions)
            .subscribe(action => this.store.dispatch({ type: 'DELETE_LOCATION', payload: item }));
    }


    deleteItem(cartId: string, item: LineItem, requestOptions: any) {

        let itemEvent: any = this.getItemEvent(cartId, item, 'REMOVE_ITEM');
        return this.http.post(`${BASE_URL_CART_EVENTS}?cartId=${cartId}&cartEventType=REMOVE_ITEM`, JSON.stringify(itemEvent), requestOptions)
            .subscribe(action => this.store.dispatch({ type: 'DELETE_ITEM', payload: item }));
    }

    addItem(cartId: string, item: LineItem, requestOptions: any): Observable<any> {
        let itemEvent: any = this.getItemEvent(cartId, item, 'ADD_ITEM');
        return this.http.post(`${BASE_URL_CART_EVENTS}?cartId=${cartId}&cartEventType=ADD_ITEM`, JSON.stringify(itemEvent), requestOptions)
            .map(res => res.json())
            .map(payload => ({ type: 'ADD_ITEM', payload: item }));
    }

    /**
     * Check out cart based on ID and save the returned order ID
     * sent in the content as Order ID
     */
    checkOut(cartId: string, requestOptions: any): Observable<any> {
        // for dev env create order ID dummy and pass


        if ('development' === ENV) {
            let orderId = Math.floor((1 + Math.random()) * 0x10000);
            let output = {
                    type: 'UPDATE_CART_DETAILS', payload: {
                        cartInfo: <CartInfo>{
                            shoppingCartId: cartId,
                            orderID: orderId
                        }
                    }
                };
            return Observable.of(output);
        /*    return new Observable(payload => {
                ({
                    type: 'UPDATE_CART_DETAILS', payload: {
                        cartInfo: <CartInfo>{
                            shoppingCartId: cartId,
                            orderID: orderId
                        }
                    }
                })
            });
*/
        }
        this.logger.log(`${BASE_URL_ORDER_CHECKOUT}`);
        let cartInfo = <CartInfo>{
                            shoppingCartId: cartId
                        };
       return this.http.post(`${BASE_URL_ORDER_CHECKOUT}?cartId=${cartId}&cartEventType=CHECKOUT`, JSON.stringify(cartInfo),requestOptions)
            .map(res => res.json())
            .map(payload =>
                ({
                    type: 'UPDATE_CART_DETAILS',
                    payload: {
                        cartInfo: <CartInfo>{
                            shoppingCartId: cartId,
                            orderID: payload.content
                        }
                    }
                })
            );
    }

    loadCartByID(cartID: string, requestOptions: any) {
        this.http.get(`${BASE_URL_CART_BASIC}${cartID}`,
            requestOptions)
            .map((res: any) => res.json())
            .catch((error: any) => {
                this.logger.log("ERROR in get cart call");
                return Observable.throw(error._body);
            })
            .subscribe(
            data => {
                //  this.user = data.content;
                //this.logger.log('cart Info result :');
                //this.logger.log(data);
                //let cartId: string;
                //cartId = data.cartId;
                // If there are line Items update the cart with that
                if (data.content === undefined)
                    return;
                if (data.content.lineItems !== undefined &&
                    data.content.lineItems.length > 0) {
                    data.content.lineItems.forEach((item, index) => {
                        // Add items to the cart
                        this.store.dispatch({ type: 'ADD_ITEM', payload: <LineItem>item })
                    });

                }

            },
            err => {
                // this.error = JSON.parse(err.message);
                this.logger.error(err);
            }
            );
    }

    /*   CART_EVENTS
      *  Create Cart and add it to the user state
      *  Call this when the user state does not have the cart existing
      */
    public initCart(): ShoppingCart {
        //TODO: Call API to get cart ID from Cart Microservice
        // for now generating our own ID
        let cart: ShoppingCart;
        cart = {
            id: FingerPrintService.UUID(),
            lineItems: []
        };
        this.store.dispatch({ type: 'CREATE_CART', payload: cart });
        return cart;
    }

    public initCartWithID(cartId: string): ShoppingCart {
        //TODO: Call API to get cart ID from Cart Microservice
        // for now generating our own ID
        let cart: ShoppingCart;
        cart = {
            id: cartId,
            lineItems: []
        };
        this.store.dispatch({ type: 'CREATE_CART', payload: cart });
        return cart;
    }

    private getItemEvent(cartId: string, item: LineItem, cartEventType: string): any {
        let itemEvent: any = {
            cartId: cartId,
            quantity: 1,
            cartEventType: cartEventType,
            productName: item.productName,
            productId: item.productId,
            productTemplateName: item.productTemplateName,
            productTemplateId: item.productTemplateId,
            unitPrice: item.unitPrice,
            term: item.term
        }
        return itemEvent;
    }

    private getLocationEvent(cartId: string, item: LocationInfo, cartEventType: string): any {

        let locationEvent: any = {
            cartId: cartId,
            cartEventType: cartEventType,
            productTemplateId: item.productTemplateId,
            location: item.location
        }
        return locationEvent;
    }

}
