import { Component, Input, Output, EventEmitter, OnInit,OnDestroy } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import {
  ShoppingCart,
  LineItem,
  SDWANLocationInfo,
  ContactInfo,
  EnterpriseAddress,
  LocationInfo,
  LocationDisplay
} from '../common/models/cart.model';
import { Subscription } from 'rxjs/Subscription';
import { CartService } from '../common/service/cart.service';
import { Store, Action } from '@ngrx/store';
import { Router } from '@angular/router';
import { AppStore } from '../common/models/appstore.model';
import { Pricing, ActiveStatus } from '../common/models/pricing.model';
import { User } from '../common/models/user.model';
import { PricingService } from '../common/service/pricing.service';
import { AppStateService } from '../common/service/app-state.service';
import { CountryStateService } from '../common/service/country-state.service';
import { Country } from '../common/models/country.model';
import { AuthService } from '../common/service/auth.service';
import { Logger } from '../common/logging/default-log.service';
import { FormComponent } from '../common/service/unsaved.guard';

/**
 * Locations smart component
 * This drives the entire locations page
 * Initialized the locations form and list based on the product selection
 * and user/cart state
 */
@Component({
  selector: 'locations',
  templateUrl: './locations.component.html'
})

export class LocationsComponent implements OnInit,OnDestroy,FormComponent {
  form: FormGroup;
  cart: Observable<ShoppingCart>;
  cartSubscription: Subscription;
  lineItem: LineItem;
  locations: LocationDisplay[] = [];
  // Subscribe to ActiveStatus from user Store
  user: Observable<User>;
  currentStore: AppStore;
  formData: SDWANLocationInfo;
  countries: Country[];
  isFormDirty: boolean;

  constructor(
    private countryStateService: CountryStateService,
    private appStateService: AppStateService,
    public cartService: CartService,
    public store: Store<AppStore>,
    public authService: AuthService,
    private logger: Logger,
    private router: Router) {
    this.user = <Observable<User>>store.select('user');         // user store
    this.cart = <Observable<ShoppingCart>>store.select('cart'); // cart store
    let self = this;
    this.currentStore = this.appStateService.getState();
    this.countries = this.countryStateService.getCountries();
    this.cartSubscription = this.cart.subscribe(    // subscribe to cart line item updates
      cart => {
        if (cart.lineItems === undefined)
          return;
        // filter item related to selected project variant
        self.lineItem = cart.lineItems.find(lineItem => {
          return lineItem.productTemplateId === self.currentStore.user.status.sku;
        });
        // After deleting the item from Order Summary, if clicked back trying to find that lineItem
        // so restricting it and navigating to HomePage
        if(self.lineItem === undefined){
          this.router.navigate(['/home']);
          return;
        }
        self.locations = [];
        // create locations Display array based on locations for current lineItem
        if (self.lineItem.locations !== undefined) {
          self.lineItem.locations.map(
            item => {
              let location: LocationDisplay = {
                id: item.id,
                name: item.serviceAddress.locationName,
                contact: item.serviceContact.firstName + ' ' + item.serviceContact.lastName,
                address: item.serviceAddress.addressLine + ',' +
                item.serviceAddress.city + ',' +
                this.getStringValue(item.serviceAddress.street,',') +
                item.serviceAddress.state + ',' +
                item.serviceAddress.country + ',' +
                item.serviceAddress.zipCode,
                serviceContact: {
                  firstAndLastName:
                  item.serviceContact.firstName + ' ' + item.serviceContact.lastName,
                  email: item.serviceContact.email,
                  phoneNumber: item.serviceContact.phoneNumber,
                  phoneNumberExtn: item.serviceContact.phoneNumberExtn
                },
                serviceAddress: {
                  locationName: item.serviceAddress.locationName,
                  addressLine: item.serviceAddress.addressLine,
                  addressDetails: item.serviceAddress.city + ',' +
                  this.getStringValue(item.serviceAddress.street,',') +
                  item.serviceAddress.state + ',' +
                  item.serviceAddress.country + ',' +
                  item.serviceAddress.zipCode
                },
                shippingAddress: {
                  locationName: item.shippingAddress.locationName,
                  addressLine: item.shippingAddress.addressLine,
                  addressDetails: item.shippingAddress.city + ',' +
                  this.getStringValue(item.shippingAddress.street,',') +
                  item.shippingAddress.state + ',' +
                  item.shippingAddress.country + ',' +
                  item.shippingAddress.zipCode
                }
              };
              self.locations.push(location);
            }


          );
        }
      }
    );
  }

