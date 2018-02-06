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
import { AppStore } from '../common/models/appstore.model';
import { Logger } from "../common/logging/default-log.service";
import { Observable } from 'rxjs/Rx';
import { CountryStateService } from '../service/country-state.service';



  describe('Country State Service', () => {
let options: RequestOptions;
 let service: CountryStateService = null;
  let backend: MockBackend = null;
 beforeEach(() => TestBed.configureTestingModule({

    providers: [
      BaseRequestOptions,CountryStateService,
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

     beforeEach(inject([CountryStateService, MockBackend], (CountryStateService: CountryStateService, mockBackend: MockBackend) => {

    backend = mockBackend;
  }));
  
   it('Should select countries data will be  valid`',inject([ CountryStateService ], (countryservice:CountryStateService) => {
        let data=[ { countryId: 1, countryName: 'United States of America', countryCode: 'USA' }];
        
        expect(countryservice.getCountries()).toEqual(data);
    }));

    it('Should select states data will be  valid`',inject([ CountryStateService ], (countryservice:CountryStateService) => {
          let data=[
	{  
		   stateId:1,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Alabama",
		   stateCode:"AL"
		},
		{  
		   stateId:2,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Alaska",
		   stateCode:"AK"
		},
		{  
		   stateId:3,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Arizona",
		   stateCode:"AZ"
		},
		{  
		   stateId:4,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Arkansas",
		   stateCode:"AR"
		},
		{  
		   stateId:5,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"California",
		   stateCode:"CA"
		},
		{  
		   stateId:6,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Colorado",
		   stateCode:"CO"
		},
		{  
		   stateId:7,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Connecticut",
		   stateCode:"CT"
		},
		{  
		   stateId:8,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Delaware",
		   stateCode:"DE"
		},
		{  
		   stateId:9,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Florida",
		   stateCode:"FL"
		},
		{  
		   stateId:10,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Georgia",
		   stateCode:"GA"
		},
		{  
		   stateId:11,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Hawaii",
		   stateCode:"HI"
		},
		{  
		   stateId:12,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Idaho",
		   stateCode:"ID"
		},
		{  
		   stateId:13,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Illinois",
		   stateCode:"IL"
		},
		{  
		   stateId:14,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Indiana",
		   stateCode:"IN"
		},
		{  
		   stateId:15,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Iowa",
		   stateCode:"IA"
		},
		{  
		   stateId:16,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Kansas",
		   stateCode:"KS"
		},
		{  
		   stateId:17,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Kentucky",
		   stateCode:"KY"
		},
		{  
		   stateId:18,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Louisiana",
		   stateCode:"LA"
		},
		{  
		   stateId:19,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Maine",
		   stateCode:"ME"
		},
		{  
		   stateId:20,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Maryland",
		   stateCode:"MD"
		},
		{  
		   stateId:21,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Massachusetts",
		   stateCode:"MA"
		},
		{  
		   stateId:22,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Michigan",
		   stateCode:"MI"
		},
		{  
		   stateId:23,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Minnesota",
		   stateCode:"MN"
		},
		{  
		   stateId:24,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Mississippi",
		   stateCode:"MS"
		},
		{  
		   stateId:25,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Missouri",
		   stateCode:"MO"
		},
		{  
		   stateId:26,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Montana",
		   stateCode:"MT"
		},
		{  
		   stateId:27,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Nebraska",
		   stateCode:"NE"
		},
		{  
		   stateId:28,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Nevada",
		   stateCode:"NV"
		},
		{  
		   stateId:29,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"New Hampshire",
		   stateCode:"NH"
		},
		{  
		   stateId:30,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"New Jersey",
		   stateCode:"NJ"
		},
		{  
		   stateId:31,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"New Mexico",
		   stateCode:"NM"
		},
		{  
		   stateId:32,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"New York",
		   stateCode:"NY"
		},
		{  
		   stateId:33,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"North Carolina",
		   stateCode:"NC"
		},
		{  
		   stateId:34,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"North Dakota",
		   stateCode:"ND"
		},
		{  
		   stateId:35,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Ohio",
		   stateCode:"OH"
		},
		{  
		   stateId:36,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Oklahoma",
		   stateCode:"OK"
		},
		{  
		   stateId:37,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Oregon",
		   stateCode:"OR"
		},
		{  
		   stateId:38,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Pennsylvania",
		   stateCode:"PA"
		},
		{  
		   stateId:39,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Rhode Island",
		   stateCode:"RI"
		},
		{  
		   stateId:40,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"South Carolina",
		   stateCode:"SC"
		},
		{  
		   stateId:41,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"South Dakota",
		   stateCode:"SD"
		},
		{  
		   stateId:42,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Tennessee",
		   stateCode:"TN"
		},
		{  
		   stateId:43,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Texas",
		   stateCode:"TX"
		},
		{  
		   stateId:44,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Utah",
		   stateCode:"UT"
		},
		{  
		   stateId:45,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Vermont",
		   stateCode:"VT"
		},
		{  
		   stateId:46,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Virginia",
		   stateCode:"VA"
		},
		{  
		   stateId:47,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Washington",
		   stateCode:"WA"
		},
		{  
		   stateId:48,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"West Virginia",
		   stateCode:"WV"
		},
		{  
		   stateId:49,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Wisconsin",
		   stateCode:"WI"
		},
		{  
		   stateId:50,
		   countryId:1,
		   countryCode:"USA",
		   stateName:"Wyoming",
		   stateCode:"WY"
		},
	];
        expect(countryservice.getStates()).toEqual(data);
    }));
  });