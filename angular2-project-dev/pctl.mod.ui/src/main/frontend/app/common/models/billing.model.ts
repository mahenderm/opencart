/**
 * Billing data model
 */
export interface Billing {
    companyName: string;       // company name
    //address: BillingAddress;
    hqaddress: BillingHQAddress;
    billingContactDetails: BillingContactDetails;      
}

/**
 * Company Headquarters Address information
 * part of the Billing data model
 */
export interface BillingHQAddress{
    addressLine: string;    // Company Address Line 1
    street?: string;        // Address Line 2
    city: string;           // City
    state: string;          // State
    country: string;        // Country
    zipCode: string;        // Postal Code
}

/**
 * Company Contact details
 * part of the Billing data model
 */
export interface BillingContactDetails {
    firstName: string;      // firstName
    lastName: string;       // lastName
    phoneNumber: string;    // phone number
    phoneNumberExtn?: string;
    email: string;          // email
    jobTitle?: string;      // job title
}

/**
 * Billing API Response Interface
 * Respose from API while address validation
 */
export interface BillingApiResponse {
   content?: Billing,
   message: string,
   fieldStatus?: Array<String>,
   code: string
}
