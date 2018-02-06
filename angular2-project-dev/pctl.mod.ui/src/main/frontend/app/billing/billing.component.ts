import { Component, OnDestroy } from '@angular/core';
import { OrderComponent } from '../order/order.component';
import { Observable } from 'rxjs/Observable';
import { Subscription } from 'rxjs/Subscription';
import { Store } from '@ngrx/store';
import { AppStore } from '../common/models/appstore.model';
import { User } from '../common/models/user.model';
import { Breadcrumb } from '../common/models/breadcrumb.model';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Billing, BillingApiResponse } from '../common/models/billing.model';
import { Validations } from '../common/validations/validations';
import { CartService } from '../common/service/cart.service';
import { CountryStateService } from '../common/service/country-state.service';
import { Country } from '../common/models/country.model';
import { State } from '../common/models/state.model';
import { Router } from '@angular/router';
import { OrderService } from '../order/order.service';
import { Logger } from '../common/logging/default-log.service';
import { TextMaskService } from '../common/service/text-mask.service';
import { FormComponent } from '../common/service/unsaved.guard';
import { AppStateService } from '../common/service/app-state.service';

/**
 * Billing component
 * This drives the entire billing page
 */
@Component({
    selector: 'billing',
    templateUrl: './billing.component.html',
    styleUrls: ['./billing.component.css']
})
export class BillingComponent implements OnDestroy, FormComponent {
    user: Observable<User>;
    userSubscription: Subscription;
    billingForm: FormGroup;
    cartService: CartService;
    einValue: string;
    einScore: string;
    einFlag: boolean = false;
    countries: Country[];
    states: State[];
    selectedDefault = 0;
    billingResponse: BillingApiResponse;
    billingValid: boolean;
    billingAddValData: any;
    billingFormRestoreData: any;
    orderId: any;
    phoneMask: any;
    phoneExtnMask: any;
    saving: boolean;

    constructor(
        public store: Store<AppStore>,
        private formBuilder: FormBuilder,
        private countryStateService: CountryStateService,
        private router: Router,
        private orderService: OrderService,
        private logger: Logger,
        private textMask: TextMaskService,     
        private appStateService: AppStateService,) {
        this.user = <Observable<User>>store.select('user');         // user store
        // subscribe data from user and restore the form
        this.userSubscription = this.user.subscribe(
            data => {
                this.billingFormRestoreData = data.billing;
            }
        );
        this.states = this.countryStateService.getStates();
        this.countries = this.countryStateService.getCountries();
        this.phoneMask = this.textMask.getPhoneNumberMaskFormat();
        this.phoneExtnMask = this.textMask.getPhoneNumberExtnMaskFormat();
        this.saving = false;
        /**
         * Billing page form validation and
         * restore the form data from store if billing details exists
         * or else part will work
         */
        if (this.billingFormRestoreData !== undefined) {
            this.billingForm = this.formBuilder.group({
                companyName: [this.billingFormRestoreData.companyName,
                [<any>Validators.required,
                <any>Validators.minLength(3)]],
                hqAddress: this.formBuilder.group({
                    addressLine: [this.billingFormRestoreData.hqaddress.addressLine,
                    <any>Validators.required],
                    street: [this.billingFormRestoreData.hqaddress.street],
                    city: [this.billingFormRestoreData.hqaddress.city,
                    [<any>Validators.required,
                    <any>Validators.minLength(3)]],
                    state: [this.billingFormRestoreData.hqaddress.state,
                    <any>Validators.required],
                    country: [this.billingFormRestoreData.hqaddress.country],
                    zipCode: [this.billingFormRestoreData.hqaddress.zipCode,
                    <any>Validations.zipCodeValidator]
                }),
                billingContactDetails: this.formBuilder.group({
                    firstName: [this.billingFormRestoreData.billingContactDetails.firstName,
                    [<any>Validators.required,
                    <any>Validators.minLength(3),
                    <any>Validations.nameValidator]],
                    lastName: [this.billingFormRestoreData.billingContactDetails.lastName,
                    [<any>Validators.required,
                    <any>Validations.nameValidator]],
                    phoneNumber: [this.billingFormRestoreData.billingContactDetails.phoneNumber,
                    [<any>Validators.required,
                    <any>Validations.phoneValidator]],
                    phoneNumberExtn: 
                    [this.billingFormRestoreData.billingContactDetails.phoneNumberExtn],
                    email: [this.billingFormRestoreData.billingContactDetails.email,
                    [<any>Validators.required,
                    <any>Validations.emailValidator]],
                    jobTitle: [this.billingFormRestoreData.billingContactDetails.jobTitle]
                })
            })
        } else {
            this.billingForm = this.formBuilder.group({
                companyName: ['', [<any>Validators.required, <any>Validators.minLength(3)]],
                hqAddress: this.formBuilder.group({
                    addressLine: ['', <any>Validators.required],
                    street: [''],
                    city: ['', [<any>Validators.required, <any>Validators.minLength(3)]],
                    state: ['', <any>Validators.required],
                    country: [''],
                    zipCode: ['', <any>Validations.zipCodeValidator]
                }),
                billingContactDetails: this.formBuilder.group({
                    firstName: ['', [<any>Validators.required,
                    <any>Validators.minLength(3),
                    <any>Validations.nameValidator]],
                    lastName: ['', [<any>Validators.required,
                    <any>Validations.nameValidator]],
                    phoneNumber: ['', [<any>Validators.required,
                    <any>Validations.phoneValidator]],
                    phoneNumberExtn: [''],
                    email: ['', [<any>Validators.required,
                    <any>Validations.emailValidator]],
                    jobTitle: ['']
                })
            })
        }

        /**
         * Billing Address onchange API call
         */
        this.billingForm.controls['hqAddress'].valueChanges
            .debounceTime(900)
            .subscribe(values => this.billingAddressValidation() );
    }

