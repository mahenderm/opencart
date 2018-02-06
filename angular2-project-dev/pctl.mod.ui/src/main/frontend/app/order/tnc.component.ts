import { Component, OnDestroy, ViewContainerRef } from '@angular/core';
import { OrderComponent } from '../order/order.component';
import { Observable } from 'rxjs/Observable';
import { Store } from '@ngrx/store';
import { AppStore } from '../common/models/appstore.model';
import { User } from '../common/models/user.model';
import { Breadcrumb } from '../common/models/breadcrumb.model';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import {
  ShoppingCart,
  LineItem,
  SDWANLocationInfo,
  ContactInfo,
  EnterpriseAddress,
  LocationInfo
} from '../common/models/cart.model';
import { TnCList, TnCInfo } from '../common/models/tnc.model';
import { AuthService } from '../common/service/auth.service';
import { CartService } from '../common/service/cart.service';
import { AppStateService } from '../common/service/app-state.service';
import { OrderService } from './order.service';
import { Logger } from '../common/logging/default-log.service';


/**
 * Terms & Conditions Component
 */
@Component({
  selector: 'tnc',
  styleUrls: ['./tnc.component.css'],
  templateUrl: './tnc.component.html'
})
export class TnCComponent implements OnDestroy {
  user: Observable<User>;
  tncForm: any;
  cart: Observable<ShoppingCart>;               // cart store
  cartSubscription: Subscription;               // subscribe cart
  lineItem: LineItem[];
  totalPrice: number;
  generalTerm: string;
  titleGen: string;
  generalAgreement: boolean;
  generalPdfURL: string;
  generalVersion: string;
  sdwanPdfURL: string;
  sdWanTerm: string;
  sdwanVersion: string;
  titleSD: string;
  sdWanAgreement: boolean;
  visible: boolean = true;

  constructor(
    public store: Store<AppStore>,
    private formBuilder: FormBuilder,
    private router: Router,
    public authService: AuthService,
    public cartService: CartService,
    private appStateService: AppStateService,
    private orderService: OrderService,
    private logger: Logger) {
    this.user = <Observable<User>>store.select('user');
    this.tncForm = this.formBuilder.group({
      tncCheck: [false, <any>Validators.pattern('true')]
    });
    this.user = authService.user;
    this.cart = cartService.cart;
    this.totalPrice = 0;
    this.visible = true;

  }
  onGeneral($event){
    this.generalAgreement = $event;
    let generalTncInfo: TnCInfo = {
      version: this.generalVersion,
      type: 'General',
      agreed: $event
    }
    this.store.dispatch({ type: 'GENERAL_AGREEMENT', payload: generalTncInfo });
    console.log(this.generalAgreement);
  }
  onSDWan($event){
    this.sdWanAgreement = $event;
    let generalTncInfo: TnCInfo = {
      version: this.generalVersion,
      type: 'SDWAN',
      agreed: $event
    }
    this.store.dispatch({ type: 'SDWAN_AGREEMENT', payload: generalTncInfo });
    console.log(this.sdWanAgreement);
  }

  tncSubmit() {
    // TODO: CALL CART CHECKOUT API
    // this.cartService.checkOut();
    // get Order ID and update user State with Order ID
    // call authService to update user with order API
    let currentStore = this.appStateService.getState();
    if(!currentStore.user.generalTerm.agreed || !currentStore.user.sdWanTerm.agreed){
      this.router.navigate(['/thanks']);
      return;
    }
    if (currentStore.user.cartInfo.shoppingCartId === undefined ||
      currentStore.user.cartInfo.orderID === undefined ||
      currentStore.user.billing === undefined) {
      this.logger.error('Error in order submit : incomplete information');
    }
    let cartID = currentStore.user.cartInfo.shoppingCartId;
    let orderID = currentStore.user.cartInfo.orderID;
    let billingDetails = currentStore.user.billing;
    this.orderService.placeOrder(cartID, orderID, billingDetails).subscribe(
      data => {
        //this.logger.log('api msg '+JSON.stringify(data))
        if (data.message === 'Success' && data.code === '200') {
          this.router.navigate(['/order-placed']);
        } else {
          //console.log('Dev ENV ')
          this.router.navigate(['/order-placed']);
        }
      },
      error => {
        this.logger.error('Error in order submit : incomplete information' + error)
      }
    )

  }

  ngOnInit() {
    document.body.scrollTop = 0;
    this.getContractDetails();
  }

  getTnCByType(type: string,tncList: TnCList): TnCInfo {
    return tncList.items.find(item =>
      item.type === type);
  }

  getContractDetails(){
    let currentStore = this.appStateService.getState();
    if (currentStore.tnc === undefined ||
        currentStore.tnc.items === undefined ||
        currentStore.tnc.items.length !== 2) {
          this.logger.error("TNC not loaded");
        }
    let data: TnCList = <TnCList> currentStore.tnc;
    
    let generalTncInfo: TnCInfo = this.getTnCByType('General',data);
    let sdwanTncInfo: TnCInfo = this.getTnCByType('SDWAN',data);

    this.generalTerm = generalTncInfo.descriptionHTML;
    this.sdWanTerm = sdwanTncInfo.descriptionHTML;
    this.generalVersion = generalTncInfo.version;
    this.sdwanVersion = sdwanTncInfo.version;
    this.titleGen = "General Terms & Conditions";
    this.titleSD = "SD-WAN Terms & Conditions";
    this.generalPdfURL = generalTncInfo.pdfURL;
    this.sdwanPdfURL = sdwanTncInfo.pdfURL;

  /*  this.orderService.getContract().subscribe(
      data => {
        this.titleGen = "General Terms & Conditions";
        this.titleSD = "SD-WAN Terms & Conditions";
        if(data[0].type === 'General'){
          this.generalTerm = data[0].descriptionHTML;
          this.sdWanTerm = data[1].descriptionHTML;
        }else{
          this.generalTerm = data[1].descriptionHTML;
          this.sdWanTerm = data[0].descriptionHTML;
        }
      }
    )*/
  }

  /**
   * get lineItems total price value
   */
  getTotalPrice() {
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

  /**
   * Destroy the cart subscription
   */
  ngOnDestroy() {
    if (this.cartSubscription !== undefined)
      this.cartSubscription.unsubscribe();
  }


}
