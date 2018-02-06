/* tslint:disable */
import { inject, TestBed } from '@angular/core/testing';
import { Component } from '@angular/core';
import { TestingCompilerImpl } from '@angular/compiler/testing';
import { Router,RouterModule,provideRoutes } from '@angular/router';
import { FormGroup,FormControl,Validators,FormBuilder,ReactiveFormsModule } from '@angular/forms';   
import { LocationsComponent } from './locations.component';
import { MockBackend } from '@angular/http/testing';
import { Http,ConnectionBackend,RequestOptions,RequestMethod,BaseRequestOptions } from '@angular/http';
import { async } from '@angular/core/testing';
import { provideStore } from '@ngrx/store';
import { Store } from '@ngrx/store';
import { AppStore } from '../models/appstore.model';
import { Logger } from "../common/logging/default-log.service";
import { Observable } from 'rxjs/Rx';
import { AppStateService } from './app-state.service';
import { userReducer } from '../reducers/user.reducer';
import { CartState } from '../models/cart.model';
  describe('AppStateService', () => {
  let options: RequestOptions;
  let backend: MockBackend = null;
  let appstateservice:AppStateService=null;
 beforeEach(() => TestBed.configureTestingModule({

    providers: [
      BaseRequestOptions,AppStateService,provideStore({user: userReducer}),
      ConnectionBackend,
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
    
    ]}));

   
it('Should have  AppState default data will have   Array`',inject([ AppStateService ], (service:AppStateService) => {
  let  ServiceData=  service.getState();
       service.store.select('user').subscribe(q => {
      expect(q).toEqual([]);
     
    });
       
    }));
    it('Should have  AppState  has getstate function`',inject([ AppStateService ], (service:AppStateService) => {
      expect(service.getState()).toBeTruthy;
       
    }));
  });
