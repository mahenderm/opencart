import { Component , OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { Store } from '@ngrx/store';
import { Subscription } from 'rxjs/Subscription';

import { AppStore } from '../common/models/appstore.model';
import { User } from '../common/models/user.model';
import {
  ShoppingCart,
  LineItem,
  SDWANLocationInfo,
  ContactInfo,
  EnterpriseAddress,
  LocationInfo
} from '../common/models/cart.model';
import { AuthService } from '../common/service/auth.service';
import { CartService } from '../common/service/cart.service';

@Component({
  selector: 'ctl-nav-bar',
  styleUrls: ['./navbar.component.css'],
  templateUrl: './navbar.component.html'
})
export class NavBarComponent implements OnDestroy {
  ctlLogo = 'assets/img/centurylink-logo-white-text.png';

  user: Observable<User>;
  cart: Observable<ShoppingCart>;
  cartSubscription: Subscription;
  lineItem: LineItem[];
  totalPrice: number;

  constructor(
    private router: Router,
    public authService: AuthService,
    public cartService: CartService,
    public store: Store<AppStore>) {
    this.user = authService.user;
    this.cart = cartService.cart;
    authService.init();
    this.totalPrice = 0;
    //   this.user.subscribe(v => console.log(v));
  }

  getTotalPrice() {
    //return 0;
    //this.totalPrice = 0;
    let price = 0;
    this.cartSubscription = this.cart.subscribe(
      cart => {
        if (cart.lineItems === undefined)
          return;
        this.lineItem = cart.lineItems;
        if (this.lineItem !== undefined && 
            this.lineItem.length !== undefined) {
          for (let i = 0; i < this.lineItem.length; i++) {
            let qty = (this.lineItem[i].locations !== undefined &&
                    this.lineItem[i].locations.length !== undefined) ?
                    this.lineItem[i].locations.length : 0;
            price += this.lineItem[i].unitPrice * qty;
          }
        }
      });
    return this.totalPrice = price;
  }

  login() {
    // this.authService.login('tsukhu@hcl.com', 'xxx');
    // this.router.navigate(['/login']);
  }

  logout() {
    this.authService.logout();
  }

  // Navigating to Order Summary page
  summaryRoute(){
    // Navigating to previous page when user is in order summary from Navbar
    if(this.router.url !== '/order-summary-detail'){
      this.store.dispatch({ type: 'PREVIOUS_URL', payload: this.router.url });
    }
    this.router.navigate(['/order-summary-detail']);
  }

   ngOnDestroy() {
     if (this.cartSubscription !== undefined){
        this.cartSubscription.unsubscribe();
      }
  }

}
