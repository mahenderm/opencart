export interface TnCList {
    items: TnCInfo[]
}

/**
 * TNC details
 */
export interface TnCInfo {
    _id?: any;                       //  id
    version: string;                // version 
    descriptionHTML?: string;        // TNC descriptionHTML
    pdfURL?: string;                 // pdf file URL
    type: string;                   // TNC type
    agreed?: boolean;               // Status
}
