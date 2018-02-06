/* tslint:disable */
import { inject, TestBed } from '@angular/core/testing';
import { Component } from '@angular/core';
import { TestingCompilerImpl } from '@angular/compiler/testing';
import { Router,RouterModule,provideRoutes } from '@angular/router';
import { FormGroup,FormControl,Validators,FormBuilder,ReactiveFormsModule } from '@angular/forms';   
import { ContactUsComponent } from './contact-us.component';
import { ContactService } from '../service/contact.service';
import { MockBackend } from '@angular/http/testing';
import { Http,ConnectionBackend,RequestOptions,RequestMethod,BaseRequestOptions } from '@angular/http';
import { async } from '@angular/core/testing';
import { TextMaskService } from '../service/text-mask.service';
import { userReducer } from '../reducers/user.reducer';
import { provideStore } from '@ngrx/store';
import { Store } from '@ngrx/store';
import { AppStore } from '../models/appstore.model';
import { Logger } from "../logging/default-log.service";
import { AuthService } from '../service/auth.service';
import { CartService } from '../service/cart.service';
import { AppStateService } from '../service/app-state.service';
import { Observable } from 'rxjs/Rx';
import { ContactUs } from '../models/contact-us.model';

   describe('Contact Us', () => {
    beforeEach(() => TestBed.configureTestingModule({
        providers: [
            ContactUsComponent,Logger,
            ContactService,AuthService, AppStateService,
            FormBuilder,CartService, 
            TextMaskService, 
            provideStore({
                user: userReducer
            }), 
            Logger,
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
    //checking whether contactus component creating instance or not
    it('Should `ContactUsComponent` can be created ', inject([ ContactUsComponent,ContactService,FormBuilder,TextMaskService ], (ContactUs:ContactUsComponent,contactService:ContactService,form:FormBuilder,tms:TextMaskService,lo:Logger,ro:Router) => {
        expect(ContactUs instanceof ContactUsComponent).toEqual(true);
    }));
    //checking whether contactus component returning function or not
     it('Should provide a contactUs for the contactUs binding',  inject([ ContactUsComponent,ContactService,FormBuilder,TextMaskService ], (ContactUs:ContactUsComponent,contactService:ContactService,form:FormBuilder,tms:TextMaskService,lo:Logger,ro:Router) => {
      expect(typeof ContactUs.contactUs).toBe('function');
    }));
     it('should have a defined component',inject([ ContactUsComponent,ContactService,FormBuilder,TextMaskService ], (ContactUs:ContactUsComponent,contactService:ContactService,form:FormBuilder,tms:TextMaskService,lo:Logger,ro:Router) => {
        expect(ContactUs).toBeDefined();
    }));
     it('should create a `FormGroup`',inject([ ContactUsComponent,ContactService,FormBuilder,TextMaskService ], (ContactUs:ContactUsComponent,contactService:ContactService,form:FormBuilder,tms:TextMaskService,lo:Logger,ro:Router) => {
        ContactUs.contactUs();
        expect(ContactUs.form instanceof FormGroup).toBe(true);
    }));
    it('Should phoneMask  will be valid ', inject([ ContactUsComponent ], (contactUs: ContactUsComponent) => {
           expect(contactUs.phoneMask).toEqual( [ '(', /[1-9]/, /\d/, /\d/, ')', ' ', /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/ ]);  
       }));
     it('Should phoneMask  will be valid ', inject([ ContactUsComponent ], (contactUs: ContactUsComponent) => {
           expect(contactUs.phoneExtnMask).toEqual([ /[1-9]/, /\d/, /\d/, /\d/ ]);  
       }));
    it('should  `ContactUs` will be valid',inject([ ContactUsComponent,ContactService,FormBuilder,TextMaskService ], (ContactUs:ContactUsComponent,contactService:ContactService,form:FormBuilder,tms:TextMaskService,lo:Logger,ro:Router) => {
        ContactUs.contactUs();
        expect(ContactUs.form.value.firstName).toBe('');
        expect(ContactUs.form.value.lastName).toBe('');
        expect(ContactUs.form.value.primaryPhone).toBe('');
        expect(ContactUs.form.value.email).toBe('');
        expect(ContactUs.form.value.companyName).toBe('');
    }));
   });
