/**
 * ContactUs data model
 */
export interface ContactUs {
    firstName: string;      // firstName
    lastName: string;       // lastName
    primaryPhone: string;   // phone number
    primaryPhoneExtn: string;
    email: string;          // email
    companyName?: string;   // enterprise name
    jobTitle?: string;      // job title
    comments?: string;      // enquiry comments
}
