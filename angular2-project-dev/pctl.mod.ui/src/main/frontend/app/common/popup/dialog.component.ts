import { Component, OnInit, Input, Output, OnChanges, EventEmitter, trigger, state, style, animate, transition } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { Observable } from 'rxjs/Observable';
import { CartService } from '../service/cart.service';
import {
  ShoppingCart,
  LineItem,
  SDWANLocationInfo,
  ContactInfo,
  EnterpriseAddress,
  LocationInfo
} from '../models/cart.model';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css'],
  animations: [
    trigger('dialog', [
      transition('void => *', [
        style({ transform: 'scale3d(.3, .3, .3)' }),
        animate(100)
      ]),
      transition('* => void', [
        animate(100, style({ transform: 'scale3d(.0, .0, .0)' }))
      ])
    ])
  ]
})
export class DialogComponent implements OnInit {
  @Input() closable = true;
  @Input() visible: boolean;
  @Input() descriptionHtml: string;
  @Input() title: string;
  @Input() pdfURL: string;
  @Output() visibleChange: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Output() tncValue: EventEmitter<boolean> = new EventEmitter<boolean>();
  enable: boolean = false;
  cartSubscription: Subscription;
  cart: Observable<ShoppingCart>;
  lineItem: LineItem[];
  totalPrice: number;

  constructor(public cartService: CartService) {
    this.cart = cartService.cart;
    this.totalPrice = 0;
  }

  ngOnInit() { }

  close() {
    this.enable = false;
    this.visible = false;
    this.visibleChange.emit(this.visible);
  }

  sendValue(agree: boolean){
      this.enable = false;
      this.tncValue.emit(agree);
      this.visible = false;
      this.visibleChange.emit(this.visible);
  }

  enableButton(checked){
    this.enable = checked;
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

  ngOnDestroy() {
     if (this.cartSubscription !== undefined){
        this.cartSubscription.unsubscribe();
      }
  }
}