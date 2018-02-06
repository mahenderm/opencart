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
import { AppStore } from '../common/models/appstore.model';
import { Logger } from "../common/logging/default-log.service";
import { Observable } from 'rxjs/Rx';
import { CityService } from '../service/city.service';



  describe('City Service', () => {
  let options: RequestOptions;
  let service: CityService = null;
  let backend: MockBackend = null;
 beforeEach(() => TestBed.configureTestingModule({

    providers: [
      BaseRequestOptions,CityService,
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

     beforeEach(inject([CityService, MockBackend], (CityService: CityService, mockBackend: MockBackend) => {

    backend = mockBackend;
  }));
  
it('Should select cityservice data will be  valid`',inject([ CityService ], (cityservice:CityService) => {
       
        expect(cityservice.fetchCities().length).toBeGreaterThan(2);
    }));
  });



         