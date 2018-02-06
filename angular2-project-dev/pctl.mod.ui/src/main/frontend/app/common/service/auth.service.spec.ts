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
import { userReducer } from '../reducers/user.reducer';
import { AuthService } from './auth.service';
import { RouterModule, Router } from '@angular/router';
import { ReflectiveInjector, Injector } from '@angular/core';
import { AppStateService } from './app-state.service';
import { Logger } from "../logging/default-log.service";
import { User, EnterpriseInfo, CartInfo } from '../models/user.model';

describe('Auth Service', () => {
let nameListService: AuthService;
    let mockBackend: MockBackend;
    let initialResponse: any
  let router: Router;
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
      AuthService,
       provideStore({user: userReducer}),
       AuthService,
       RouterModule,
       AppStateService,
       Logger,CartService
    ]}));
it('Should have userInfo() will be valid  ', 
    inject(
      [AuthService, MockBackend],
      fakeAsync((service:AuthService, backend: MockBackend) => {
        backend.connections.subscribe((connection: MockConnection) => {
        expect(connection.request.method).toBe(RequestMethod.Get);
        expect(connection.request.headers.get('Content-Type')).toEqual('application/json');         
        });
        service.userInfo();
      })));
    it('Should have getUserDetails() will be valid  ', 
    inject(
      [AuthService, MockBackend],
      fakeAsync((service:AuthService, backend: MockBackend) => {
        backend.connections.subscribe((connection: MockConnection) => {
        expect(connection.request.method).toBe(RequestMethod.Get);
        expect(connection.request.headers.get('Content-Type')).toEqual('application/json');   
          expect(service.getJWTToken()).toBe('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcGljbGllbnRAY3RsLmNvbSIsImlzcyI6IkN0bCIsImlhdCI6MTQ3OTgxNTA5MSwiZXhwIjoxNzkwODU1MDkxfQ.kc-P35bWdS_7qWslOXs7mtcW3CcSZ8fU2jTDtc6uRUiBMhWr1U_3cG3Yoew9nYjgGkvcaRHMl_QHDiEarIrHdA');     
        });
        service.getUserDetails() ;
      })));
it('Should have cartInitWithToken() will be valid  ', 
    inject(
      [AuthService, MockBackend],
      fakeAsync((service:AuthService, backend: MockBackend) => {
        backend.connections.subscribe((connection: MockConnection) => {
        expect(connection.request.method).toBe(RequestMethod.Post);
        expect(service.getJWTToken()).toBe('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcGljbGllbnRAY3RsLmNvbSIsImlzcyI6IkN0bCIsImlhdCI6MTQ3OTgxNTA5MSwiZXhwIjoxNzkwODU1MDkxfQ.kc-P35bWdS_7qWslOXs7mtcW3CcSZ8fU2jTDtc6uRUiBMhWr1U_3cG3Yoew9nYjgGkvcaRHMl_QHDiEarIrHdA');     
        });
        service.cartInitWithToken('12','767') ;
      })));
      it('Should have  getJWTToken will be valid`',inject([ AuthService ], (service:AuthService) => {
 
      expect(service.getJWTToken()).toEqual('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcGljbGllbnRAY3RsLmNvbSIsImlzcyI6IkN0bCIsImlhdCI6MTQ3OTgxNTA5MSwiZXhwIjoxNzkwODU1MDkxfQ.kc-P35bWdS_7qWslOXs7mtcW3CcSZ8fU2jTDtc6uRUiBMhWr1U_3cG3Yoew9nYjgGkvcaRHMl_QHDiEarIrHdA');
       
    }));
      it('Should have  login() will be valid`',inject([ AuthService ], (service:AuthService) => {
     service.login('mahesh','reddy');
      let my = {
            id: 1,
            email: 'mahesh'.toString(),
            loggedIn: true
        }
        service.user.subscribe(g => {
      expect(g).toEqual(my);
    }); 
    }));
    it('Should have  login() dispatch  will be valid`',inject([ AuthService ], (service:AuthService) => {
         spyOn(service.store,'dispatch');
         service.login('mahesh','reddy');
      let my = {
            id: 1,
            email: 'mahesh'.toString(),
            loggedIn: true
        }
          expect(service.store.dispatch).toHaveBeenCalledWith({ type: 'CREATE_USER', payload: my });
    }));
     it('Should have  init will be valid`',inject([ AuthService ], (service:AuthService) => {
     service.init();
      let my = {
            id: 1,
            forceCheck: false
             }
        service.user.subscribe(g => {
      expect(g).toEqual(my);
    }); 
    }));
   it('Should have  addUserInfo() will be valid`',inject([ AuthService ], (service:AuthService) => {
    spyOn(service.store,'dispatch');
    let info={
      ein: 20,                        
    ownTransport: true
    }
  service.addUserInfo(info);
   expect(service.store.dispatch).toHaveBeenCalledWith({ type: 'UPDATE_ENT_DETAILS', payload: info });
    }));
});
