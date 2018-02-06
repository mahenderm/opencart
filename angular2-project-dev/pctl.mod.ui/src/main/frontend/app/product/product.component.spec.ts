/* tslint:disable */
import { Observable } from 'rxjs/Observable';
import { Store, Action } from '@ngrx/store';
import { AppStore } from '../common/models/appstore.model';
import {
  inject,
  TestBed,fakeAsync
} from '@angular/core/testing';
import { Component } from '@angular/core';
import {
  BaseRequestOptions,
  ConnectionBackend,
  Http
} from '@angular/http';
import {  Response, ResponseOptions, RequestMethod } from '@angular/http';
import { MockBackend } from '@angular/http/testing';
import { MockConnection } from '@angular/http/testing';
// Load the implementations that should be tested
import { ProductDescriptionComponent } from './product-desc.component';
import { ProductDetailsComponent } from './product-details.component';
import { ProductFeaturesComponent } from './product-features.component';
import { ProductPricingComponent } from './product-pricing.component';
import { ProductTermSelectionComponent } from './product-term-selection.component';
//import { Router } from '@angular/router';
import { provideStore } from '@ngrx/store';
import { ProductsService } from '../common/service/products.service';
import { PricingService } from '../common/service/pricing.service';
import { AuthService } from '../common/service/auth.service';
import { CartService } from '../common/service/cart.service';
import { AppStateService } from '../common/service/app-state.service';
import { Logger } from "../common/logging/default-log.service";
import { productsReducer } from '../common/reducers/products.reducer';
import { Router, RouterModule ,provideRoutes} from '@angular/router';
import { userReducer } from '../common/reducers/user.reducer';
import { AppState } from '../app.service';

