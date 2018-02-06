/*
 * Angular 2 decorators and services
 */
import { Component, ViewEncapsulation, OnDestroy } from '@angular/core';

import { AppState } from './app.service';
import { Subscription } from 'rxjs/Subscription';

import { Store, Action } from '@ngrx/store';
import { Observable } from 'rxjs/Observable';
import { AppStore } from './common/models/appstore.model';
import { User, EnterpriseInfo, CartInfo } from './common/models/user.model';
import { AuthService } from './common/service/auth.service';
import { FingerPrintService } from './common/service/fingerprint.service';
import { ProductsService } from './common/service/products.service';
import { CartService } from './common/service/cart.service';
import { TnCService } from './common/service/tnc.service';
import { AppStateService } from './common/service/app-state.service';
import { Logger } from './common/logging/default-log.service';

/**
 * App Component
 * Top Level Component
 */
@Component({
  selector: 'app',
  encapsulation: ViewEncapsulation.None,
  styleUrls: [
    './app.component.css'
  ],
  templateUrl: './app.component.html'
})
export class AppComponent implements OnDestroy {
  angularclassLogo = 'assets/img/angularclass-avatar.png';
  name = 'SDWAN Portal';
  user: Observable<User>;
  productServiceSubscription: Subscription;

  // Simple notifications configuration
  notificationsOptions = {
    timeOut: 3000,
    showProgressBar: false,
    pauseOnHover: false,
    clickToClose: false
  };

  constructor(
    public appState: AppState,
    public authService: AuthService,
    public appStateService: AppStateService,
    public store: Store<AppStore>,
    public fingerPrintService: FingerPrintService,
    public prodService: ProductsService,
    private cartService: CartService,
    private tncService: TnCService,
    private logger: Logger) {
    this.user = <Observable<User>>store.select('user');
  }

  ngOnInit() {
    // get JWT token for APIs
    this.authService.clearTokens();
    this.authService.getJWTToken(true);
    this.initState();
  }

  /**
   * Initialize application state
   * Cart, User state, Browser Finger Print, 
   * Restore cart contents from the user state
   * Load Products and Pricing
   */
  initState() {
    this.authService.init();
    // get user state from User Service API and update cart ID


    let currentStore = this.appStateService.getState();
    // create new cart ID and update the user state if 
    // current user state does not have a valid cart
    if (currentStore.user.cartInfo === undefined ||
      currentStore.user.cartInfo.shoppingCartId === undefined ||
      currentStore.user.cartInfo.shoppingCartId === '') {
      let cart = this.cartService.initCart();
      this.authService.updateUserCartInfoFromCart(cart);
    } else { // sync cart & user cartIds based on the user store
      this.cartService.initCartWithID(currentStore.user.cartInfo.shoppingCartId);
      this.authService.updateUserCartInfo(currentStore.user.cartInfo);
    }
    // get brower finger print and load user & cart details 
    // if present in the user service
    this.authService.getFingerPrint();
    // load products and pricing
    this.loadProductAndPrices();
    this.tncService.loadTnC();
  }

  /**
   * Load products and pricing details
   */
  private loadProductAndPrices() {
    this.productServiceSubscription = this.prodService.loadProduct()
      .map(payload => ({ type: 'ADD_PRODUCTS', payload }))
      .subscribe(
      action => {
        this.store.dispatch(action);
        this.store.dispatch(<Action>{ type: 'LOAD_PRICES' });
      },
      error => {
        this.logger.error('Unable to load products: ' + error.message);
      },
      () => {
        // called after success or error callback
      }
      );

  }

  /**
   * Clear existing Subscription
   */
  ngOnDestroy() {
    if (this.productServiceSubscription !== undefined)
      this.productServiceSubscription.unsubscribe();
  }
}
