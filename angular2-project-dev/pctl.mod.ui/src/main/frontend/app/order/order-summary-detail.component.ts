import { Component, OnDestroy, Input } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Subscription } from 'rxjs/Subscription';
import { Store, Action } from '@ngrx/store';
import { Router } from '@angular/router';
import { AppStore } from '../common/models/appstore.model';
import { ShoppingCart, LineItem } from '../common/models/cart.model';
import { User } from '../common/models/user.model';
import { Pricing, ActiveStatus } from '../common/models/pricing.model';
import { CartService } from '../common/service/cart.service';
import { AuthService } from '../common/service/auth.service';
import { PricingService } from '../common/service/pricing.service';

@Component({
  selector: 'order-summary-detail',
  styleUrls: ['./order-summary-detail.component.css'],
  templateUrl: './order-summary-detail.component.html'
})
export class OrderSummaryDetailComponent implements OnDestroy {

  cart: Observable<ShoppingCart>;
  user: Observable<User>;
  cartSubscription: Subscription;
  lineItem: LineItem[];
  totalPrice = 0;

  constructor(public cartService: CartService,
              public store: Store<AppStore>,
              private authService: AuthService,
              private router: Router,
              private pricingService: PricingService) {
    this.user = <Observable<User>>store.select('user');         // user store
    this.cart = cartService.cart;
    this.totalPrice = 0;
    //   this.user.subscribe(v => console.log(v));
  }

  /**
   * Looping the product to fetch the unitPrice and locations
   * and finally multiplying number of locations per product and per product's unitPrice
   * Summing up to find totalPrice
   */
  getTotalPrice() {
    //this.totalPrice = 0;
    let price = 0;
    this.cartSubscription = this.cart.subscribe(
      cart => {
        if (cart.lineItems === undefined)
          return;
        this.lineItem = cart.lineItems;
        if (this.lineItem.length !== undefined) {
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

  editLineItem(item: LineItem){
    this.updateORdeleteLineItem(item, 'EDIT_ITEM');
  }
  
  deleteLineItem(item: LineItem){
    this.updateORdeleteLineItem(item, 'REMOVE_ITEM');
  }

  private updateORdeleteLineItem(item: LineItem,action: string){
    let status: ActiveStatus={
      mode: 'own',
      sku: item.productTemplateId,
      term: item.term,
      name: item.productTemplateName,
      transport: 'With My Own Transport'
    };
    this.pricingService.setActiveSelection(status);
    let cartId: string;
    this.cartSubscription = this.cart.subscribe(
      cart => {
        cartId = cart.id;
      });
      let lineItem: LineItem;

    lineItem = {
      id: item.id,
      productName: 'SD WAN',
      productId:  item.productId,
      productTemplateName: item.productTemplateName,
      productTemplateId: item.productTemplateId,
      unitPrice: item.unitPrice,
      locations: [],
      term: item.term
    };
      let jwtLocation = this.authService.getJWTToken();
      let requestOptions = this.authService.setRequestOptions('',jwtLocation);
      if(action === 'REMOVE_ITEM'){
        this.cartService.deleteItem(cartId, lineItem, requestOptions);
      } else{
        this.cartService.addItem(cartId, lineItem,requestOptions).subscribe(
        action => {
        this.store.dispatch(action);
        this.router.navigate(['/locations']);
      }
      );
      }
  }

  ngOnDestroy() {
    // Unsubscribing is called to avoid MemoryLeak
    if (this.cartSubscription !== undefined)       
      this.cartSubscription.unsubscribe();
  }

}