describe('Products Component', () => {

describe('Products Description Component', () => {
   let ProductDescriptionMessage: ProductDescriptionComponent;
  beforeEach(() => {
    ProductDescriptionMessage = new ProductDescriptionComponent();
  });

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
      ProductDescriptionComponent,
      ProductDetailsComponent,
      ProductFeaturesComponent,
      ProductPricingComponent,
      ProductTermSelectionComponent
      
    ]
  }));

    /*To Added Testcases For Landing Page*/
 it('should have Product: ProductDescription', inject([ ProductDescriptionComponent ], (productDescription: ProductDescriptionComponent) => {
    spyOn(console, 'log');
    expect(console.log).not.toHaveBeenCalled();
    productDescription.hasProduct();
    expect(console.log).toHaveBeenCalled();
  }));

  //TODO: Kannan and Niranjan should discuss on the below TC.
  /* it('should have ProductDescriptionComponent data', inject([ ProductDescriptionComponent ], (proDesc: ProductDescriptionComponent) => {
      expect(proDesc.product.name).toEqual(proDesc.product.name);
  })); */

  //TODO: Kannan and Niranjan should discuss on the below TC.
  /* it('should have ProductFeaturesComponent data', inject([ ProductFeaturesComponent ], (proFeatures: ProductFeaturesComponent) => {
      expect(proFeatures.options).toEqual(['Standard CPE', 'High Availability', 'Security Package', 'Tier 1 Support']);
    
  })); */
   it('should have valid ProductDescriptionComponent title is undefined or Defined', inject([ ProductDescriptionComponent ], (proDesc: ProductDescriptionComponent) => {
      expect(proDesc.title).toBeUndefined();
  }));
  it('should have valid ProductDescriptionComponent product is undefined or Defined', inject([ ProductDescriptionComponent ], (proDesc: ProductDescriptionComponent) => {
      expect(proDesc.product).toBeUndefined();
  }));
   it('should have valid ProductDescriptionComponent item is undefined or Defined', inject([ ProductDescriptionComponent ], (proDesc: ProductDescriptionComponent) => {
      expect(proDesc.item).toBeUndefined();
  }));
  describe('ProductService method call :', () => {
  let service: ProductsService = null;
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
        ProductsService, 
        CartService,
        provideStore({names: productsReducer}),
        AuthService,
        AppStateService,
        provideStore({user: userReducer}),
        Logger
          ]
    });
  });
  beforeEach(inject([ProductsService, MockBackend], (ProductsService: ProductsService, mockBackend: MockBackend) => {
    service = ProductsService;
    backend = mockBackend;
  }));
  let data = {
                'productId': 'sdwan1000',
                'name': 'SD WAN',
                'descriptionHtml': 'Software-defined wide area networking (SD-WAN) is the next evolution of private networking. SD-WAN uses software to automate the ongoing configuration of edge routers and push traffic over a mix of private, wireless, and broadband network access.SD-WAN offers a real network solution for businesses trying to keep up with the impact a highly dynamic application environment has on their network quality of service. Our SD-WAN combines CenturyLink network design and management expertise with leading software controls and the ubiquity of any connection type or any vendor. The result is improved flexibility through greater bandwidth that delivers increased visibility into network usage and vastly simplified control. CenturyLink offers four popular configurations of SD-WAN as described below -- or a custom configuration can be set up via a sales agent.',
                'displayImage': '',
                'productType': 'Network',
                'createdAt': '2016-09-22T14:25:08-03:00',
                'updatedAt': '2016-09-22T14:25:08-03:00',
                'terms': [
                  {
                    'name': '12',
                    'frequency': 'monthly',
                    'shortCode': '12m'
                  },
                  {
                    'name': '24',
                    'frequency': 'monthly',
                    'shortCode': '24m'
                  },
                  {
                    'name': '36',
                    'frequency': 'monthly',
                    'shortCode': '36m'
                  }
                ],
                'productVariants': [
                  {
                    'variantId': 'sdwanbasic',
                    'productId': 'sdwan1000',
                    'name': 'SD-WAN BASIC',
                    'descriptionHtml': 'Software-defined wide area networking (SD-WAN) is the next evolution of private networking',
                    'displayImage': '',
                    'sku': 'SDWANBASIC',
                    'displayPosition': 1,
                    'servicePlan': 'basic',
                    'serviceVendor': 'versa',
                    'serviceTransport': 'customer',
                    'securityPkg': '1',
                    'standardCpe': '1',
                    'haServicePlan': '',
                    'haServiceVendor': '',
                    'haServiceTransport': '',
                    'haSecurityPkg': '',
                    'haCpe': '0',
                    'tier1Support': '1'
                  },
                  {
                    'variantId': 'sdwanbasicha',
                    'productId': 'sdwan1000',
                    'descriptionHtml': 'Software-defined wide area networking (SD-WAN) is the next evolution of private networking',
                    'displayImage': '',
                    'name': 'SD-WAN BASIC HA',
                    'sku': 'SDWANBASICHA',
                    'displayPosition': 1,
                    'servicePlan': 'basic',
                    'serviceVendor': 'versa',
                    'serviceTransport': 'customer',
                    'securityPkg': '1',
                    'standardCpe': '1',
                    'haServicePlan': 'basic',
                    'haServiceVendor': 'versa',
                    'haServiceTransport': 'ctl',
                    'haSecurityPkg': '0',
                    'haCpe': '1',
                    'tier1Support': '1'
                  },
                  {
                    'variantId': 'sdwanpremium',
                    'productId': 'sdwan1000',
                    'name': 'SD-WAN PREMIUM',
                    'descriptionHtml': 'Software-defined wide area networking (SD-WAN) is the next evolution of private networking',
                    'displayImage': '',
                    'sku': 'SDWANPREMIUM',
                    'displayPosition': 3,
                    'servicePlan': 'premium',
                    'serviceVendor': 'versa',
                    'serviceTransport': 'customer',
                    'securityPkg': '1',
                    'standardCpe': '1',
                    'haServicePlan': '',
                    'haServiceVendor': '',
                    'haServiceTransport': '',
                    'haSecurityPkg': '1',
                    'haCpe': '0',
                    'tier1Support': '1'
                  },
                  {
                    'variantId': 'sdwanpremiumha',
                    'productId': 'sdwan1000',
                    'name': 'SD-WAN PREMIUM HA',
                    'descriptionHtml': 'Software-defined wide area networking (SD-WAN) is the next evolution of private networking',
                    'displayImage': '',
                    'sku': 'SDWANPREMIUMHA',
                    'displayPosition': 1,
                    'servicePlan': 'premium',
                    'serviceVendor': 'versa',
                    'serviceTransport': 'customer',
                    'securityPkg': '1',
                    'standardCpe': '1',
                    'haServicePlan': 'premium',
                    'haServiceVendor': 'versa',
                    'haServiceTransport': 'ctl',
                    'haSecurityPkg': '1',
                    'haCpe': '1',
                    'tier1Support': '1'
                  }
                ]
              };
 it('Should have response with json data from service method call - loadProduct()', (done) => {
    backend.connections.subscribe((connection: MockConnection) => {
      let options = new ResponseOptions({
        body: JSON.stringify(data)
      });
      connection.mockRespond(new Response(options));
      expect(connection.request.method).toEqual(RequestMethod.Get);
      expect(connection.request.url).toMatch(/productMS/);
      expect(connection.request.headers.get('Content-Type')).toEqual('application/json');       
    });
    service.loadProduct().subscribe((response) => {
        expect(response).toEqual(data);
        done();
      });
  });
  });//ProductsService method call end

});

