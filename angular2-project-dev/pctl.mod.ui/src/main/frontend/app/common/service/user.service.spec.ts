/* tslint:disable */
import {  inject,TestBed ,fakeAsync} from '@angular/core/testing';
import { Http, BaseRequestOptions, ConnectionBackend } from '@angular/http';
import { MockBackend } from '@angular/http/testing';
import { Router, RouterModule ,provideRoutes} from '@angular/router';
import { Response, ResponseOptions, RequestMethod } from '@angular/http';
import { MockConnection } from '@angular/http/testing';
import { productsReducer } from '../reducers/products.reducer';
import { userReducer } from '../reducers/user.reducer';
import { ActionReducer, Action } from '@ngrx/store';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs/Rx';
import { provideStore } from '@ngrx/store';
import { AppStore } from '../models/appstore.model';
import { ProductsService } from '../service/products.service';
import { AuthService } from './auth.service';
import { CartService } from './cart.service';
import { AppStateService } from './app-state.service';
import { Logger } from "../logging/default-log.service";
import { UserService } from './user.service';
describe('UserService:', () => {
    // provide our implementations or mocks to the dependency injector
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
     ProductsService, 
     CartService,
     provideStore({names: productsReducer}),
     AuthService,
     AppStateService,
     provideStore({user: userReducer}),
     Logger,
     UserService
      
    ]
  }));
  it('should have test user service', inject([ UserService ], (userDetails: UserService) => {
    spyOn(console, 'log');
    expect(console.log).not.toHaveBeenCalled();
    
    //expect(console.log).toHaveBeenCalled();
  }));
  });

  

 