    /**
     * Billing submit with OrderId and form value
     */
    billingSubmit() {
        let billing: Billing;
        this.orderId = Math.floor((1 + Math.random()) * 0x10000); // 'Order123' // static value
        let billingApiValue: Billing = {
            companyName: this.billingForm.value.companyName,
            hqaddress: {
                addressLine: this.billingForm.value.hqAddress.addressLine,
                street: this.billingForm.value.hqAddress.street,
                country: this.countries[0].countryCode,
                state: this.billingForm.value.hqAddress.state,
                city: this.billingForm.value.hqAddress.city,
                zipCode: this.billingForm.value.hqAddress.zipCode
            },
            billingContactDetails: {
                firstName: this.billingForm.value.billingContactDetails.firstName,
                lastName: this.billingForm.value.billingContactDetails.lastName,
                jobTitle: this.billingForm.value.billingContactDetails.jobTitle,
                phoneNumber: this.billingForm.value.billingContactDetails.phoneNumber,
                phoneNumberExtn: this.billingForm.value.billingContactDetails.phoneNumberExtn,
                email: this.billingForm.value.billingContactDetails.email
            }
        };
        /**
         * On submiting update the Breadcrumb array values
         */
        let breadcrumbs: Breadcrumb[];
        breadcrumbs = [{
            isRoute: false,
            name: 'Locations',
            routerLink: '/locations'
        }, {
            isRoute: false,
            name: 'Billing Details',
            routerLink: '/billing'
        }, {
            isRoute: false,
            name: 'Contract',
            routerLink: ''
        }];
        this.store.dispatch({ type: 'UPDATE_BREADCRUMB', payload: breadcrumbs });
        this.store.dispatch({ type: 'ADD_BILLING', payload: billingApiValue });
        this.saving = true;
        let currentStore = this.appStateService.getState();
        if (currentStore.user.cartInfo.shoppingCartId === undefined ||
            currentStore.user.cartInfo.orderID === undefined ||
            currentStore.user.billing === undefined) {
            this.logger.error('Error in order submit : incomplete information');
        }
        let cartID = currentStore.user.cartInfo.shoppingCartId;
        let orderID = currentStore.user.cartInfo.orderID;
        let billingDetails = currentStore.user.billing;
        this.orderService.updateOrder(cartID, orderID, billingDetails).subscribe(
            data => {
                //this.logger.log('api msg '+JSON.stringify(data))
                if (data.message === 'Success' && data.code === '200') {
                    this.router.navigate(['/tnc']);
                } else {
                    this.router.navigate(['/tnc']);
                }
            },
            error => {
                this.logger.error('Error in order submit : incomplete information' + error)
            }
        )
    }

    ngOnInit() {
        document.body.scrollTop = 0;
    }
    /**
     * Billing Page Address validation API call
     */
    billingAddressValidation() {
        this.billingValid = false;
        this.billingAddValData = {
            addressLine: this.billingForm.value.hqAddress.addressLine,
            street: this.billingForm.value.hqAddress.street,
            country: this.countries[0].countryCode,
            state: this.billingForm.value.hqAddress.state,
            city: this.billingForm.value.hqAddress.city,
            zipCode: this.billingForm.value.hqAddress.zipCode
        };
        let billingApiValue: Billing = {
            companyName: this.billingForm.value.companyName,
            hqaddress: {
                addressLine: this.billingForm.value.hqAddress.addressLine,
                street: this.billingForm.value.hqAddress.street,
                country: this.countries[0].countryCode,
                state: this.billingForm.value.hqAddress.state,
                city: this.billingForm.value.hqAddress.city,
                zipCode: this.billingForm.value.hqAddress.zipCode
            },
            billingContactDetails: {
                firstName: this.billingForm.value.billingContactDetails.firstName,
                lastName: this.billingForm.value.billingContactDetails.lastName,
                jobTitle: this.billingForm.value.billingContactDetails.jobTitle,
                phoneNumber: this.billingForm.value.billingContactDetails.phoneNumber,
                phoneNumberExtn: this.billingForm.value.billingContactDetails.phoneNumberExtn,
                email: this.billingForm.value.billingContactDetails.email
            }
        };

        if (this.billingForm.value.hqAddress.addressLine
            && this.billingForm.value.hqAddress.state
            && this.billingForm.value.hqAddress.city
            && this.billingForm.value.hqAddress.zipCode.length >= 5) {
            this.store.dispatch({ type: 'ADD_BILLING', payload: billingApiValue });
            this.validationCall(this.billingAddValData, 'POSTAL')
                .map(payload => payload)
                .catch((error: any) => {
                    return Observable.throw(error._body);
                })
                .subscribe(
                data => {
                    this.billingValid = true;
                    this.billingResponse = data;
                },
                err => {
                    this.billingValid = true;
                    this.billingResponse = JSON.parse(err);
                    this.logger.error(JSON.parse(err));
                });
        }
    }

    private validationCall(hqaddress: Billing, call: String) {
        return this.orderService.validLocation(hqaddress, call);
    }


    /**
    * Clean up user subscription
    */
    ngOnDestroy() {
        if (this.userSubscription !== undefined)
            this.userSubscription.unsubscribe();
    }

    hasUnsavedChanges(): Boolean {
        return this.billingForm.dirty && !this.saving;
    }
}
