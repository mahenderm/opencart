/* tslint:disable */
import { Injectable, Inject, OnInit } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { Observer } from 'rxjs/Observer';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { AppStore } from '../models/appstore.model';
import { User, EnterpriseInfo, CartInfo } from '../models/user.model';
import { CartState, ShoppingCart } from '../models/cart.model';
import { CartService } from './cart.service';
import { AppStateService } from './app-state.service';
import { Logger } from '../logging/default-log.service';


// Fingerprintjs2
import * as Fingerprint from 'fingerprintjs2';

const HEADER = { headers: new Headers({ 'Content-Type': 'application/json' }) };

/**
 * Authentication Service for
 *     1) Storing the current auth token (if loggedIn)
 *     2) Handle login and logout methods
*/
@Injectable()
export class AuthService implements OnInit {
    user: Observable<User>;
    shoppingCartId: string;
    userToken: string;
    currentStore: AppStore;

    constructor(
        private http: Http,
        private router: Router,
        public store: Store<AppStore>,
        public cartService: CartService,
        public appStateService: AppStateService,
        private logger: Logger
    ) {
        this.user = <Observable<User>>store.select('user');
        this.currentStore = this.appStateService.getState();
        if (this.currentStore.user.token != undefined) {
            this.userToken = this.currentStore.user.token;
        }
    }

    ngOnInit() {

    }


    public login(username: String, password?: String) {

        let user: User = {
            id: 1,
            email: username.toString(),
            loggedIn: true
        }

        this.store.dispatch({ type: 'CREATE_USER', payload: user });
        this.userInit(user.email, '');
        let currentStore = this.appStateService.getState();
        let check = currentStore.user.forceCheck;
        if (check) {
            // Create oppurtunity for existing customer
            this.router.navigate(['/thanks']);
            return;
        }
        this.router.navigate(['/home']);
    }

    public logout() {

        Observable.fromPromise(this.router.navigate(['/home'])).subscribe(
            result => {
                this.store.dispatch({ type: 'DELETE_USER', payload: {} });
                // Create new cart
                let cart = this.cartService.initCart();
                this.updateUserCartInfo(<CartInfo>{
                    cartState: CartState.LandingPage,
                    shoppingCartId: cart.id
                });
                // clear tokens
                this.updateUserToken('');
                this.updateJWTToken('');
            }
        )
    }

    public clearTokens() {
        this.updateUserToken('');
        this.updateJWTToken('');
    }

    public init() {
         let user: User = {
            id: 1,
            forceCheck: false
        }

        this.store.dispatch({ type: 'UPDATE_USER', payload: user });
    }

    /**
     * Add user EnterpriseInfo to user store
     */
    public addUserInfo(entInfo: EnterpriseInfo) {
        this.store.dispatch({ type: 'UPDATE_ENT_DETAILS', payload: entInfo });
    }

    /**
     * Update user cart Information in the user store
     */
    public updateUserCartInfo(cartInfo: CartInfo) {
        // this.logger.log(cartInfo);
        this.store.dispatch({
            type: 'UPDATE_CART_DETAILS', payload: {
                cartInfo: cartInfo
            }
        });
    }

    /**
     * Update User state with the User API token
     */
    public updateUserToken(tokenValue: string) {
        this.store.dispatch({ type: 'ADD_USER_TOKEN', payload: { token: tokenValue } });
    }

    /**
     * Update User state with the JWT API token
     */
    public updateJWTToken(tokenValue: string) {
        this.store.dispatch({ type: 'ADD_JWT_TOKEN', payload: { jwtToken: tokenValue } });
    }

    /**
     * Update user cart information in the user store
     */
    public updateUserCartInfoFromCart(shoppingCart: ShoppingCart) {
        this.updateUserCartInfo(<CartInfo>{
            cartState: CartState.LandingPage,
            shoppingCartId: shoppingCart.id
        });
    }

    /**
     * Unused
     * Unused
     */
    public check() {
        //     return this.user.isLoggedIn;
    }

    /**
     * Unused
     */
    public getUserDetails() {
        let jwtToken = this.getJWTToken();
        return this.http.get(`${BASE_URL_USERS_INFO}`, this.setRequestOptions('', jwtToken))
            .map(res => res.json())
            .subscribe(user => this.logger.log(user),
            error => this.logger.log(error));
    }

