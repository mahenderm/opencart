import { CartState, ShoppingCart } from './cart.model';
import { ActiveStatus } from './pricing.model';
import { Breadcrumb } from './breadcrumb.model';
import { Billing } from './billing.model';
import { TnCInfo } from './tnc.model';


export interface productInfo {
    productName?: string;
    locationSize?: string;
    price?: string;
}

export interface OrderHistory {
    orderId?: string;
    cartId?: string;
    state?: string;
    orderDate?: string;
    productInfo?: productInfo[];
}
/**
 * User detail data model
 */
export interface User {
    id: number;
    avatar?: string;
    email?: string;
    enterpriseInfo?: EnterpriseInfo;    // enterprise Information
    fingerPrint?: string;
    cartInfo?: CartInfo;
    rememberMe?: boolean;
    token?: string;                     // User service auth token
    loggedIn?: boolean;
    status?: ActiveStatus;
    breadcrumbs?: Breadcrumb[];
    billing?: Billing;
    jwtToken?: string;                  // Microservice API Token
    previousUrl?: string;
    forceCheck?: boolean;
    generalTerm?: TnCInfo; // General Contract Details
    sdWanTerm?: TnCInfo; // SD-WAN Contract Details 


    firstName?: string;
    lastName?: string;
    phoneNumber?: string;
    companyName?: string;
    einNumber?: string;
    orderHistory?: OrderHistory[];
}

/**
 * User Enterprise Infomation
 * part of User model data
 */
export interface EnterpriseInfo {
    ein: number;                        // Enterprise Identification Number
    ownTransport: boolean;
}

/**
 * User cart details
 * part of user cart informations
 */
export interface CartInfo {
    cartState?: CartState;
    shoppingCartId: string;
    orderID?: any;
}