  /**
   * Find Location info from the LineItem based on ID
   * @return SDWANLocationInfo;
   */
  getLocationById(id: string): SDWANLocationInfo {
    return this.lineItem.locations.find(location =>
      location.id === id);
  }

  ngOnInit() {

  }
  
  /**
   * Reset the form data model
   */
  resetDataModel() {
    this.formData = {
      id: '',
      serviceContact: {
  //      id: '',
        email: '',
        firstName: '',
        lastName: '',
        phoneNumber: '',
        phoneNumberExtn: ''
      },
      serviceAddress: {
  //      id: '',
        locationName: '',
        addressLine: '',
        street: '',
        city: '',
        country: this.countries[0].countryCode,
        state: '',
        zipCode: ''
      },
      shippingAddress: {
 //       id: '',
        locationName: '',
        addressLine: '',
        street: '',
        city: '',
        country: this.countries[0].countryCode,
        state: '',
        zipCode: '',
        checkAddress: ''
      }
    };
  }

  /**
   * Location Form Edit action listener
   * Load the Form with selected location in the Locations List
   */
  editAction($event) {
    //this.logger.log($event);
    this.resetDataModel();
    let locationDisplay: LocationDisplay;
    locationDisplay = $event;
    this.formData = this.getLocationById(locationDisplay.id);
    //  this.cartService.deleteLocation(delLocationInfo);
  }

  /**
   * Location Form Delete action listener
   * Perform delete of the location from the store and the cart
   */
  deleteAction($event) {
    let locationDisplay: LocationDisplay;
    locationDisplay = $event;
    if (this.formData) {
      if ((this.formData.id !== undefined && this.formData.id.trim() !== '') &&
          this.formData.id === locationDisplay.id) {
        this.resetDataModel();
      }
    }
    let delLocationInfo: LocationInfo;
    let sdwanLocationInfo = this.getLocationById(locationDisplay.id);
    delLocationInfo = {
      productTemplateId: this.currentStore.user.status.sku,
      location: sdwanLocationInfo
    };
    let jwtToken = this.authService.getJWTToken();
    let requestOptions = this.authService.setRequestOptions('',jwtToken);
    this.cartService.deleteLocation(this.currentStore.cart.id, delLocationInfo,requestOptions);
  }

  /**
   * Form Location update action listener
   */
  onLocationUpdate($event) {
    let sdwanLocation: SDWANLocationInfo;
    let newLocation = {
      productTemplateId: this.currentStore.user.status.sku,
      location: <SDWANLocationInfo>$event
    };
    let jwtToken = this.authService.getJWTToken();
    let requestOptions = this.authService.setRequestOptions('',jwtToken);
    this.cartService.updateLocation(this.currentStore.cart.id, <LocationInfo>newLocation,requestOptions);
  }

  /**
   * Form Location add action Listener
   */
  onLocationAdd($event) {
    let sdwanLocation: SDWANLocationInfo;
    let newLocation = {
      productTemplateId: this.currentStore.user.status.sku,
      location: <SDWANLocationInfo>$event
    };
    let jwtToken = this.authService.getJWTToken();
    let requestOptions = this.authService.setRequestOptions('',jwtToken);
    this.cartService.addLocation(this.currentStore.cart.id, <LocationInfo>newLocation,requestOptions);
  }

  /**
   * If the location form is dirty we will receive this event
   */
  onFormDirty($event) {
    this.isFormDirty=$event;
  }

   hasUnsavedChanges(): Boolean {
        return this.isFormDirty;
    }

  /**
   * Clean up cart subscription
   */
  ngOnDestroy() {
    if (this.cartSubscription !== undefined)
      this.cartSubscription.unsubscribe();
  }

  /**
   * utility function for adding the string value and prefix 
   * if value is present
   */
  private getStringValue(value: string,suffix: string) {
    if (value === undefined || value.trim().length === 0)
      return '';
    else 
      return value + suffix;
  }
}
