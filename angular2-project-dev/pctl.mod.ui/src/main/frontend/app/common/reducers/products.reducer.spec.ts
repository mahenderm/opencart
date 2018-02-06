/* tslint:disable */
import {inject,TestBed} from '@angular/core/testing';
import { ActionReducer, Action } from '@ngrx/store';
import { Product } from '../models/product.model';
import { productsReducer } from './products.reducer';
describe ('The Product Reducer', () => {
    beforeEach(() => TestBed.configureTestingModule({
    }));
    it('Should return current state when no valid actions have been made', ()  => {
        const state = {
	"productId": "sdwan1000",
	"name": "SD WAN",
	"descriptionHtml": "Software-defined wide area networking (SD-WAN) is the next evolution of private networking. SD-WAN uses software to automate the ongoing configuration of edge routers and push traffic over a mix of private, wireless, and broadband network access.SD-WAN offers a real network solution for businesses trying to keep up with the impact a highly dynamic application environment has on their network quality of service. Our SD-WAN combines CenturyLink network design and management expertise with leading software controls and the ubiquity of any connection type or any vendor. The result is improved flexibility through greater bandwidth that delivers increased visibility into network usage and vastly simplified control. CenturyLink offers four popular configurations of SD-WAN as described below -- or a custom configuration can be set up via a sales agent.",
	"displayImage": "ghghgh",
	"productType": "Network",
	"createdAt": "2016-09-22T14:25:08-03:00",
	"updatedAt": "2016-09-22T14:25:08-03:00",
	"productVariants": [{
		"variantId": "sdwanbasic",
		"productId": 2,
		"name": "SD-WAN BASIC",
		"descriptionHtml": "Software-defined wide area networking (SD-WAN) is the next evolution of private networking",
		"displayImage": "",
		"sku": "SDWANBASIC",
		"displayPosition": 1,
		"servicePlan": "basic",
		"serviceVendor": "versa",
		"serviceTransport": "customer",
		"securityPkg": "1",
		"standardCpe": "1",
		"haServicePlan": "",
		"haServiceVendor": "",
		"haServiceTransport": "",
		"haSecurityPkg": "",
		"haCpe": "0",
		"tier1Support": "1"
           	}]
          };
        const actual = productsReducer(state,{ type: 'INVALID_ACTION', payload: {} });
        const expected = state;
        expect(actual).toBe(expected);
    });
    it('Should return product payload', ()=>{
      const state ={
	"productId": "sdwan1000",
	"name": "SD WAN",
	"descriptionHtml": "Software-defined wide area networking (SD-WAN) is the next evolution of private networking. SD-WAN uses software to automate the ongoing configuration of edge routers and push traffic over a mix of private, wireless, and broadband network access.SD-WAN offers a real network solution for businesses trying to keep up with the impact a highly dynamic application environment has on their network quality of service. Our SD-WAN combines CenturyLink network design and management expertise with leading software controls and the ubiquity of any connection type or any vendor. The result is improved flexibility through greater bandwidth that delivers increased visibility into network usage and vastly simplified control. CenturyLink offers four popular configurations of SD-WAN as described below -- or a custom configuration can be set up via a sales agent.",
	"displayImage": "",
	"productType": "Network",
	"createdAt": "2016-09-22T14:25:08-03:00",
	"updatedAt": "2016-09-22T14:25:08-03:00",
	"productVariants": [{
		"variantId": "sdwanbasic",
		"productId": 2,
		"name": "SD-WAN BASIC",
		"descriptionHtml": "Software-defined wide area networking (SD-WAN) is the next evolution of private networking",
		"displayImage": "",
		"sku": "SDWANBASIC",
		"displayPosition": 1,
		"servicePlan": "basic",
		"serviceVendor": "versa",
		"serviceTransport": "customer",
		"securityPkg": "1",
		"standardCpe": "1",
		"haServicePlan": "",
		"haServiceVendor": "",
		"haServiceTransport": "",
		"haSecurityPkg": "",
		"haCpe": "0",
		"tier1Support": "1"
	}]
     }
        const payload_to_send = state;
        const actual = productsReducer(state, { type: 'ADD_PRODUCTS', payload: payload_to_send });
        const expected = state;
        expect(actual).toBe(expected);
    });
});




