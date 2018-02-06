package com.centurylink.pctl.mod.core.utils;

import org.springframework.http.HttpStatus;

/**
 * @author haribabu.ka on 14-10-2016.
 */
public enum StatusCode {

    // HTTP COMMON Error codes.
    U200("200", "Success", HttpStatus.OK),
    U202("202", "Invalid User", HttpStatus.OK),
    U203("202", "Invalid Cart Id", HttpStatus.OK),
    U204("202", "Invalid Order Id", HttpStatus.OK),
    U205("204", "No Content", HttpStatus.NO_CONTENT),
    U400("400", "Bad Request", HttpStatus.NOT_FOUND),
    U401("400", "Bad Request - Header is Empty", HttpStatus.NOT_FOUND),
    U402("400", "Bad Request - User Not Found In Database", HttpStatus.NOT_FOUND),
    U403("400", "Bad Request - User Not Logged in", HttpStatus.NOT_FOUND),

    E400("400", "Bad Request", HttpStatus.BAD_REQUEST),

    // Notification Error codes
    N401("400", "Bad Request - Not a valid DeliveryType: Supported formats :  EMAIL and MESSAGE .", HttpStatus.NOT_FOUND),



    C401("400", "Civic Address zipCode Invalid", HttpStatus.BAD_REQUEST),
    C402("400", "Postal Address zipCode Invalid", HttpStatus.BAD_REQUEST),
    C403("400", "Bad Request - Service and Shipping Address Empty", HttpStatus.NOT_FOUND),


    C406("502", "WSDL - Response is Null","Red", HttpStatus.BAD_GATEWAY),
    E401("101-1", "PostalAddressValidationRequest must exist","Red", HttpStatus.NOT_FOUND),
    E402("101-2", "PostalAddressValidationRequest clientId must exist","Red", HttpStatus.NOT_FOUND),
    E403("101-3", "PostalAddressValidationRequest clientId invalid","Red", HttpStatus.NOT_FOUND),
    E404("101-4", "PostalAddressValidationRequest input Address must exist","Red", HttpStatus.NOT_FOUND),
    E405("101-5", "InputAddress address Line1 must exist","Red", HttpStatus.NOT_FOUND),
    E406("101-6", "InputAddress address Line1: Invalid Format or Length","Red", HttpStatus.NOT_FOUND),
    E407("101-7", "InputAddress address Line2: Invalid Format or Length","Red", HttpStatus.NOT_FOUND),
    E408("101-8", "InputAddress locality and InputAddress state Or Province must exist","Red", HttpStatus.NOT_FOUND),
    E409("101-9", "InputAddress locality Invalid Format or Length","Red", HttpStatus.NOT_FOUND),
    E4010("101-10", "InputAddress state Or Province Invalid Format or Length","Red", HttpStatus.NOT_FOUND),
    E4011("101-11", "InputAddress postCode Invalid Format or Length","Red", HttpStatus.NOT_FOUND),
    E4012("101-12", "InputAddress addressLine3 is reserved for future use","Red", HttpStatus.NOT_FOUND),
    E4013("101-13", "InputAddress country is reserved for future use","Red", HttpStatus.NOT_FOUND),
    E4014("101-14", "InputAddress country is reserved for future use","Red", HttpStatus.NOT_FOUND),
    E500("500", "Internal Server Error","Red", HttpStatus.INTERNAL_SERVER_ERROR),

    // Cart Service Codes
    E701("701", "Invalid Cart Id","Red", HttpStatus.BAD_REQUEST),
    E702("702", "Invalid Cart Event","Red", HttpStatus.BAD_REQUEST),
    E703("703", "Error in retrieving cart", HttpStatus.INTERNAL_SERVER_ERROR),
    E704("704", "Invalid Cart Event Type","Red", HttpStatus.BAD_REQUEST),
    E705("705", "Shopping Cart is Empty","Red", HttpStatus.BAD_GATEWAY),

    //Order Service Codes
    E800("800", "Order initialized successfully", HttpStatus.OK),
    E801("801", "Invalid Order Id/Cart is empty","Red", HttpStatus.NOT_FOUND),
    E802("802", "Invalid Order Event","Red", HttpStatus.NOT_FOUND),
    E803("803", "Error in retrieving Order", HttpStatus.INTERNAL_SERVER_ERROR); 

    private HttpStatus httpStatus;
    private String code;
    private String message;
    private String colourCode;

    /**
     * StatusCode()  code,message, color, httpstatus argument constructor
     * @param code
     * @param message
     * @param colourCode
     * @param httpStatus
     */
    StatusCode(String code, String message, String colourCode, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.colourCode = colourCode;
        this.httpStatus = httpStatus;

    }

    /**
     * StatusCode()  code,message, httpstatus argument constructor
     * @param code
     * @param message
     * @param httpStatus
     */
    StatusCode(String code, String message,  HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.colourCode = colourCode;
        this.httpStatus = httpStatus;

    }

    /**
     * getCode()
     * @return code
     */

    public String getCode() {
        return code;
    }

    /**
     * getMessage() method
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * getHttpStatus()
     * @return httpStatus
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /**
     * getColourCode ()
     *
     * @return colourCode
     */
    public String getColourCode() { return colourCode; }
}
