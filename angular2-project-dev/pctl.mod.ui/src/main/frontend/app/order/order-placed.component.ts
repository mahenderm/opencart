import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Store, Action } from '@ngrx/store';
import { Observable } from 'rxjs/Observable';
import { AppStateService } from '../common/service/app-state.service';
import { AppStore } from '../common/models/appstore.model';
import { CartService } from '../common/service/cart.service';
import { AuthService } from '../common/service/auth.service';
import { User } from '../common/models/user.model';
import { LineItem, SDWANLocationInfo, LocationDisplay } from '../common/models/cart.model';

import { NotificationsService } from 'angular2-notifications';
import { Logger } from '../common/logging/default-log.service';

@Component({
    selector: 'order-placed',
    styleUrls: ['./order-placed.component.css'],
    templateUrl: './order-placed.component.html'
})
export class OrderPlacedComponent {

    user: Observable<User>;
    currentStore: AppStore;
    lineItems: LineItem[];
    locations: LocationDisplay[] = [];

    constructor(
        private authService: AuthService,
        private cartService: CartService,
        private appStateService: AppStateService,
        private router: Router,
        private _notificationsService: NotificationsService,
        public store: Store<AppStore>,
        private logger: Logger) {
        this.currentStore = this.appStateService.getState();
        this.user = <Observable<User>>store.select('user');
    }

    /**
     * Looping the product to fetch locations
     * and adding the locations to Array to show in the view
     */
    ngOnInit() {
        let currentStore = this.appStateService.getState();

        this.lineItems = currentStore.cart.lineItems;
        if (this.lineItems !== undefined) {

            for (var i = 0; i < this.lineItems.length; i++) {
                if (this.lineItems[i].locations === undefined)
                    continue;
                let eachLocations: SDWANLocationInfo[] = this.lineItems[i].locations;
                if (eachLocations.length === undefined)
                    continue;
                for (var j = 0; j < eachLocations.length; j++) {
                    let locations: LocationDisplay = {
                        name: eachLocations[j].serviceAddress.locationName,
                        contact: eachLocations[j].serviceContact.phoneNumber + ' - ' + eachLocations[j].serviceContact.phoneNumberExtn,
                        address: eachLocations[j].serviceAddress.addressLine + ',' +
                        eachLocations[j].serviceAddress.city + ',' +
                        eachLocations[j].serviceAddress.street + ',' +
                        eachLocations[j].serviceAddress.state + ',' +
                        eachLocations[j].serviceAddress.country + ',' +
                        eachLocations[j].serviceAddress.zipCode,
                        serviceContact: {
                            firstAndLastName:
                            eachLocations[j].serviceContact.firstName + ' ' + eachLocations[j].serviceContact.lastName,
                            email: eachLocations[j].serviceContact.email,
                            phoneNumber: eachLocations[j].serviceContact.phoneNumber,
                            phoneNumberExtn: eachLocations[j].serviceContact.phoneNumberExtn
                        },
                        serviceAddress: {
                            locationName: eachLocations[j].serviceAddress.locationName,
                            addressLine: eachLocations[j].serviceAddress.addressLine,
                            addressDetails: eachLocations[j].serviceAddress.city + ',' +
                            eachLocations[j].serviceAddress.street + ',' +
                            eachLocations[j].serviceAddress.state + ',' +
                            eachLocations[j].serviceAddress.country + ',' +
                            eachLocations[j].serviceAddress.zipCode
                        },
                        shippingAddress: {
                            locationName: eachLocations[j].shippingAddress.locationName,
                            addressLine: eachLocations[j].shippingAddress.addressLine,
                            addressDetails: eachLocations[j].shippingAddress.city + ',' +
                            eachLocations[j].shippingAddress.street + ',' +
                            eachLocations[j].shippingAddress.state + ',' +
                            eachLocations[j].shippingAddress.country + ',' +
                            eachLocations[j].shippingAddress.zipCode
                        }
                    }
                    // this.logger.log(locations);
                    this.locations.push(locations);
                }
            }
        }

        // Create new cart ID
        let cart = this.cartService.initCart();
        // Update user store with new cart ID
        this.authService.updateUserCartInfoFromCart(cart);
        // update the user state with new cart ID
        this.authService.cartInitWithToken(cart.id, currentStore.user.token);

        this._notificationsService.success('Order #12345678', 'Successfully placed. Order confirmation has been sent to your registered Email ID.');

    }

}
