/* tslint:disable */
import { inject, TestBed,fakeAsync } from '@angular/core/testing';
import { Component } from '@angular/core';
import { TestingCompilerImpl } from '@angular/compiler/testing';
import { Router,RouterModule,provideRoutes } from '@angular/router';
import { FormGroup,FormControl,Validators,FormBuilder,ReactiveFormsModule } from '@angular/forms';   
import { LocationsComponent } from './locations.component';
import { MockBackend ,MockConnection} from '@angular/http/testing';
import { Http,ConnectionBackend,RequestOptions,RequestMethod,BaseRequestOptions } from '@angular/http';
import { async } from '@angular/core/testing';
import {userReducer} from '../common/reducers/user.reducer';
import { cartReducer } from '../common/reducers/cart.reducer';
import { provideStore } from '@ngrx/store';
import { Store } from '@ngrx/store';
import { AppStore } from '../common/models/appstore.model';
import { Logger } from "../common/logging/default-log.service";
import { AuthService } from '../common/service/auth.service';
import { CartService } from '../common/service/cart.service';
import { AppStateService } from '../common/service/app-state.service';
import { Observable } from 'rxjs/Rx';
import { CountryStateService } from '../common/service/country-state.service';
import { LocationsService } from './locations.service';
import { LocationsListComponent } from './locations-list.component';
import { CityService } from '../common/service/city.service';
import { TextMaskService } from '../common/service/text-mask.service';
import { LocationsFormComponent } from './locations-form.component';
import { AppState } from '../app.service';
describe('Locations', () => {
describe('Location list Component', () => {
let options: RequestOptions;
  let component: LocationsListComponent;
let authservice:AuthService;
 beforeEach(() => TestBed.configureTestingModule({
    providers: [
      BaseRequestOptions,
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
        {
          provide:AuthService,useClass: class {AuthService}
       }
       ,
        {
          provide:CartService,useClass: class {CartService}
       }
       ,
   LocationsListComponent,LocationsService,AppStateService,CountryStateService,provideStore({userreducer: userReducer}),
   provideStore({cartreducer: cartReducer}),Logger,FormBuilder,CityService,TextMaskService
    ]}));
      it('Should have `LocationsfromComponent ` can be created ', inject([ LocationsListComponent,CartService], 
      (location:LocationsListComponent,cart:CartService) => {
        expect(location instanceof LocationsListComponent).toEqual(true);
    }));
     it('Should have `locations ` will be Array ', inject([ LocationsListComponent,CartService], 
      (location:LocationsListComponent,cart:CartService) => {
        expect(location.locations).toEqual([]); 
    }));
     it('Should have `pagedLocations ` will be Array ', inject([ LocationsListComponent,CartService], 
      (location:LocationsListComponent,cart:CartService) => {
        expect(location.pagedLocations).toEqual([]);
    }));
     it('Should have `totalItems ` will be 100 ', inject([ LocationsListComponent,CartService], 
      (location:LocationsListComponent,cart:CartService) => {
        expect(location.totalItems).toEqual(100);
        expect(location.totalItems).toEqual(jasmine.any(Number));
    }));
    it('Should have `currentPage ` will be 1 ', inject([ LocationsListComponent,CartService], 
      (location:LocationsListComponent,cart:CartService) => {
        expect(location.currentPage).toEqual(1);
        expect(location.currentPage).toEqual(jasmine.any(Number));
    }));
    it('Should have `maxSize ` will be 200 ', inject([ LocationsListComponent,CartService], 
      (location:LocationsListComponent,cart:CartService) => {
        expect(location.maxSize).toEqual(200);
        expect(location.maxSize).toEqual(jasmine.any(Number));
    }));
    it('Should have `itemsPerPage ` will be 2 ', inject([ LocationsListComponent,CartService], 
      (location:LocationsListComponent,cart:CartService) => {
        expect(location.itemsPerPage).toEqual(2);
        expect(location.itemsPerPage).toEqual(jasmine.any(Number));
    }));
    it('Should have `pageSize ` will be 2 ', inject([ LocationsListComponent,CartService], 
      (location:LocationsListComponent,cart:CartService) => {
        expect(location.pageSize).toEqual(2);
    }));
   it('Should `deleteEvent ` will be valid ', inject([ LocationsListComponent,CartService], 
      (location:LocationsListComponent,cart:CartService) => {
     let locatio={
    id: '123',
    name: 'centunry link',                             
    contact: '040-565541544',                           
    address: 'usa',                           
    serviceContact: {
   firstAndLastName: 'broadband',
    email: 'mahesh@gmail.com',
    phoneNumber: '9640336429',
    phoneNumberExtn: '+91',
    } ,      
    serviceAddress: {
locationName: 'chennai',
    addressLine: 'hcl',
    addressDetails: 'sholingnallur',
    } , 
    shippingAddress:{
    locationName: 'bangolor',
    addressLine: 'hcl',
    addressDetails: 'ktnager',
    }
 };
  location.deleteEvent.subscribe(g => {
      expect(g).toEqual(locatio);
    });
    location.deleteLocation(locatio);
  }));
it('Should `editLocation ` will be valid ', inject([ LocationsListComponent,CartService],
(location:LocationsListComponent,cart:CartService) => {

let locatio={
    id: '123',
    name: 'centunry link',                             
    contact: '040-565541544',                           
    address: 'usa',                           
    serviceContact: {
    firstAndLastName: 'broadband',
    email: 'mahesh@gmail.com',
    phoneNumber: '9640336429',
    phoneNumberExtn: '+91',
    } ,      
    serviceAddress: {
    locationName: 'chennai',
    addressLine: 'hcl',
    addressDetails: 'sholingnallur',
    } , 
    shippingAddress:{
    locationName: 'bangolor',
    addressLine: 'hcl',
    addressDetails: 'ktnager',
    }
 };
    location.editEvent.subscribe(g => {
      expect(g).toEqual(locatio);
    });
    location.editLocation(locatio);
  }));   
  });
describe('Location Form Component', () => {
let options: RequestOptions;
  let component: LocationsFormComponent;
let authservice:AuthService;
 beforeEach(() => TestBed.configureTestingModule({
    providers: [
      BaseRequestOptions,
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
        {
          provide:AuthService,useClass: class {AuthService}
        }
       ,
         {
          provide:CartService,useClass: class {CartService}
         }
       ,
   LocationsFormComponent,LocationsService,AppStateService,CountryStateService,provideStore({userreducer: userReducer}),
   provideStore({cartreducer: cartReducer}),Logger,FormBuilder,CityService,TextMaskService
    ]}));
      it('Should `LocationsfromComponent ` can be created ', inject([ LocationsFormComponent,CartService], 
      (location:LocationsFormComponent,cart:CartService) => {
        expect(location instanceof LocationsFormComponent).toEqual(true); 
    }));
  it('Should select default data will be  valid`',inject([ LocationsFormComponent ], (locationform:LocationsFormComponent) => {
        expect(locationform.selectedDefault).toBe(0);
    }));
   it('Should LocationsfromComponent phone Mask will be valid', inject([ LocationsFormComponent ], (locationfromcomponent: LocationsFormComponent) => {
     expect(locationfromcomponent.phoneMask).toEqual( [ '(', /[1-9]/, /\d/, /\d/, ')', ' ', /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/ ]);  
       }));
 it('Should LocationsfromComponent phoneExtnMask will be valid', inject([ LocationsFormComponent ], (locationfromcomponent: LocationsFormComponent) => {
             expect(locationfromcomponent.phoneExtnMask).toEqual([ /[1-9]/, /\d/, /\d/, /\d/ ]);  
       }));
    it('Should select htmlTooltip data will be  valid`',inject([ LocationsFormComponent ], (locationform:LocationsFormComponent) => {
        expect(locationform.htmlTooltip).toBe('service address required');
    }));
  it('Should  have resetDataModel data initial value will be valid  `',inject([ LocationsFormComponent ], (locationform:LocationsFormComponent) => {
       let values=
        { id: '', 
        serviceContact: {
          email: '',
          firstName: '',
          lastName: '',
          phoneNumber: '', 
          phoneNumberExtn: ''
        }
        , serviceAddress: {
           locationName: '', 
           addressLine: '',
            street: '', 
            city: '', 
            country: 'USA', 
            state: '', 
            zipCode: '' 
          }
        , shippingAddress: {
           locationName: '', 
           addressLine: '', 
           street: '',
            city: '', 
            country: 'USA', 
            state: '', 
            zipCode: '', 
            checkAddress: '' 
          }
        }
       locationform.resetDataModel();
        expect(locationform.data).toEqual(values);
    }));
    it('Should have submitted data will be  False`',inject([ LocationsFormComponent ], (locationform:LocationsFormComponent) => {
       locationform.ngOnInit();
        expect(locationform.submitted).toBeFalsy();
    }));
 it('Should have myForm data will be  Null`',inject([ LocationsFormComponent ], (locationform:LocationsFormComponent) => {
       locationform.ngOnInit();
        locationform.myForm.value.id="mahesh";
        expect(locationform.myForm.value.id).toBe("mahesh");
        expect(locationform.myForm.value.serviceContact.email).toBeNull;
        expect(locationform.myForm.value.serviceContact.firstName).toBeNull;
        expect(locationform.myForm.value.serviceContact.lastName).toBeNull;
        expect(locationform.myForm.value.serviceContact.phoneNumber).toBeNull;
        expect(locationform.myForm.value.serviceContact.phoneNumberExtn).toBeNull;
        expect(locationform.myForm.value.serviceAddress.locationName).toBeNull;
        expect(locationform.myForm.value.serviceAddress.addressLine).toBeNull;
        expect(locationform.myForm.value.serviceAddress.street).toBeNull;
        expect(locationform.myForm.value.serviceAddress.city).toBeNull;
        expect(locationform.myForm.value.serviceAddress.country).toBe('USA');
        expect(locationform.myForm.value.serviceAddress.state).toBeNull;
        expect(locationform.myForm.value.serviceAddress.zipCode).toBeNull;
        expect(locationform.myForm.value.shippingAddress.checkAddress).toBeNull;
        expect(locationform.myForm.value.shippingAddress.locationName).toBeNull;
        expect(locationform.myForm.value.shippingAddress.addressLine).toBeNull;
        expect(locationform.myForm.value.shippingAddress.street).toBeNull;
        expect(locationform.myForm.value.shippingAddress.city).toBeNull;
        expect(locationform.myForm.value.shippingAddress.country).toBe('USA');
        expect(locationform.myForm.value.shippingAddress.state).toBeNull;
        expect(locationform.myForm.value.shippingAddress.zipCode).toBeNull;
    }));
   });
describe('Location Service', () => {
    let locationService: LocationsService;
    let mockBackend: MockBackend;
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
      LocationsService,
       provideStore({user: userReducer}),
       AuthService,
       RouterModule,
       AppStateService,
       Logger,CartService
    ]}));
it('Should be get response from  validLocation() will be valid ', 
    inject(
      [LocationsService, MockBackend],
      fakeAsync((service:LocationsService, backend: MockBackend) => {
        backend.connections.subscribe((connection: MockConnection) => {
        expect(connection.request.method).toBe(RequestMethod.Post);         
        });
        let item={
           id: '123',
    locationName: 'Chennai',        
    addressLine: 'karapakam',          
    street: 'gandhi nagar',                
    city: 'chennai',                  
    country: 'India',   
    state: 'Tamilnadu',
    zipCode: '6000018',
    checkAddress: 'correct'
        }
        
      })));
});
});

