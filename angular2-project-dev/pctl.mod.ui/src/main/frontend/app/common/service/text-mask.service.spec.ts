/* tslint:disable */
import {  inject,TestBed ,fakeAsync} from '@angular/core/testing';
import { Http, BaseRequestOptions,ConnectionBackend } from '@angular/http';
import { MockBackend } from '@angular/http/testing';
import { Router, RouterModule ,provideRoutes} from '@angular/router';
import { ProductsService } from '../service/products.service';
import {  Response, ResponseOptions, RequestMethod } from '@angular/http';
import { MockConnection } from '@angular/http/testing';
import { Store } from '@ngrx/store';
import { AppStore } from '../models/appstore.model';
import { ReflectiveInjector } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import {provideStore} from '@ngrx/store';
import { TextMaskService } from './text-mask.service';

describe('TextMask Service :', () => {
  let service: TextMaskService = null;
  let backend: MockBackend = null;
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        MockBackend,
        BaseRequestOptions,
        {
          provide: Http,
          useFactory: (backendInstance: MockBackend, defaultOptions: BaseRequestOptions) => {
            return new Http(backendInstance, defaultOptions);
          },
          deps: [ MockBackend, BaseRequestOptions ]
        },
         { 
        provide: Router, 
        useClass: class { navigate = jasmine.createSpy("navigate"); }
        },
    TextMaskService]
    });
  });
  beforeEach(inject([TextMaskService, MockBackend], (TextMaskService: TextMaskService, mockBackend: MockBackend) => {
    service = TextMaskService;
    backend = mockBackend;
  }));

  it('Should have  MaskForUsaZip  will be  undefined`',inject([ TextMaskService ], (service:TextMaskService) => {
    
        expect(service.getMaskForUsaZip()).toBeUndefined;
        
    }));
    it('Should have  getPhoneNumberMaskFormat  will be  valid`',inject([ TextMaskService ], (service:TextMaskService) => {
    
        expect(service.getPhoneNumberMaskFormat()).toEqual(['(', /[1-9]/, /\d/, /\d/, ')', ' ', /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/]);
    }));

    it('Should have  getPhoneNumberExtnMaskFormat  will be  valid`',inject([ TextMaskService ], (service:TextMaskService) => {
        expect(service. getPhoneNumberExtnMaskFormat()).toEqual([/[1-9]/, /\d/, /\d/, /\d/]);
    }));
});