    /**
     * User Init by login Id or finger Printer Id
     */
    public userInit(email: string, fingerPrint: string) {
        if ('development' === ENV && (email !== undefined && email.trim() !== '')) {
            this.http.get(`${BASE_URL_USERS_INFO}`).
                map(res => res.json()).
                subscribe(payload => this.store.dispatch({ type: 'ADD_PROFILE_INFO', payload: payload }));
        }
        if ('production' !== ENV)
            return;
        let userInitObject: any = {
            login: email,
            fingerPrint: fingerPrint
        }
        let jwtToken = this.getJWTToken();
        this.http.post(`${BASE_URL_USERS_INIT}`, JSON.stringify(userInitObject), this.setRequestOptions('', jwtToken))
            .map((res: any) => res.json())
            .catch((error: any) => {
                return Observable.throw(error._body);
            })
            .subscribe(
            data => {
                let userData = data.content;
                if (userData !== 'undefined' ||
                    userData !== undefined) {
                    /* Initializing the user object 
                     in the UI microservice
                     This will be passed as header info for all related microservices
                     requiring user information
                     */
                    this.sessionUnit(data.content);
                    this.updateUserToken(data.content.token);
                    this.userToken = data.content.token;

                    // The user service does not have cart initialized
                    // Add cart ID to the user service
                    if (userData.cartInfo === undefined) {

                        if (this.currentStore.cart.id === undefined) {  //  New code in If case.. Old code in else case
                            let cart = this.cartService.initCart();
                            this.updateUserCartInfoFromCart(cart);
                            this.cartInitWithToken(cart.id, this.userToken);
                        }
                        else {
                            this.cartInitWithToken(this.currentStore.cart.id, this.userToken);
                            this.updateUserCartInfo(<CartInfo>{
                                cartState: CartState.LandingPage,
                                shoppingCartId: this.currentStore.cart.id
                            });
                        }


                    } else {
                        // if logged in user (assuming email is valid)
                        // then replace the user cart id with the current annonymous cart id
                        if (email !== undefined && email.trim() !== '') {
                            this.cartInitWithToken(this.currentStore.cart.id, this.userToken);
                        } else {
                            // annonymous user logged in
                            // so update the cart id with the previously saved cart id in the store
                            this.updateUserCartInfo(<CartInfo>{
                                cartState: CartState.LandingPage,
                                shoppingCartId: userData.cartInfo.shoppingCartId
                            });
                            // persist this in the user api as well
                            this.cartService.initCartWithID(userData.cartInfo.shoppingCartId);

                        }
                        // Update user state with the cart detailed returned
                        // from the user service
                        this.cartService.loadCartByID(userData.cartInfo.shoppingCartId, this.setRequestOptions('', jwtToken));
                    }

                    this.populateUserProfile(userData);

                }

            },
            err => {
                // this.error = JSON.parse(err.message);
                this.logger.error('Error during user initialization :' + err);
            }
            );
    }

    /**
 * utility function for adding the string value and prefix
 * if value is present
 */
    private getStringValue(value: string) {
        if (value === undefined || value.trim().length === 0)
            return '';
        else
            return value;
    }

    /**
     * get user profile details from user init API call and
     * update the user store
     */
    private populateUserProfile(userData: any) {
        let firstName = this.getStringValue(userData.firstName);
        let lastName = this.getStringValue(userData.lastName);
        let phoneNumber = this.getStringValue(userData.phoneNumber);
        let companyName = this.getStringValue(userData.companyName);
        let email = this.getStringValue(userData.email);
        let einNumber = this.getStringValue(userData.einNumber);

        let payload = {
            payload: {
                firstName: firstName,
                lastName: lastName,
                phoneNumber: phoneNumber,
                companyName: companyName,
                email: email,
                einNumber: einNumber
            }
        }
        this.store.dispatch({ type: 'ADD_PROFILE_INFO', payload: payload });
    }

    //  Get User info by passing authToken in Request Header
    public userInfo() {
        if ('production' !== ENV)
            return;
        //let currentStore = this.appStateService.getState();
        let jwtToken = this.getJWTToken();
        this.http.get(`${BASE_URL_USERS_INFO}`, this.setRequestOptions(this.userToken, jwtToken))
            .map((res: any) => res.json())
            .catch((error: any) => {
                return Observable.throw(error._body);
            })
            .subscribe(
            data => {
                //  this.user = data.content;
                this.logger.log('user Info result :');
                this.logger.log(data);

            },
            err => {
                // this.error = JSON.parse(err.message);
                this.logger.log(err);
            }
            );

    }

