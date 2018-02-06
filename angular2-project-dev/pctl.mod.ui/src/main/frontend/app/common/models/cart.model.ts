/**
 * Current cart state enum
 */
export enum CartState {
    'LandingPage',
    'Locations',
    'Terms And Conditions'
}

/**
 * Shopping Cart interface
 */
export interface ShoppingCart {
    id?: string;            // cart id
    state?: CartState;      // cart state
    lineItems?: LineItem[]; // cart line items
}

/**
 * cart line item details
 */
export interface LineItem {
    id?: string;                        // line item id
    productName: string;                // product name 
    productId: string;                  // product id
    productTemplateName: string;        // product template name (variant like SDWAN Basic)
    productTemplateId: string;          // product template/sku id (variant id)
    unitPrice?: number;                 // product variant unit price
    term?: string;                      // product term
    locations?: SDWANLocationInfo[];    // product locations
}

/**
 * SDWAN location information that is added to the cart
 * contains the product template/sku id and the location details
 */
export interface LocationInfo {
    productTemplateId: string;          // product template/sku id (variant id) SDWAN BASIC,SDWAN BASIC HA,SDWAN Premium,SDWAN Premium HA
    location: SDWANLocationInfo;
}

/**
 * Service contact information
 * part of the SDWANLocationInfo data model
 */
export interface ContactInfo {
    id?: string;
    email: string;          // email
    firstName: string;      // first name
    lastName: string;       // last name
    phoneNumber: string;    // phone
    phoneNumberExtn: string;    // phoneNumberExtn 
}

/**
 * interface for displaying contact information 
 * on the locations page in the list format
 * sub part of LocationDisplay
 * (used only for display purposes)
 */
export interface DisplayContactInfo {
    firstAndLastName: string;
    email: string;
    phoneNumber: string;
    phoneNumberExtn: string;
}

/**
 * Captures the enterprise address for the location
 * Both Service and Shipping Address are captured in the same format
 */
export interface EnterpriseAddress {
    id?: string;
    locationName: string;           // unique location name
    addressLine: string;            // address line 1
    street: string;                 // address line 2
    city: string;                   // city
    country: string;    
    state: string;
    zipCode: string;
    checkAddress?: string;      // Used to mark if shipping address is same as service address
}

/**
 * Interface for displaying address information 
 * on the locations page in the list format
 * sub part of [[LocationDisplay.name]]
 * (used only for display purposes)
 */
export interface DisplayEnterpriseAddress {
    locationName: string;
    addressLine: string;
    addressDetails: string;
}

/**
 * SDWAN location details 
 * consists of service contact , service and shipping address details
 */
export interface SDWANLocationInfo {
    id?: string;
    serviceContact: ContactInfo;
    serviceAddress: EnterpriseAddress;
    shippingAddress: EnterpriseAddress;
}

/**
 * Interface for displaying sdwan location information 
 * on the locations page in the list format
 * (used only for display purposes)
 */
export interface LocationDisplay {
    id?: string;
    name: string;                               // location name
    contact: string;                            // single line contact information (short)
    address: string;                            // single line service address information (short)
    serviceContact: DisplayContactInfo;         // three line contact information
    serviceAddress: DisplayEnterpriseAddress;   // three line service address information
    shippingAddress: DisplayEnterpriseAddress;  // three line shipping address information
}

/**
 * TODO - relook at common address validation
 */
export interface ServiceAddressValidation {
   content?: EnterpriseAddress,
   message: string,
   fieldStatus?: Array<String>,
   code: string
}

export interface ShippingAddressValidation {
   content?: EnterpriseAddress,
   message: string,
   fieldStatus?: Array<String>,
   code: string
}
