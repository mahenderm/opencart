/* tslint:disable */
import { inject, TestBed, fakeAsync } from '@angular/core/testing';
import { Http, BaseRequestOptions, ConnectionBackend, RequestOptions } from '@angular/http';
import { MockBackend } from '@angular/http/testing';
import { CartService } from '../service/cart.service';
import { Response, ResponseOptions, RequestMethod } from '@angular/http';
import { MockConnection } from '@angular/http/testing';
import { ShoppingCart, LineItem, SDWANLocationInfo, LocationInfo } from '../models/cart.model';
import { Store, StoreModule } from '@ngrx/store';
import { AppStore } from '../models/appstore.model';
import { Observable } from 'rxjs/Rx';
import { Observer } from 'rxjs/Observer';
import { provideStore, Dispatcher } from '@ngrx/store';
import { cartReducer } from '../reducers/cart.reducer';
import { AuthService } from './auth.service';
import { RouterModule, Router } from '@angular/router';
import { ReflectiveInjector, Injector } from '@angular/core';
import { AppStateService } from './app-state.service';
import { Logger } from "../logging/default-log.service";
describe('Cart Service', () => {
let nameListService: CartService;
    let mockBackend: MockBackend;
    let initialResponse: any
  
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      BaseRequestOptions,
      MockBackend,
         {
          provide:AuthService,useClass: class {AuthService}
       }
       ,
      {
        provide: Http,
        useFactory: function(backend: ConnectionBackend, defaultOptions: BaseRequestOptions) {
          return new Http(backend, defaultOptions);
        },
        deps: [MockBackend, BaseRequestOptions]
      },
      { 
        provide: Router, 
        useClass: class { navigate = jasmine.createSpy("navigate"); }
    },
      CartService,
       provideStore({names: cartReducer}),
       AuthService,
       RouterModule,
       AppStateService,
       Logger
    ]}));
it('Should url will be same And Http Request valid  ', 
    inject(
      [CartService, MockBackend],
      fakeAsync((service:CartService, backend: MockBackend) => {
        backend.connections.subscribe((connection: MockConnection) => {
          expect(connection.request.method).toBe(RequestMethod.Get);
          expect(connection.request.url).toMatch(/cart/);            
        });
        service.initCart();
      })));
it('Should be cart Data equal to Observable', inject([CartService], (Cartserv: CartService) => {
    let CartServiceData = Cartserv.cart;
    expect(CartServiceData).toEqual(jasmine.any(Observable));
   // console.log("current path is ",browser().location().path());
 }));
 it('Should be get response from service method - initCartWithID ', 
    inject(
      [CartService, MockBackend],
      fakeAsync((service:CartService, backend: MockBackend) => {
        backend.connections.subscribe((connection: MockConnection) => {
          expect(connection.request.method).toBe(RequestMethod.Get);
          expect(connection.request.url).toMatch(/cart/);            
        });
        service.initCartWithID("cartId");
      })));
     

   //TODO :: Kannan and niranjan has to verify the below test case
   /* it('Cart prototype should be updateLocation', inject([CartService], (nameList: CartService) => {
    spyOn(CartService.prototype, 'updateLocation').and.callThrough();

    const actual = nameList.updateLocation;
    const expected = 0;
    expect(actual).toHaveBeenCalledTimes(expected);

     
      })); */
     
 });