    //  Initiate Cart for the USER by passing authToken in Request Header
    // TODO: Combine cartInit and cartInitWithToken - ideally the token should be fetched from the
    // appState instead of keeping it as a member variable.
    public cartInitWithToken(cartId: string, userToken: string) {
        if ('production' !== ENV)
            return;
        let cartInitObject: any = { cartId: cartId }
        let jwtToken = this.getJWTToken();
        this.http.post(`${BASE_URL_USERS_CART}`, JSON.stringify(cartInitObject), this.setRequestOptions(userToken, jwtToken))
            .map((res: any) => res.json())
            .catch((error: any) => {
                return Observable.throw(error._body);
            })
            .subscribe(
            data => {
                //this.shoppingCartId = data.content.shoppingCartId;
                this.logger.log('cart Init result :');
                this.logger.log(data);
            },
            err => {
                // this.error = JSON.parse(err.message);
                this.logger.log(err);
            }
            );

    }

    /**
     * Get JWT Token
     * Check if JWT exists and is valid return the token
     * If not fetch new JWT token and return an observable for the same
     */
    public getJWTToken(force?: boolean): any {
        let jwtTok = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcGljbGllbnRAY3RsLmNvbSIsImlzcyI6IkN0bCIsImlhdCI6MTQ3OTgxNTA5MSwiZXhwIjoxNzkwODU1MDkxfQ.kc-P35bWdS_7qWslOXs7mtcW3CcSZ8fU2jTDtc6uRUiBMhWr1U_3cG3Yoew9nYjgGkvcaRHMl_QHDiEarIrHdA';

        if (force !== undefined && force) {
            this.updateJWTToken(jwtTok);
            return jwtTok;
        }

        if (this.currentStore.user.jwtToken !== undefined &&
            this.currentStore.user.jwtToken !== '') {
            return this.currentStore.user.jwtToken;
        } else {
            this.updateJWTToken(jwtTok);
            return jwtTok;
        }
    }

    public setRequestOptions(authToken: string, jwtTok: string) {
        // create authorization header with jwt token
        // let jwtTok = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYmVnaW5zYW11ZWxAZ21haWwuY29tIiwic2NvcGVzIjpbIlJPTEVfVVNFUiIsIlJPTEVfVVNFUiJdLCJpc3MiOiJDdGwiLCJpYXQiOjE0NzY4NzYzMTQsImV4cCI6MTQ3OTQ2ODMxNH0.KyWVkQFLvsQr9vlKJUZDy-hEt7EtO5RM1tNu_Scd25poEGhSgUnXq1Dq0IiYh8Suv5NH8EY8L44wX3uNI3YMNw';
        if (jwtTok) {
            let headers = new Headers({ 'X-Authorization': 'Bearer ' + jwtTok, 'Content-Type': 'application/json' });
            if (authToken && authToken !== "") {
                headers = new Headers({
                    'X-Authorization': 'Bearer ' + jwtTok, 'Content-Type': 'application/json',
                    'authToken': authToken
                });
            }
            return new RequestOptions({ headers: headers });

        }

    }

    public getFingerPrint() {
        let fingerprint = new Fingerprint();
        let self = this;
        fingerprint.get(function (result) {
            self.store.dispatch({ type: 'ADD_FINGERPRINT', payload: { fingerPrint: result } });
            self.userInit('', result);
        });
    }

    /**
     * TODO
     * Checks the user store state to see if
     * annoymous or logged in user
     * if logged In then old customer
     * else new customer
     */
    public isNewCustomer() {
        this.currentStore = this.appStateService.getState();
        if (this.currentStore.user.email !== undefined &&
            this.currentStore.user.email !== '') {
            return false;
        } else {
            return true;
        }

    }


    /**
         * Init Session using login Id or finger Printer Id
        */
        public sessionUnit(userInitObject: any) {


            if ('production' !== ENV)
                return;

            let jwtToken = this.getJWTToken();
            this.http.post(`${BASE_URL_UI_INIT_SESSION}`, JSON.stringify(userInitObject), this.setRequestOptions('', jwtToken))
                .map((res: any) => res.json())
                .catch((error: any) => {
                    return Observable.throw(error._body);
                })
                .subscribe(
                data => {

                },
                err => {
                    this.logger.error('Error during Session initialization :' + err);
                }
                );
        }


}
