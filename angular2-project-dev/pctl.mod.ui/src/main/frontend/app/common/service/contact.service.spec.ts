/* tslint:disable */
import {  inject,TestBed ,fakeAsync} from '@angular/core/testing';
import { Http, BaseRequestOptions,ConnectionBackend } from '@angular/http';
import { MockBackend } from '@angular/http/testing';
import { Router, RouterModule ,provideRoutes} from '@angular/router';
import { ProductsService } from '../service/products.service';
import {  Response, ResponseOptions, RequestMethod } from '@angular/http';
import { MockConnection } from '@angular/http/testing';
import {productsReducer} from '../reducers/products.reducer';
import { ActionReducer, Action } from '@ngrx/store';
import { Store } from '@ngrx/store';
import { AppStore } from '../models/appstore.model';
import { ReflectiveInjector } from '@angular/core';
  import { Observable } from 'rxjs/Rx';
import {provideStore} from '@ngrx/store';
import { AuthService } from './auth.service';
import { AppStateService } from './app-state.service';
import { ContactService } from './contact.service';
import {userReducer} from '../reducers/user.reducer';
import { Logger } from "../logging/default-log.service";
import { CartService } from './cart.service';

describe('Contact Service:', () => {
  let service: ContactService = null;
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
        ContactService,AuthService,AppStateService,Logger,provideStore({user: userReducer}),CartService]
    });
  });
  beforeEach(inject([ContactService, MockBackend], (ContactService: ContactService, mockBackend: MockBackend) => {
    service = ContactService;
    backend = mockBackend;
  }));
  
  let data = {
        "firstName": "sdf",
        "lastName": "sdf",
        "primaryPhone": "(111) 111-1111",
        "primaryPhoneExtn": "5555",
        "email": "tksukhu@gmail.com"
    };
 it('Should ContactService will be match given json response  ', 
    inject( [ContactService, MockBackend],fakeAsync((service:ContactService, backend: MockBackend) => {
        backend.connections.subscribe((connection: MockConnection) => {
              expect(connection.request.method).toBe(RequestMethod.Post);
              expect(connection.request.url).toMatch(/contact/);
        });
        service.createLead(data);
      })));
});

  

 

