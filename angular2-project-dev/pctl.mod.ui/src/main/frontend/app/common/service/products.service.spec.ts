/* tslint:disable */
import {  inject,TestBed ,fakeAsync} from '@angular/core/testing';
import { Http, BaseRequestOptions,ConnectionBackend } from '@angular/http';
import { MockBackend } from '@angular/http/testing';
import { Router, RouterModule ,provideRoutes} from '@angular/router';
import {  Response, ResponseOptions, RequestMethod } from '@angular/http';
import { MockConnection } from '@angular/http/testing';
import {productsReducer} from '../reducers/products.reducer';
import {userReducer} from '../reducers/user.reducer';
import { ActionReducer, Action } from '@ngrx/store';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs/Rx';
import {provideStore} from '@ngrx/store';
import { AppStore } from '../models/appstore.model';
import { ProductsService } from '../service/products.service';
import { AuthService } from './auth.service';
import { CartService } from './cart.service';
import { AppStateService } from './app-state.service';
import { Logger } from "../logging/default-log.service";
describe('ProductService:', () => {
  let service: ProductsService = null;
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
        ProductsService, CartService,provideStore({names: productsReducer}),AuthService,AppStateService,provideStore({user: userReducer}),Logger
          ]
    });
  });
  beforeEach(inject([ProductsService, MockBackend], (ProductsService: ProductsService, mockBackend: MockBackend) => {
    service = ProductsService;
    backend = mockBackend;
  }));
  let data = {
   "productId": "sdwan1000",
    "name": "SD WAN",
    "descriptionHtml": "Software-defined wide area networking (SD-WAN) is the next evolution of private networking. SD-WAN uses software to automate the ongoing configuration of edge routers and push traffic over a mix of private, wireless, and broadband network access.SD-WAN offers a real network solution for businesses trying to keep up with the impact a highly dynamic application environment has on their network quality of service. Our SD-WAN combines CenturyLink network design and management expertise with leading software controls and the ubiquity of any connection type or any vendor. The result is improved flexibility through greater bandwidth that delivers increased visibility into network usage and vastly simplified control. CenturyLink offers four popular configurations of SD-WAN as described below -- or a custom configuration can be set up via a sales agent.",
    "displayImage": "",
    "productType": "Network",
    "createdAt": "2016-09-22T14:25:08-03:00",
    "updatedAt": "2016-09-22T14:25:08-03:00",
    "terms": [
      {
        "name": "12",
        "frequency": "monthly",
        "shortCode": "12m"
      },
      {
        "name": "24",
        "frequency": "monthly",
        "shortCode": "24m"
      },
      {
        "name": "36",
        "frequency": "monthly",
        "shortCode": "36m"
      }
    ]};
 it('Should retrieve the json correctly', (done) => {
    backend.connections.subscribe((connection: MockConnection) => {
      let options = new ResponseOptions({
        body: JSON.stringify(data)
      });
      connection.mockRespond(new Response(options));
      expect(connection.request.method).toEqual(RequestMethod.Get);
       expect(connection.request.url).toMatch(/productMS/);
      expect(connection.request.headers.get('Content-Type')).toEqual('application/json');       
    });
    service.loadProduct().subscribe((response) => {
        expect(response).toEqual(data);
        done();
      });
  });
  });

  

 

