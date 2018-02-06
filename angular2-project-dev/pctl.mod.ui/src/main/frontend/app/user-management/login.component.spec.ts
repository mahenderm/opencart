/* tslint:disable */
import { inject, TestBed, fakeAsync } from '@angular/core/testing';
import { Http, BaseRequestOptions, ConnectionBackend, RequestOptions } from '@angular/http';
import { FormGroup,FormControl,Validators,FormBuilder,ReactiveFormsModule } from '@angular/forms';   
import { MockBackend } from '@angular/http/testing';
import { CartService } from '../common/service/cart.service';
import { Response, ResponseOptions, RequestMethod } from '@angular/http';
import { MockConnection } from '@angular/http/testing';
import { Store, StoreModule } from '@ngrx/store';
import { Observable } from 'rxjs/Rx';
import { Observer } from 'rxjs/Observer';
import { provideStore, Dispatcher } from '@ngrx/store';
import { userReducer } from '../common/reducers/user.reducer';
import { AuthService } from '../common/service/auth.service';
import { RouterModule, Router} from '@angular/router';
import { LoginComponent } from './login.component'
import { AppStateService } from '../common/service/app-state.service';
import { Logger } from "../common/logging/default-log.service";
describe('Login Component', () => {
let logincomponent: LoginComponent;
    let mockBackend: MockBackend;
  let router: Router;
   let  location:Location;
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
    },LoginComponent,AuthService,FormBuilder,
       provideStore({user: userReducer}),CartService,Logger,AppStateService
    ]}));
 it('Should have `LoginComponent ` can be created ', inject([ LoginComponent], (logincomponent:LoginComponent) => {
        expect(logincomponent instanceof LoginComponent).toEqual(true);
    }));
    it('Should have  ngOnInit() `',inject([ LoginComponent ], (logincomponent:LoginComponent) => {
       expect(logincomponent.ngOnInit()).toBeTruthy;
    }));
     it('Should have  login() will true `',inject([ LoginComponent ], (logincomponent:LoginComponent) => {
       logincomponent.login();
       expect(logincomponent.isLoading).toBeTruthy;
    }));
      it('should create a `FormGroup`',inject([ LoginComponent ], (logincomponent:LoginComponent) => {
        logincomponent.login();
        expect(logincomponent.loginForm instanceof FormGroup).toBe(true);
    }));
     it('Should have   ngOnInit()  will be Array`',inject([ AuthService ], (service:AuthService) => {
     service.ngOnInit();
        service.user.subscribe(g => {
      expect(g).toEqual([]);
    }); 
    }));
     it('Should have  error will true `',inject([ LoginComponent ], (logincomponent:LoginComponent) => {
       expect(logincomponent.error).toBeTruthy;
    }));
});