describe('Products Details Component', () => {
   let ProductDescriptionMessage: ProductDescriptionComponent;
  beforeEach(() => {
    ProductDescriptionMessage = new ProductDescriptionComponent();
  });

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
      ProductDescriptionComponent,
      ProductDetailsComponent,
      ProductFeaturesComponent,
      ProductPricingComponent,
      ProductTermSelectionComponent,
      ProductsService, 
      CartService,
      PricingService,
      provideStore({names: productsReducer}),
      AuthService,
      AppStateService,
      provideStore({user: userReducer}),
      Logger
      
    ]
  }));

   it('should have Product Details Component Title', inject([ ProductDetailsComponent ], (proDetails: ProductDetailsComponent) => {
    expect(proDetails.title).toBe('SD-WAN Product Description');
  }));
  it('should have ProductDetailsComponent activeNav Value', inject([ ProductDetailsComponent ], (proDetails: ProductDetailsComponent) => {
    expect(proDetails.activeNav).toBe('description');
  }));
   it('should have ProductDetailsComponent activeTerm Value is not defined', inject([ ProductDetailsComponent ], (proDetails: ProductDetailsComponent) => {
    expect(proDetails.activeTerm).toMatch('');
  }));
   it('should have ProductDetailsComponent errorAlert Value is Null', inject([ ProductDetailsComponent ], (proDetails: ProductDetailsComponent) => {
    expect(proDetails.errorAlert).toBeNull();
  }));
   it('should have ProductDetailsComponent loading Value(boolean) is false', inject([ ProductDetailsComponent ], (proDetails: ProductDetailsComponent) => {
    expect(proDetails.loading).toBeFalsy();
  }));
   it('should have ProductDetailsComponent error Value(boolean) is false', inject([ ProductDetailsComponent ], (proDetails: ProductDetailsComponent) => {
    expect(proDetails.error).toBeFalsy();
  }));
   it('should have ProductDetailsComponent term Value', inject([ ProductDetailsComponent ], (proDetails: ProductDetailsComponent) => {
    expect(proDetails.term).toEqual('12');
  }));
   it('should have ProductDetailsComponent currentPage Value', inject([ ProductDetailsComponent ], (proDetails: ProductDetailsComponent) => {
    expect(proDetails.currentPage).toEqual(4);
  }));
   it('should have ProductDetailsComponent totalItems Value', inject([ ProductDetailsComponent ], (proDetails: ProductDetailsComponent) => {
    expect(proDetails.totalItems).toEqual(64);
  }));
   it('should have ProductDetailsComponent maxSize Value', inject([ ProductDetailsComponent ], (proDetails: ProductDetailsComponent) => {
    expect(proDetails.maxSize).toEqual(5);
  }));
});
describe('Product Features Component', () => {
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
        provide:Router,
         useClass: class { navigate = jasmine.createSpy("navigate"); }
      },
      AppState,
      ProductDescriptionComponent,
      ProductDetailsComponent,
      ProductFeaturesComponent,
      ProductPricingComponent,
      ProductTermSelectionComponent,
      ProductsService, 
      CartService,
      PricingService,
      provideStore({names: productsReducer}),
      AuthService,
      AppStateService,
      provideStore({user: userReducer}),
      Logger
    ]
  }));
   it('should have ProductFeaturesComponent data', inject([ ProductFeaturesComponent ], (proFeatures: ProductFeaturesComponent) => {
      expect(proFeatures.options).toEqual(['Standard CPE', 'High Availability', 'Security Package', 'Tier 1 Support']);
     
    //expect(proDesc.CurrentProduct).toEqual(proDesc.CurrentProduct);
  }));
});
describe('Product Pricing Component', () => {
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
        provide:Router,
         useClass: class { navigate = jasmine.createSpy("navigate"); }
      },
      AppState,
      ProductDescriptionComponent,
      ProductDetailsComponent,
      ProductFeaturesComponent,
      ProductPricingComponent,
      ProductTermSelectionComponent,
      ProductsService, 
      CartService,
      PricingService,
      provideStore({names: productsReducer}),
      AuthService,
      AppStateService,
      provideStore({user: userReducer}),
      Logger
    ]
  }));
   it('should have ProductPricingComponent data', inject([ ProductPricingComponent ], (productPricing: ProductPricingComponent) => {
      expect(productPricing.options).toEqual(['Product Option', 'With My Own Transport', 'With CenturyLink Transport']);
      }));
});
});