/* tslint:disable */
import { inject, TestBed } from '@angular/core/testing';
import { Component } from '@angular/core';
import { TestingCompilerImpl } from '@angular/compiler/testing';
import { Router,RouterModule,provideRoutes } from '@angular/router';
import { FormGroup,FormControl,Validators,FormBuilder,ReactiveFormsModule } from '@angular/forms';   
import { MockBackend } from '@angular/http/testing';
import { Http,ConnectionBackend,RequestOptions,RequestMethod,BaseRequestOptions } from '@angular/http';
import { async } from '@angular/core/testing';
import { TextMaskService } from '../common/service/text-mask.service';
import { userReducer } from '../common/reducers/user.reducer';
import { provideStore } from '@ngrx/store';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs/Rx';
import { Logger } from "../common/logging/default-log.service";
import { BillingComponent } from './billing.component';
import { CountryStateService } from '../common/service/country-state.service';
import { OrderService } from '../order/order.service';
import { AuthService } from '../common//service/auth.service';
import { CartService } from '../common//service/cart.service';
import { AppStateService } from '../common//service/app-state.service';

   describe('Billing Component', () => {
    beforeEach(() => TestBed.configureTestingModule({
        providers: [
            BillingComponent,Logger,CountryStateService,
            FormBuilder,RouterModule, OrderService,
            AuthService,CartService,
            TextMaskService, AppStateService,
            provideStore({
            user: userReducer
            }), 
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
        ],
    }));
   
     it('Should `Billing Component ` can be created ', inject([ BillingComponent], 
      (billing:BillingComponent) => {
        expect(billing instanceof BillingComponent).toEqual(true); 
     }));
     it('Should have `selectedDefault` by default to be 0',inject([ BillingComponent ], (billing:BillingComponent) => {
    
        expect(billing.selectedDefault).toBe(0);
    }));
     it('Should have countries data response will be valid data`',inject([ BillingComponent ], (billing:BillingComponent) => {
        let data=[ { countryId: 1, countryName: 'United States of America', countryCode: 'USA' }];
        expect(billing.countries).toEqual(data);
    }));
    it('Should have  phoneMask will be valid format`',inject([ BillingComponent ], (billing:BillingComponent) => {
    
        expect(billing.phoneMask).toEqual(['(', /[1-9]/, /\d/, /\d/, ')', ' ', /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/]);
    }));

    it('Should have  phoneExtnMask  will be  valid format`',inject([ BillingComponent ], (billing:BillingComponent) => {
        expect(billing.phoneExtnMask).toEqual([/[1-9]/, /\d/, /\d/, /\d/]);
    }));
    it('Should have ` ngOnInit()` will be called ',inject([ BillingComponent ], (billing:BillingComponent) => {
    billing.ngOnInit();
        expect(document.body.scrollTop).toBe(0);
    }));
     it('Should create a `  billingAddressValidation()`',inject([ BillingComponent ], (billing:BillingComponent) => {
    billing. billingAddressValidation();
        expect(billing.billingValid).toBeTruthy;
    }));
     it('Should have billingdata by default null`',inject([ BillingComponent ], (billing:BillingComponent) => {
     billing.billingAddressValidation() ;
    let billingdata={
          addressLine:'',
            street: '',
            country: 'USA',
            state: '',
            city: '',
            zipCode: ''
    }
      expect(billing.billingAddValData).toEqual(billingdata);
    }));
     it('Should have `ngOnDestroy() ` will be called', inject([ BillingComponent], (billing:BillingComponent) => {
        expect(billing.ngOnDestroy()).toHaveBeenCalled; 
    }));
     it('Should have `ngOnDestroy() ` will be userSubscription to be undefined', inject([ BillingComponent], (billing:BillingComponent) => {
      billing.ngOnDestroy();
        expect(billing.userSubscription.unsubscribe()).toBeUndefined; 
    }));
   });
