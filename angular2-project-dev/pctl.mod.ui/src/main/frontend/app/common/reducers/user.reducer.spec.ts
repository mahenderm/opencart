/* tslint:disable */
import {inject,TestBed} from '@angular/core/testing';
import { ActionReducer, Action } from '@ngrx/store';
import { userReducer } from './user.reducer';
import { User } from '../models/user.model';

import { CartState } from '../models/cart.model';

describe ('The User Reducer', () => {
	let currentState = {
						'id':0,
						'avatar':'test',
						'email':'test',
						'enterpriseInfo':{
							'ein': 0,                    
							'ownTransport': false
						},
						'fingerPrint':'test',
						'cartInfo': {
							'cartState': CartState.LandingPage,
							'shoppingCartId': 'test',
							'orderID': 'any'
						},
						'rememberMe':false,
						'token':'test',
						'loggedIn':false,
						'status':{
							'mode': 'test',
							'sku': 'test',
							'term': 'test',
							'name': 'test',
							'productVariant':{
								"variantId":"test",
								"productId":"test",
								"name":"test",
								"sku" :"test",
								"descriptionHtml":"test",
								"displayImage" :"test",
								"displayPosition": 0,
								"servicePlan" :"test" ,
								"serviceVendor":"test",
								"serviceTransport":"test",
								"securityPkg":"test",
								"standardCpe" :"test",
								"haServicePlan":"test",
								"haServiceVendor":"test",
								"haServiceTransport":"test",
								"haSecurityPkg":"test",
								"haCpe":"test",
								"tier1Support" :"test",
								"currency" : "test",
								"priceList" : [{
								"term":" test",
								"own": 0,
								"ctl": 0
								}]
								},
							'transport': 'test'
						},
						'breadcrumbs':[{
							'isRoute': false,       
							'routerLink': 'test',     
							'name': 'test',           
							'from': 'test'          
						}],
						'billing':{
							'companyName': 'someCompany',
							/*'address': {
										'addressLine': 'test',
										'street': 'test',         
										'city': 'test',           
										'state': 'test',          
										'country': 'test',        
										'zipCode': 'test'        
									},*/ 
							'hqaddress':{
										'addressLine': 'test',
										'street': 'test',         
										'city': 'test',           
										'state': 'test',          
										'country': 'test',        
										'zipCode': 'test'        
									},
							'billingContactDetails': {
										'firstName': 'test',
										'lastName': 'test',
										'phoneNumber': 'test',
										'phoneNumberExtn': 'test',
										'email': 'test',
										'jobTitle': 'test'
									}      
						},
						'jwtToken':'test',
						'previousUrl':'test',
						'forceCheck':false,
						'firstName':'test',
						'lastName':'test',
						'phoneNumber':'test',
						'companyName':'test',
						'einNumber':'test'
					};
	let initialState = {
								'id': 0,
								'email': '',
								'loggedIn': false,
								'firstName': '',
								'lastName': '',
								'phoneNumber': '',
								'companyName': '',
								'einNumber': ''
							};
    beforeEach(() => TestBed.configureTestingModule({
    }));
    it('Should have return current state when no valid actions have been made', ()  => {
        const state = currentState;
        const actual = userReducer(state, { type: 'INVALID_ACTION', payload: {} });
        const expected = state;
        expect(actual).toBe(expected);
    });
   
     it('Should have return current state when Valid actions have been made - CREATE_USER', ()  => {
        const state = currentState;
       	const payload_to_send = state;
        const actual = userReducer(state, { type: 'CREATE_USER', payload: payload_to_send });
        const expected = state;
		expect(actual).toEqual(expected);
    });
	 it('Should have return current state when Valid actions have been made - UPDATE_USER', ()  => {
        const state = currentState;
       	const payload_to_send = state;
        const actual = userReducer(state, { type: 'UPDATE_USER', payload: payload_to_send });
        const expected = state;
		expect(actual).toEqual(expected);
    });
	 it('Should have return current state when Valid actions have been made - DELETE_USER', ()  => {
        const state = initialState;
       	const payload_to_send = state;
        const actual = userReducer(state, { type: 'DELETE_USER', payload: payload_to_send });
        const expected = state;
		expect(actual).toEqual(expected);
    });
	 it('Should have return current state when Valid actions have been made - INIT_USER', ()  => {
        const state = initialState;
       	const payload_to_send = state;
        const actual = userReducer(state, { type: 'INIT_USER', payload: payload_to_send });
        const expected = state;
		expect(actual).toEqual(expected);
    });
	 it('Should have return current state when Valid actions have been made - UPDATE_ENT_DETAILS', ()  => {
        const state = currentState;
       	const payload_to_send = state;
        const actual = userReducer(state, { type: 'UPDATE_ENT_DETAILS', payload: payload_to_send });
        const expected = state;
		expect(actual).toEqual(expected);
    });
	 it('Should have return current state when Valid actions have been made - UPDATE_ACTIVE_SELECTION', ()  => {
        const state = currentState.status;
       	const payload_to_send = state;
        const actual = userReducer(currentState, { type: 'UPDATE_ACTIVE_SELECTION', payload: payload_to_send });
        const expected = state;
		expect(actual.status).toBe(expected);
    });
	 it('Should have return current state when Valid actions have been made - UPDATE_BREADCRUMB', ()  => {
        const state = currentState.breadcrumbs;
       	const payload_to_send = state;
        const actual = userReducer(currentState, { type: 'UPDATE_BREADCRUMB', payload: payload_to_send });
        const expected = state;
		expect(actual.breadcrumbs).toEqual(expected);
    });
	 it('Should have return current state when Valid actions have been made - UPDATE_CART_DETAILS', ()  => {
        const state = currentState;
       	const payload_to_send = state;
        const actual = userReducer(state, { type: 'UPDATE_CART_DETAILS', payload: payload_to_send });
        const expected = state;
		expect(actual).toEqual(expected);
    });
	 it('Should have return current state when Valid actions have been made - ADD_USER_TOKEN', ()  => {
        const state = currentState;
       	const payload_to_send = state;
        const actual = userReducer(state, { type: 'ADD_USER_TOKEN', payload: payload_to_send });
        const expected = state;
		expect(actual).toEqual(expected);
    });
	 it('Should have return current state when Valid actions have been made - ADD_JWT_TOKEN', ()  => {
        const state = currentState;
       	const payload_to_send = state;
        const actual = userReducer(state, { type: 'ADD_JWT_TOKEN', payload: payload_to_send });
        const expected = state;
		expect(actual).toEqual(expected);
    });
	 it('Should have return current state when Valid actions have been made - ADD_FINGERPRINT', ()  => {
        const state = currentState;
       	const payload_to_send = state;
        const actual = userReducer(state, { type: 'ADD_FINGERPRINT', payload: payload_to_send });
        const expected = state;
		expect(actual).toEqual(expected);
    });
	 it('Should have return current state when Valid actions have been made - ADD_BILLING', ()  => {
        const state = currentState.billing;
       	const payload_to_send = state;
        const actual = userReducer(currentState, { type: 'ADD_BILLING', payload: payload_to_send });
        const expected = state;
		expect(actual.billing).toEqual(expected);
    });
	 it('Should have return current state when Valid actions have been made - PREVIOUS_URL', ()  => {
        const state = currentState.previousUrl;
       	const payload_to_send = state;
        const actual = userReducer(currentState, { type: 'PREVIOUS_URL', payload: payload_to_send });
        const expected = state;
		expect(actual.previousUrl).toEqual(expected);
    });
	 it('Should have return current state when Valid actions have been made - FORCE_USER_CHECK', ()  => {
        const state = currentState.forceCheck;
       	const payload_to_send = state;
        const actual = userReducer(currentState, { type: 'FORCE_USER_CHECK', payload: payload_to_send });
        const expected = state;
		expect(actual.forceCheck).toEqual(expected);
    });
	 it('Should have return current state when Valid actions have been made - ADD_PROFILE_INFO', ()  => {
        const state = currentState;
       	const payload_to_send = state;
        const actual = userReducer(state, { type: 'ADD_PROFILE_INFO', payload: payload_to_send });
        const expected = state;
		expect(actual).toEqual(expected);
    });
});




