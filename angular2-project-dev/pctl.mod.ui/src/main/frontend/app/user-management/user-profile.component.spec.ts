/* tslint:disable */
import { inject, TestBed, fakeAsync } from '@angular/core/testing';
import { Http, BaseRequestOptions, ConnectionBackend, RequestOptions } from '@angular/http';
import { MockBackend } from '@angular/http/testing';
import { CartService } from '../common/service/cart.service';
import { Response, ResponseOptions, RequestMethod } from '@angular/http';
import { MockConnection } from '@angular/http/testing';
import { ShoppingCart, LineItem, SDWANLocationInfo, LocationInfo } from '../models/cart.model';
import { Store, StoreModule } from '@ngrx/store';
import { AppStore } from '../models/appstore.model';
import { Observable } from 'rxjs/Rx';
import { Observer } from 'rxjs/Observer';
import { provideStore, Dispatcher } from '@ngrx/store';
import { userReducer } from '../common/reducers/user.reducer';
import { AuthService } from '../common/service/auth.service';
import { RouterModule, Router } from '@angular/router';
import { ReflectiveInjector, Injector } from '@angular/core';
import { UserProfileComponent } from './user-profile.component'
import { AppStateService } from '../common/service/app-state.service';
import { Logger } from "../common/logging/default-log.service";
import { Breadcrumb } from '../common/models/breadcrumb.model';

describe('UserProfile Component', () => {
    let userprofile: UserProfileComponent;
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
    },UserProfileComponent,AuthService,
       provideStore({user: userReducer}),CartService,Logger,AppStateService
    ]}));

   it('Should have  ngOnInit() `',inject([ UserProfileComponent ], (userprofile:UserProfileComponent) => {
 
       expect(userprofile.ngOnInit()).toBeTruthy;
    }));
   it('Should be userDetails Data equal to Observable', inject([UserProfileComponent], (userprofile: UserProfileComponent) => {
    let check = userprofile.userDetails;
    expect(check).toEqual(jasmine.any(Observable));
   
 }));
   it('Should have  ngOnInit() will be valid`',inject([ UserProfileComponent ], (userprofile:UserProfileComponent) => {
    spyOn(userprofile.store,'dispatch');
    let breadcrumbs: Breadcrumb[];
         breadcrumbs = [{
            isRoute: false,
            name: 'User Profile',
            routerLink: '/user-profile'
        }];
     userprofile.ngOnInit();
   expect(userprofile.store.dispatch).toHaveBeenCalledWith({ type: 'UPDATE_BREADCRUMB', payload: breadcrumbs });
    })); 
  it('Should have  ngOnInit()   data be valid`',inject([ UserProfileComponent ], (userprofile:UserProfileComponent) => {
        userprofile.ngOnInit();
        userprofile.userDetails.subscribe(g => {
      expect(g).toEqual(Object({ breadcrumbs: [ Object({ isRoute: false, name: 'User Profile', routerLink: '/user-profile' }) ] }));
    }); 
    }));
});
