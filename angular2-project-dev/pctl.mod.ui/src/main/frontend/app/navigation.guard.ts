import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { AppStateService } from './common/service/app-state.service';
import { LineItem, SDWANLocationInfo, LocationDisplay } from './common/models/cart.model';

@Injectable()
export class NavigationGuard implements CanActivate {
    lineItems: LineItem[];
    constructor(
        private router: Router,
        private appStateService: AppStateService) {
        }

    canActivate() {
        let currentStore = this.appStateService.getState();
        this.lineItems = currentStore.cart.lineItems;
        if (this.lineItems !== undefined) {
            for (var i = 0; i < this.lineItems.length; i++) {
                if (this.lineItems[i].locations === undefined){
                    this.undefinedMethod();
                    break;
                }
                let eachLocations: SDWANLocationInfo[] = this.lineItems[i].locations;
                if (eachLocations.length === undefined){
                    this.undefinedMethod();
                    break;
                }else{
                    if(eachLocations.length < 2){
                        this.undefinedMethod();
                        break;
                    }
                }
            }
        }

        let lineItemCount = currentStore.cart.lineItems.length;

        // console.log( "lineItemCount "+ lineItemCount );

        if (lineItemCount) {
            return true;
        }

        this.router.navigate(['/home']);
        return false;
    }
    undefinedMethod(){
        if(this.router.url === '/' || this.router.url === '/home'){
            this.router.navigate(['/home']);
        }else{
            this.router.navigate(['/locations']);
        }
    }
}
