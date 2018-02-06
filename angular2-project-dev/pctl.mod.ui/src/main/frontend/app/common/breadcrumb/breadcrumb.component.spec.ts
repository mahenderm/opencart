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
import { AuthService } from '../service/auth.service';
import { RouterModule, Router } from '@angular/router';
import { ReflectiveInjector, Injector } from '@angular/core';
import { UserProfileComponent } from './user-profile.component'
import { AppStateService } from '../service/app-state.service';
import { Logger } from "../logging/default-log.service";
import { Breadcrumb } from '../models/breadcrumb.model';
import { BreadcrumbComponent } from './breadcrumb.component';

describe('Breadcrumb Component', () => {
      //let breadcrumb1: BreadcrumbComponent;
    //  let authservice:AuthService;
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
    BreadcrumbComponent,
    AuthService
     //  provideStore({user: userReducer}),CartService,Logger,AppStateService
     
      ]}));

    it('Should breadcrumb has homepage', inject([BreadcrumbComponent], (breadcrumb1: BreadcrumbComponent) => {
    
     expect(breadcrumb1.homePage).toBeTruthy;

   }))
   it('Should breadcrumb has defined', inject([BreadcrumbComponent], (breadcrumb1: BreadcrumbComponent) => {
    
     expect(breadcrumb1.breadcrumbs).toBeDefined;
   
   }))

  });
