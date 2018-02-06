import { ActionReducer, Action } from '@ngrx/store';
import { CartState,ShoppingCart } from '../models/cart.model';
import {inject,TestBed} from '@angular/core/testing';
import { cartReducer } from './cart.reducer';

describe ('The Cart Reducer', () => {
let currentState = {
                "id":"string",
                "state":CartState.Locations,
                "lineItems":[{
                        "id": "string",
                        "productName": "string",
                        "productId": "string",
                        "productTemplateName":"string",
                        "productTemplateId":"string",
						"unitPrice": 0,
						"term": "string",
                        "locations":[{
                            "id":"string",
                            "serviceContact":{
                                    "id":"string",
                                    "email":"string",
                                    "firstName":"string",
                                    "lastName":"string",
									"phoneNumber": "string",
                                    "phoneNumberExtn":"string"
                                    },
                            "serviceAddress":{
                                    "id":"string",
                                    "locationName":"string",
                                    "addressLine":"string",
                                    "street":"string",
                                    "city":"string",
                                    "country":"string",
                                    "state":"string",
                                    "zipCode":"string",
                                    "checkAddress":"string"
                                    },
                            "shippingAddress":{
                                    "id":"string",
                                    "locationName":"string",
                                    "addressLine":"string",
                                    "street":"string",
                                    "city":"string",
                                    "country":"string",
                                    "state":"string",
                                    "zipCode":"string",
                                    "checkAddress":"string"
                                    }
                        }]
                    }]
                };
    it('Should have return current state when no valid actions have been made', ()  => {
            const state = currentState;
            const actual = cartReducer(state,{ type: 'INVALID_ACTION', payload: {} });
            const expected = state;
            expect(actual).toBe(expected);
        });
     it('Should have return current state when valid actions have been made - CREATE_CART', ()  => {
         const state = currentState;
       	const payload_to_send = state;
        const actual = cartReducer(state, { type: 'CREATE_CART', payload: payload_to_send });
        const expected = state;
		expect(actual).toEqual(expected);
    }); 
     it('Should have return current state when valid actions have been made - ADD_ITEM', ()  => {
         const state = currentState.lineItems;
       	const payload_to_send = state;
        const actual = cartReducer(currentState, { type: 'ADD_ITEM', payload: payload_to_send });
        const expected = state;
		expect([actual.lineItems[0]]).toEqual(expected);
    });  
     it('Should have return current state when valid actions have been made - UPDATE_ITEM', ()  => {
         const state = currentState.lineItems;
       	const payload_to_send = state;
        const actual = cartReducer(currentState, { type: 'UPDATE_ITEM', payload: payload_to_send });
        const expected = state;
		expect([actual.lineItems[0]]).toEqual(expected);
    });  
    it('Should have return current state when valid actions have been made - UPDATE_LOCATION', ()  => {
         const state = currentState.lineItems;
       	const payload_to_send = state;
        const actual = cartReducer(currentState, { type: 'UPDATE_LOCATION', payload: payload_to_send });
        const expected = state;
		expect([actual.lineItems[0]]).toEqual(expected);
    }); 
    it('Should have return current state when valid actions have been made - DELETE_ITEM', ()  => {
         const state = currentState.lineItems;
       	const payload_to_send = state;
        const actual = cartReducer(currentState, { type: 'DELETE_ITEM', payload: payload_to_send });
        const expected = state;
		expect([actual.lineItems[0]]).toEqual(expected);
    });  
     it('Should have return current state when valid actions have been made - DELETE_LOCATION', ()  => {
         const state = currentState.lineItems;
       	const payload_to_send = state;
        const actual = cartReducer(currentState, { type: 'DELETE_LOCATION', payload: payload_to_send });
        const expected = state;
		expect([actual.lineItems[0]]).toEqual(expected);
    });  
});