import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { Store, Action } from '@ngrx/store';
import { AppStore } from '../common/models/appstore.model';
import { ActiveStatus } from '../common/models/pricing.model';
import { AppStateService } from '../common/service/app-state.service';
import { CartService } from '../common/service/cart.service';
import { AuthService } from '../common/service/auth.service';
import {
  ShoppingCart,
  LineItem,
  SDWANLocationInfo,
  ContactInfo,
  EnterpriseAddress,
  LocationInfo,
  LocationDisplay
} from '../common/models/cart.model';
import { Router } from '@angular/router';
import { Breadcrumb } from '../common/models/breadcrumb.model';
import * as _ from 'lodash';

@Component({
  selector: 'locations-list',
  styleUrls: ['./locations-list.component.css'],
  templateUrl: './locations-list.component.html'
})

export class LocationsListComponent implements OnInit {

  currentStore: AppStore;
  locations: LocationDisplay[] = [];
  lineItems: LineItem[];
  locationValid: boolean = true;
  itemName: string;
  @Output() deleteEvent = new EventEmitter();
  @Output() editEvent = new EventEmitter();
  selected: LocationDisplay;
  pagedLocations: LocationDisplay[] = [];
  public totalItems: number = 100;
  public currentPage: number = 1;
  public maxSize: number = 200;
  public itemsPerPage: number = 2;
  // pagination counters
  pageSize = 2;

  @Input() set items(locationsDisplay: LocationDisplay[]) {
    let currentStore = this.appStateService.getState();
    this.lineItems = currentStore.cart.lineItems;
    if (this.lineItems !== undefined) {
      this.locationValid = true;
        for (var i = 0; i < this.lineItems.length; i++) {
          this.itemName = this.lineItems[i].productTemplateName;
            if (this.lineItems[i].locations === undefined){
                this.locationValid = false;
                break;
            }
            let eachLocations: SDWANLocationInfo[] = this.lineItems[i].locations;
            if (eachLocations.length === undefined){
                this.locationValid = false;
                break;
            }else{
                if(eachLocations.length < 2){
                    this.locationValid = false;
                    break;
                }
            }
        }
    }
    if (locationsDisplay !== undefined || locationsDisplay.length > 0) {
      this.locations = locationsDisplay;
      // this.pagedLocations = _.take(this.locations, this.pageSize);
      this.totalItems = _.size(this.locations);
      this.pagedLocations = _.take(this.locations, this.itemsPerPage);
    }
  }

  constructor(
    private appStateService: AppStateService,
    private router: Router,
    public store: Store<AppStore>,
    private cartService: CartService,
    public authService: AuthService) {
    this.currentStore = this.appStateService.getState();
  }

  ngOnInit() {
    //  console.log(this.lineItems);
  }

  deleteLocation(location) {
    this.deleteEvent.emit(location);
  }

  editLocation(location) {
    this.editEvent.emit(location);
  }

  /*
    onPageChanged(page) {
      let startIndex = (page - 1) * this.pageSize;
      this.pagedLocations = _.take(_.drop(this.locations, startIndex), this.pageSize);
    }
  */

  public pageChanged(event: any): void {
    let startIndex = (event.page - 1) * this.itemsPerPage;
    this.pagedLocations = _.take(_.drop(this.locations, startIndex), this.itemsPerPage);
  }

  billingPage() {
    // this.pricingService.setActiveSelection(this.status);    
    this.currentStore = this.appStateService.getState();
    // let lineItem: LineItem;
    // let lineItemCount = this.currentStore.cart.lineItems.length;
    let breadcrumbs: Breadcrumb[];
    breadcrumbs = [{
      isRoute: false,
      name: 'Locations',
      routerLink: '/locations'
    },
    {
      isRoute: false,
      name: 'Billing',
      routerLink: ''
    }];
    this.store.dispatch({ type: 'UPDATE_BREADCRUMB', payload: breadcrumbs });
    // console.log("billing brdcmrb " + JSON.stringify(breadcrumbs));
    // console.log('billing page ' + lineItemCount);



    let cartID = this.currentStore.user.cartInfo.shoppingCartId;
    let jwtToken = this.authService.getJWTToken();
    let requestOptions = this.authService.setRequestOptions('', jwtToken);
    // call checkOut and load the order Id in the user store
    this.cartService.checkOut(cartID, requestOptions).subscribe(
      action => {
        this.store.dispatch(action);
        let userLoggedInCheck = this.currentStore.user.loggedIn;
        if(!userLoggedInCheck){
          this.store.dispatch({ type: 'FORCE_USER_CHECK', payload: true });
          this.router.navigate(['/login']);
          return;
        } else 
          this.router.navigate(['/thanks']);
      }
    );

  }

}
