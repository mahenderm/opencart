import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Pricing, ActiveStatus } from '../common/models/pricing.model';
import { PricingService } from '../common/service/pricing.service';
import { Router } from '@angular/router';
import { Store, Action } from '@ngrx/store';
import { AppStore } from '../common/models/appstore.model';
import { CartState, ShoppingCart, LineItem } from '../common/models/cart.model';
import { CartInfo } from '../common/models/user.model';
import { FingerPrintService } from '../common/service/fingerprint.service';
import { CartService } from '../common/service/cart.service';
import { AppStateService } from '../common/service/app-state.service';
import { AuthService } from '../common/service/auth.service';

@Component({
  selector: 'product-pricing',
  styleUrls: ['./product-pricing.component.css'],
  templateUrl: './product-pricing.component.html'
})
export class ProductPricingComponent {
  @Input() prices: Pricing[];
  @Input() term: string;
  @Input() activeTerm: string;
  @Input() status: ActiveStatus;
  transport: string;
  currentPrice: number;
  activeIndex: string;
  selected: boolean;
  showTerm: boolean;
  termSelected: boolean;
  // status: ActiveStatus;
  options = ['Product Option', 'With My Own Transport', 'With CenturyLink Transport'];

  public getCurrentPrice(term: string ,
              sku: string ,
              mode: string): number {
    // get current selected price variant
    let currentPricing = this.prices.find( price => price.sku === sku);
    if (mode === 'own') {
      return currentPricing.priceList.find( priceList => priceList.term === term).own;
    }else {
      return currentPricing.priceList.find( priceList => priceList.term === term).ctl;
    }
  }


  constructor(
    private router: Router,
    private pricingService: PricingService,
    private cartService: CartService,
    private authService: AuthService,
    private appStateService: AppStateService,
    public store: Store<AppStore>) {
    this.selected = false;
    this.showTerm = false;
    this.termSelected = false;

  }

  onTermClick(term: string) {
    this.term = term;
    this.activeTerm = term;
    this.termSelected = true;
    this.showTerm = true;
    this.selected = false;
    this.status = {
      mode: 'own',
      sku: '',
      term: this.term,
      productVariant: this.prices[0],
      transport: this.options[1]
    };
    // commenting to stop showing the default selection
    // this.getCurrentSelection();
  }

  getCurrentSelection() {
    switch (this.term) {
      case '12':
        this.currentPrice =
        this.prices[0].priceList.find(priceList => priceList.term === '12').own;
        break;
      case '24':
        this.currentPrice =
        this.prices[0].priceList.find(priceList => priceList.term === '24').own;
        break;
      case '36':
        this.currentPrice =
        this.prices[0].priceList.find(priceList => priceList.term === '36').own;
        break;
      default:
        // this.currentPrice =
        // this.prices[0].priceList.find(priceList => priceList.term === '12').own;
        break;
    };
  }

  /**
   * Adding selected Term, Price and Transport type in User's ActiveStatus State
   */
  onPriceSelection(mode: string, sku: string, term: string, variant: Pricing) {
    let price = this.getCurrentPrice(term, sku, mode);
    this.status = {
      mode: mode,
      sku: sku,
      term: this.term,
      name: variant.name,
      productVariant: variant,
      transport: mode === 'own' ? this.options[1] : this.options[2]
    };
    this.currentPrice = price;
    this.selected = true;
    this.showTerm = false;
    // console.log(AppStateService.getState(this.store));
    // this.pricingService.setActiveSelection(this.status);
  }

  nextPage() {
    // Adding Current selection to User Store
    this.pricingService.setActiveSelection(this.status);
    // Routing to Contact-Us page if user selected own Transport
    if (this.status.mode === 'ctl') {
      this.router.navigate(['/contact-us']);
      return;
    }
    let currentStore = this.appStateService.getState();
    // let lineItemCount = currentStore.cart.lineItems.length + 1;
    // currentStore.prices.name;
    // currentStore.prices.product_id;
    // this.status.productVariant.name;
    // this.status.productVariant.sku;
    let lineItem: LineItem;

    lineItem = {
      id: FingerPrintService.UUID(),
      productName: 'SD WAN',
      productId:  this.status.productVariant.productId,
      productTemplateName: this.status.productVariant.name,
      productTemplateId: this.status.productVariant.sku,
      unitPrice: this.currentPrice,
      term: this.term,
      locations: []
    };
    // console.log('next page additem');
    let jwtLocation = this.authService.getJWTToken();
    let requestOptions = this.authService.setRequestOptions('',jwtLocation);
    // adding the selected product to the cart as well as triggering cart API
    this.cartService.addItem(currentStore.cart.id, lineItem,requestOptions).subscribe(
      action => {
        this.store.dispatch(action);
      /*  this.authService.updateUserCartInfo(<CartInfo>{
          cartState: CartState.LandingPage,
          shoppingCartId: currentStore.cart.id,
          cartItemCount: lineItemCount
        });
        */
        //     this.authService.updateCartInfo(currentStore.cart);
        this.router.navigate(['/locations']);
      }
    );
  }

  /*  getState(store: Store<AppStore>): AppStore {
      let state: AppStore;
      store.take(1).subscribe(s => state = s);
      return state;
    }*/
}
