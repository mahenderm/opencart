import {  inject,TestBed ,fakeAsync} from '@angular/core/testing';
import { Http, BaseRequestOptions,ConnectionBackend ,RequestOptions} from '@angular/http';
import { MockBackend } from '@angular/http/testing';
import { CartService } from '../service/cart.service';
import {  Response, ResponseOptions, RequestMethod } from '@angular/http';
import { MockConnection } from '@angular/http/testing';
import { ShoppingCart, LineItem, SDWANLocationInfo, LocationInfo } from '../models/cart.model';
import { Store } from '@ngrx/store';
import { AppStore } from '../models/appstore.model';
import { Observable } from 'rxjs/Rx';
import { Observer } from 'rxjs/Observer';
import {provideStore} from '@ngrx/store';
import {cartReducer} from '../reducers/cart.reducer';
import { AuthService } from './auth.service';
import { RouterModule ,Router} from '@angular/router';


describe('Cart service', () => {
  let store:Store<AppStore>;
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      BaseRequestOptions,
      MockBackend,
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
      CartService, provideStore({names: cartReducer}),AuthService,RouterModule
    ]}));
it('should url will be same  ', 
    inject(
      [CartService, MockBackend],
      fakeAsync((service:CartService, backend: MockBackend) => {
        backend.connections.subscribe((connection: MockConnection) => {

       
          expect(connection.request.url).toBe(
            'http://localhost:3001/cart/');
            
        });

        service.loadItems();
      })));



      it('should method will get method ', 
    inject(
      [CartService, MockBackend],
      fakeAsync((service:CartService, backend: MockBackend) => {
        backend.connections.subscribe((connection: MockConnection) => {

          expect(connection.request.method).toBe(RequestMethod.Get);
             
        });

       service.loadItems();
      })));

it('should send the price request to the server', (done) => {
  done();
});

     

});


