/* tslint:disable */ 
import {inject,TestBed} from '@angular/core/testing';
import {Http, ConnectionBackend,RequestOptions,RequestMethod,BaseRequestOptions} from '@angular/http';
import {Route} from '@angular/router';
import { RouterTestingModule} from '@angular/router/testing';
import { MockBackend } from '@angular/http/testing';
import {provideStore} from '@ngrx/store';
import { Router, RouterModule ,provideRoutes} from '@angular/router';
import { AppState } from './app.service';
import { AuthService } from './common/service/auth.service';
import { AppStateService } from './common/service/app-state.service';
import { FingerPrintService } from './common/service/fingerprint.service';
import { ProductsService } from './common/service/products.service';
import { CartService } from './common/service/cart.service';
import { Logger } from "./common/logging/default-log.service";
import { AppComponent } from './app.component';
import {userReducer} from './common/reducers/user.reducer';
import { ProductDetailsComponent } from './product/product-details.component';
 import {PricingService} from './common/service/pricing.service';
 import { TnCService } from './common/service/tnc.service';

describe('App Component', () => {
      let options: RequestOptions;
      let component: AppComponent;
      let authservice:AuthService;
      let productdetail:ProductDetailsComponent;
      beforeEach(() => TestBed.configureTestingModule({

          providers: [
            {
                provide:AuthService,useClass: class {AuthService}
            }
            ,
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
        AppComponent, AppState,AppStateService,provideStore({userreducer: userReducer}),
        FingerPrintService,ProductsService,CartService,Logger,ProductDetailsComponent,PricingService,TnCService
          ]}));

          
        beforeEach(inject([AppState,AuthService, AppComponent],
          (comp: AppComponent) => {component = comp;
            options = new RequestOptions({method: RequestMethod.Post}); }))
            
          it('Should have a url', inject([ AppComponent,AuthService], (app,auth) => {
          expect(app.angularclassLogo).toEqual('assets/img/angularclass-avatar.png');
        }));
        it('Should have a title', inject([ AppComponent ], (app) => {
          expect(app.name).toEqual('SDWAN Portal');
        }));
        it('Should have a Description', inject([ ProductDetailsComponent ], (product: ProductDetailsComponent) => {
          expect(product.title).toEqual('SD-WAN Product Description');
        }));
        
        it('Should have a method called `initState` in AppComponent', () => {
          expect(AppComponent.prototype.initState).toBeDefined();
        });
        it('APP should have console called: AppComponent', inject([ AppComponent ], (app: AppComponent) => {
          spyOn(console, 'log');
          app.ngOnInit;
          expect(console.log).not.toHaveBeenCalled();
        }));
        it('Should inject AppComponent', inject([AppComponent],
          (component: AppComponent) => {
            expect(component).toBeTruthy();
        }));
  });


  