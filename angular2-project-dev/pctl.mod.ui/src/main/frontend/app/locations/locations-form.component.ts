import {
    Component,
    Input,
    EventEmitter,
    OnInit,
    OnDestroy,
    Output,
    AfterViewInit
} from '@angular/core';
import {
    EnterpriseAddress,
    ContactInfo,
    SDWANLocationInfo,
    ShoppingCart,
    ServiceAddressValidation,
    ShippingAddressValidation
} from '../common/models/cart.model';
import { Observable } from 'rxjs/Observable';
import { Subscription } from 'rxjs/Subscription';
import { Store, Action } from '@ngrx/store';
import { AppStore } from '../common/models/appstore.model';
import { ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { FingerPrintService } from '../common/service/fingerprint.service';
import { Validations } from '../common/validations/validations';
import { CountryStateService } from '../common/service/country-state.service';
import { LocationsService } from './locations.service';
import { Country } from '../common/models/country.model';
import { State } from '../common/models/state.model';
import { CityService } from '../common/service/city.service';
import { CITIES } from '../common/db-static/cities';
import { TextMaskService } from '../common/service/text-mask.service';

@Component({
    selector: 'locations-form',
    styleUrls: ['./locations-form.component.css'],
    templateUrl: './locations-form.component.html'
})

export class LocationsFormComponent implements OnInit, OnDestroy {
    public myForm: FormGroup;
    locationInfo: SDWANLocationInfo;
    serviceResponse: ServiceAddressValidation;
    shippingResponse: ShippingAddressValidation;
    serviceValid: boolean;
    shippingValid: boolean;
    serviceValidationSubscription: Subscription;
    shippingValidationSubscription: Subscription;
    @Input() data: SDWANLocationInfo;
    @Output() locationUpdateEvent = new EventEmitter();
    @Output() locationAddEvent = new EventEmitter();
    @Output() formDirtyEvent = new EventEmitter();
    submitted: boolean;
    countries: Country[];
    states: State[];
    selectedDefault = 0;
    phoneMask: any;
    phoneExtnMask: any;

    getCities: Array<any>;
    htmlTooltip = 'service address required';

    @Input() set formData(formData: SDWANLocationInfo) {
        // console.log(formData);
        this.data = formData;
        if (formData !== undefined) {
            // console.log(formData);
            // document.getElementById('email').focus();
            this.populateForm();
            // Fix for Update button enabling
            if (this.data.id) {
                this.serviceValid = true;
                this.shippingValid = true;
            }
        }
    }

    constructor(private fb: FormBuilder,
        private countryStateService: CountryStateService,
        public store: Store<AppStore>,
        public locationService: LocationsService,
        private cityService: CityService,
        private textMask: TextMaskService) {
        this.states = this.countryStateService.getStates();
        this.countries = this.countryStateService.getCountries();
        this.getCities = this.cityService.fetchCities();
        this.phoneMask = this.textMask.getPhoneNumberMaskFormat();
        this.phoneExtnMask = this.textMask.getPhoneNumberExtnMaskFormat();
    }

    // we have to keep country: 'USA', it's because of when reset country should be keep remain
    resetDataModel() {
        this.data = {
            id: '',
            serviceContact: {
                //   id: '',
                email: '',
                firstName: '',
                lastName: '',
                phoneNumber: '',
                phoneNumberExtn: ''
            },
            serviceAddress: {
                //     id: '',
                locationName: '',
                addressLine: '',
                street: '',
                city: '',
                country: this.countries[0].countryCode,
                state: '',
                zipCode: ''
            },
            shippingAddress: {
                //     id: '',
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

    /*
     * Populating the form when clicking the edit button
     */
    populateForm() {
        (<FormGroup>this.myForm)
            .setValue(this.data, {
                onlySelf: true
            });
    }

    /**
     * OnLoading the page injecting the form's each field validations
     */
    ngOnInit() {
        // OnLoad of Location page should come to top
        document.body.scrollTop = 0;
        this.submitted = false;
        // prepopulate form
        this.resetDataModel();
        this.myForm = this.fb.group({
            id: [''],
            serviceContact: this.fb.group({
                //       id: [''],
                email: ['', [<any>Validators.required, <any>Validations.emailValidator]],
                firstName: ['', [<any>Validators.required, <any>Validators.minLength(3), <
                    any>Validations.nameValidator
                ]],
                lastName: ['', [<any>Validators.required, <any>Validations.nameValidator]],
                phoneNumber: ['', [<any>Validators.required, <any>Validations.phoneValidator]],
                phoneNumberExtn: ['']
            }),
            serviceAddress: this.fb.group({
                //      id: [''],
                locationName: ['', [<any>Validators.required, <any>Validators.minLength(3)]],
                addressLine: ['', <any>Validators.required],
                street: [''],
                city: ['', [<any>Validators.required, <any>Validations.nameValidator]],
                country: ['', <any>Validators.required],
                state: ['', <any>Validators.required],
                zipCode: ['', <any>Validations.zipCodeValidator],
            }),
            shippingAddress: this.fb.group({
                //     id: [''],
                checkAddress: [''],
                locationName: ['', [<any>Validators.required, <any>Validators.minLength(3)]],
                addressLine: ['', <any>Validators.required],
                street: [''],
                city: ['', [<any>Validators.required, <any>Validations.nameValidator]],
                country: ['', <any>Validators.required],
                state: ['', <any>Validators.required],
                zipCode: ['', <any>Validations.zipCodeValidator],
            })
        });
        (<FormGroup>this.myForm)
            .setValue(this.data, {
                onlySelf: true
            });
        this.populateForm();
        this.myForm.valueChanges
            .subscribe(values => this.emitDirtyEvent());

        this.myForm.controls['serviceAddress'].valueChanges
            .debounceTime(900)
            .subscribe(values => this.serverCallForValidation());
        this.myForm.controls['shippingAddress'].valueChanges
            .debounceTime(900)
            .subscribe(values => this.serverCallForValidation());
    }

    emitDirtyEvent() {
        if (this.myForm.dirty) {
            this.formDirtyEvent.emit(true);
        } else {
            this.formDirtyEvent.emit(false);
        }
    }
    /**
     * This is a function with parameter that is optional.
     * @param newVal is the value of the text of service address city input.
     * This function will be called when we type on service address city input and select any city by autocomplete.
     */
    updateCityForService(newVal) {
        if (newVal === undefined)
            return;
        this.myForm.patchValue({
            serviceAddress: {
                city: newVal
            }
        });
    }

    /**
     * This is a function with parameter that is optional.
     * @param newVal is the value of the text of shipping address city input.
     * This function will be called when we type on shipping address city input and select any city by autocomplete.
     */
    updateCityForShipping(newVal) {
        if (newVal === undefined)
            return;
        this.myForm.patchValue({
            shippingAddress: {
                city: newVal
            }
        });
    }


    /**
     * This function has no parameter.
     * This function will be called when the user submit new location(by onclick ADD LOCATION button).
     * This function has if condition, if it is true then we need to merge all the service fields
     * to shipping fields. It is required because of if user change service address after the checkbox is checked
     * so we need to merge that changes at the time of submitting the form.
     * 
     * This function calling serverCallForValidation() function to check server validation.
     * If server validation return true then it will call another function submit() function.
     * submit() function has the concept of add location and update location.
     */
    onSubmit() {
        if (this.myForm.value.shippingAddress.checkAddress) {
            this.myForm.value.shippingAddress.locationName =
                this.myForm.value.serviceAddress.locationName;
            this.myForm.value.shippingAddress.addressLine =
                this.myForm.value.serviceAddress.addressLine;
            this.myForm.value.shippingAddress.street = this.myForm.value.serviceAddress.street;
            this.myForm.value.shippingAddress.city = this.myForm.value.serviceAddress.city;
            this.myForm.value.shippingAddress.state = this.myForm.value.serviceAddress.state;
            this.myForm.value.shippingAddress.zipCode = this.myForm.value.serviceAddress.zipCode;

            this.myForm.patchValue({
                shippingAddress: {
                    locationName: this.myForm.value.shippingAddress.locationName,
                    addressLine: this.myForm.value.shippingAddress.addressLine,
                    street: this.myForm.value.shippingAddress.street,
                    city: this.myForm.value.shippingAddress.city,
                    state: this.myForm.value.shippingAddress.state,
                    zipCode: this.myForm.value.shippingAddress.zipCode,
                }
            });
        }

        // this.serverCallForValidation();
        // ==> this concept is implemented correctly because of no real api for validation
        // if (!this.serviceValid && !this.shippingValid) {
        this.submit();
        // }
    }

    /**
     * This function has no parameter.
     * This function used as a callback function inside onSubmit() function.
     * This function will be called only when service and shipping, server side validation true.
     * This function has two functionalities, first is add location and another is update location.
     */
    submit() {
        this.submitted = true;
        this.locationInfo = this.myForm.value;
        let addEvent: boolean = false;
        // commenting the AddressValidation call as it consumes time
        // this.serverCallForValidation();

        if (this.locationInfo.id === '') {
            addEvent = true;
            this.locationInfo = Object.assign({},
                this.locationInfo, {
                    id: FingerPrintService.UUID()
                }
            );
        }

        this.resetDataModel();
        this.populateForm();
        this.myForm.markAsUntouched();

        if (addEvent) {
            this.locationAddEvent.emit(this.locationInfo);
        } else { // Update Event
            this.locationUpdateEvent.emit(this.locationInfo);
        }
        this.formDirtyEvent.emit(false);
    }

    /**
     * This function has no parameter.
     * This function is implemented for check server validation for both service and shipping.
     * This function will be used inside the onSubmit() and used as a callback function.
     */
    serverCallForValidation() {
        this.serviceValid = false;
        this.shippingValid = false;

        if (this.myForm.value.serviceAddress.addressLine && this.myForm.value.serviceAddress.city
            && this.myForm.value.serviceAddress.country && this.myForm.value.serviceAddress.state
            && this.myForm.value.serviceAddress.zipCode.length >= 5) {
            this.serviceValidationSubscription =
                this.addressValidation(this.myForm.value.serviceAddress, 'CIVIC')
                    .map(payload => payload)
                    .catch((error: any) => {
                        return Observable.throw(error._body);
                    })
                    .subscribe(
                    data => {
                        this.serviceValid = true;
                        this.serviceResponse = data;
                    },
                    err => {
                        this.serviceValid = true;
                        this.serviceResponse = JSON.parse(err);
                    });
        }
        // Restricting the ShippingAddressValidation call if selected Same as Service Address
         if (!this.myForm.value.shippingAddress.checkAddress) {
            if (this.myForm.value.shippingAddress.addressLine && this.myForm.value.shippingAddress.city
            && this.myForm.value.shippingAddress.country && this.myForm.value.shippingAddress.state
            && this.myForm.value.shippingAddress.zipCode.length >= 5) {
            this.shippingValidationSubscription =
                this.addressValidation(this.myForm.value.shippingAddress, 'CIVIC')
                    .map(payload => payload)
                    .catch((error: any) => {
                        return Observable.throw(error._body);
                    })
                    .subscribe(
                    data => {
                        this.shippingValid = true;
                        this.shippingResponse = data;
                    },
                    err => {
                        this.shippingValid = true;
                        this.shippingResponse = JSON.parse(err);
                    });
            }
         }
    }

    /**
     * This is a function with a parameter that is mandatory.
     * 
     * @param checked will depend on checkbox value.
     * when checkbox checked, @param checked will have value true.
     * when checkbox unchecked, @param checked will have value false.
     * 
     * This function will merge service address fields to shipping address fields 
     * only when @param checked is true(checked).
     * 
     * When user unchecked checkbox at the time of edit it will clean(empty) 
     * all the fields of shipping.
     * */
    onClickCheckAddressMerge(checked) {
        if (checked) {
            this.myForm.value.shippingAddress.locationName =
                this.myForm.value.serviceAddress.locationName;
            this.myForm.value.shippingAddress.addressLine =
                this.myForm.value.serviceAddress.addressLine;
            this.myForm.value.shippingAddress.street = this.myForm.value.serviceAddress.street;
            this.myForm.value.shippingAddress.city = this.myForm.value.serviceAddress.city;
            this.myForm.value.shippingAddress.state = this.myForm.value.serviceAddress.state;
            this.myForm.value.shippingAddress.zipCode = this.myForm.value.serviceAddress.zipCode;

            this.myForm.patchValue({
                shippingAddress: {
                    locationName: this.myForm.value.shippingAddress.locationName,
                    addressLine: this.myForm.value.shippingAddress.addressLine,
                    street: this.myForm.value.shippingAddress.street,
                    city: this.myForm.value.shippingAddress.city,
                    state: this.myForm.value.shippingAddress.state,
                    zipCode: this.myForm.value.shippingAddress.zipCode,
                }
            });
        } else {
            this.myForm.patchValue({
                shippingAddress: {
                    locationName: '',
                    addressLine: '',
                    street: '',
                    city: '',
                    state: '',
                    zipCode: '',
                }
            });

        }
    }

    /*
     * Calling AddressValidation API
     */
    private addressValidation(address: EnterpriseAddress, call: String) {
        return this.locationService.validLocation(address, call);
    }

    /*
     * Destroying the Subscription on end
     */
    ngOnDestroy() {
        if (this.serviceValidationSubscription !== undefined)
            this.serviceValidationSubscription.unsubscribe();
        if (this.shippingValidationSubscription !== undefined)
            this.shippingValidationSubscription.unsubscribe();
    }
    
}
