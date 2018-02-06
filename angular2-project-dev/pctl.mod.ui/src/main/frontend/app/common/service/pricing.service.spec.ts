/* tslint:disable */
import { Observable } from 'rxjs/Rx';
import { Observer } from 'rxjs/Observer';
import { inject,TestBed,fakeAsync,tick} from '@angular/core/testing';
import { Component } from '@angular/core';
import {BaseRequestOptions,ConnectionBackend,RequestMethod,Http,ResponseOptions,Response} from '@angular/http';
import { MockBackend,MockConnection } from '@angular/http/testing';
import { Router, RouterModule ,provideRoutes} from '@angular/router';
import {provideStore} from '@ngrx/store';
import { Store } from '@ngrx/store';
// // Load the implementations that should be tested
import { AppStore } from '../models/appstore.model';
import { Pricing } from '../models/pricing.model';
 import {PricingService} from '../service/pricing.service';
import {pricesReducer} from '../reducers/prices.reducer';
import {userReducer} from '../reducers/user.reducer';
import { AuthService } from './auth.service';
import { CartService } from './cart.service';
import { AppStateService } from './app-state.service';
import { Logger } from "../logging/default-log.service";
import { Breadcrumb } from '../models/breadcrumb.model';
describe('PriceService:', () => {
  let service: PricingService = null;
  let backend: MockBackend = null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        MockBackend,
        BaseRequestOptions,
           {
          provide: Http,
          useFactory: (backendInstance: MockBackend, defaultOptions: BaseRequestOptions) => {
            return new Http(backendInstance, defaultOptions);
            },
          deps: [ MockBackend, BaseRequestOptions ]
          },
          { 
        provide: Router, 
        useClass: class { navigate = jasmine.createSpy("navigate"); }
          },
        PricingService, provideStore({prices: pricesReducer}),AuthService,AppStateService,provideStore({user: userReducer}),Logger,CartService
        ]
    });
  });

  beforeEach(inject([PricingService, MockBackend], (PricingService: PricingService, mockBackend: MockBackend) => {
    service = PricingService;
    backend = mockBackend;
  }));
  let data = {
      "variantId": "sdwanbasic",
      "productId": "sdwan1000",
      "name": "SD-WAN BASIC",
      "descriptionHtml": "Software-defined wide area networking (SD-WAN) is the next evolution of private networking",
      "displayImage": "",
      "sku": "SDWANBASIC",
      "displayPosition": 1,
      "servicePlan": "basic",
      "serviceVendor": "versa",
      "serviceTransport": "customer",
      "securityPkg": "1",
      "standardCpe": "1",
      "haServicePlan": "",
      "haServiceVendor": "",
      "haServiceTransport": "",
      "haSecurityPkg": "",
      "haCpe": "0",
      "tier1Support": "1",
      "currency": "USD",
      "priceList": [
        {
          "term": "12",
          "own": 150,
          "ctl": 100
        },
        {
          "term": "24",
          "own": 250,
          "ctl": 200
        },
        {
          "term": "36",
          "own": 350,
          "ctl": 300
        }
    ]};
 it('Should retrieve the json correctly', (done) => {
    backend.connections.subscribe((connection: MockConnection) => {
      let options = new ResponseOptions({
        body: JSON.stringify(data)
      });
      connection.mockRespond(new Response(options));
      expect(connection.request.method).toEqual(RequestMethod.Get);
       expect(connection.request.url).toMatch(/pricesMS/);
      expect(connection.request.headers.get('Content-Type')).toEqual('application/json');      
    });
    service.loadPrices().subscribe((response) => {
        expect(response).toEqual(data);
        done();
      });
  });
    it('Should have  setActiveSelection() dispatch  will be valid`',inject([ PricingService ], (service:PricingService) => {
         spyOn(service.store,'dispatch');
         let item={
    mode: 'sample',
    sku: 'century',
    term: 'link',
    name: 'tarun',
 
         }
          service.setActiveSelection(item);
          expect(service.store.dispatch).toHaveBeenCalledWith({ type: 'UPDATE_ACTIVE_SELECTION', payload: item });
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
  
 expect(service.store.dispatch).toHaveBeenCalledWith({ type: 'UPDATE_BREADCRUMB', payload: breadcrumbs });

 }));
});
