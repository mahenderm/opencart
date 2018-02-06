
package com.centurylink.pctl.mod.address.domain.address;


import com.centurylink.pctl.mod.address.domain.camel.*;
import com.centurylink.pctl.mod.core.camel.factory.CamelServiceFactory;
import com.centurylink.pctl.mod.core.camel.factory.MessageTypeEnum;
import com.centurylink.pctl.mod.core.camel.service.ICamelService;
import com.centurylink.pctl.mod.core.model.address.Address;
import com.centurylink.pctl.mod.core.utils.Response;
import com.centurylink.pctl.mod.core.utils.StatusCode;
import com.centurylink.pctl.mod.core.utils.ValidateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * This class AddressServiceImpl is a service implementation class for PctlAddressValidationController.
 * <br>
 * This class AddressServiceImpl makes a call to the Soap end point.
 * This class PctlAddressValidationController contains one methods
 * <br> - validate()
 * <br> - callAddressValidationSoapService()
 *
 * @author Haribabu.ka
 */
@Component
public class PctlAddressServiceImpl implements PctlAddressService {

//    private static final Logger LOGGER  = LoggerFactory.getLogger(PctlAddressServiceImpl.class);

    private static final Logger log = LoggerFactory.getLogger(PctlAddressServiceImpl.class);

    @Autowired
    CamelServiceFactory factory;

    /**
     * This validate() method accepts address and addressType as input and returns respective address response from Soap.
     * This validate() method calls callAddressValidationSoapService() method.
     *
     * @param address     {@link com.centurylink.pctl.mod.core.model.address.Address}.
     * @param addressType {@link com.centurylink.pctl.mod.address.domain.address.AddressType}.
     * @return Address, respective address for the given address, addressType available from Soap
     */
    @Override
    public Response validate(Address address, AddressType addressType) {
        ValidateUtils<Address> validator = new ValidateUtils<Address>();

        Response addressResponse = validator.validateBean(address);
        if (addressResponse != null) {
            return addressResponse;
        }

        return callAddressValidationSoapService(address, addressType);
    }

    /**
     * This callAddressValidationSoapService() method accepts address and addressType as input and returns respective address response from Soap.
     *
     * @param address     {@link com.centurylink.pctl.mod.core.model.address.Address}.
     * @param addressType {@link com.centurylink.pctl.mod.address.domain.address.AddressType}.
     * @return Response, respective address for the given address, addressType available from Soap
     */
    public Response callAddressValidationSoapService(Address address, AddressType addressType) {
        Response addressResponse = new Response<>();

        ICamelService service = CamelServiceFactory.getService(MessageTypeEnum.SOAP.name());

        //Actual API camel response with camel integration

       /* if (AddressType.CIVIC.equals(addressType) && "60302".equals(address.getZipCode())) {
            addressResponse.setStatus(StatusCode.C401);

            return addressResponse;
        } else if (AddressType.POSTAL.equals(addressType) && "60302".equals(address.getZipCode())) {
            addressResponse.setStatus(StatusCode.C402);
            return addressResponse;
        }*/

      /*  try {
            log.info("Inside callAddressValidationSoapService");
            log.info("CivicAddressValidation Process Started");
            CivicAddressValidationResponse civicAddressValidationResponse = (CivicAddressValidationResponse) service.process("CivicAddressValidation", address);
            log.info("Response : " + civicAddressValidationResponse);

            CivicAddressValidationResult civicAddressValidationResult= civicAddressValidationResponse.getCivicAddressValidationResult();
            log.info("civicAddressValidationResponse : " + civicAddressValidationResponse.getCivicAddressValidationResult().getCivicAddresses());
            ArrayOfCivicAddress arrayOfCivicAddress = civicAddressValidationResponse.getCivicAddressValidationResult().getCivicAddresses();
            log.info("arrayOfCivicAddress : " + civicAddressValidationResponse.getCivicAddressValidationResult().getCivicAddresses());
            CivicAddress civicAddressResponse = civicAddressValidationResponse.getCivicAddressValidationResult().getCivicAddresses().getCivicAddress().get(0);
            log.info("civicAddressResponse : " + civicAddressValidationResponse.getCivicAddressValidationResult().getCivicAddresses());
*/

        //For testing purpose mock response starts
        CivicAddressValidationResponse civicAddressValidationResponse = new CivicAddressValidationResponse();
        CivicAddressValidationResult civicAddressValidationResult = new CivicAddressValidationResult();

        civicAddressValidationResult.setVersion("2.0.0");
        civicAddressValidationResult.setTrackingId("GeoDevTest:2016-12-01T12:37:53.7315571Z");
        civicAddressValidationResult.setOverallDuration(140.6259);
        civicAddressValidationResult.setResult(ResultColor.GREEN);


        List<CivicAddress> civicAddressResponse = new ArrayList<CivicAddress>();

        CivicAddress civicAddress = new CivicAddress();

        civicAddress.setStreetAddress("93 CENTENNIAL blvd");
        civicAddress.setLocality("HIGHLANDS RANCH");
        civicAddress.setStateOrProvince("CO");
        civicAddress.setPostCode("80126");
        civicAddressResponse.add(civicAddress);

        ArrayOfCivicAddress arrayOfCivicAddress = new ArrayOfCivicAddress();
        arrayOfCivicAddress.setCivicAddress(civicAddressResponse);

        civicAddressValidationResult.setCivicAddresses(arrayOfCivicAddress);


        GeoPoint geoPoint = new GeoPoint();
        geoPoint.setSource(GeoPointSource.TRILLIUM);
        geoPoint.setLatitude(39.561483);
        geoPoint.setLongitude(-104.991963);
        geoPoint.setCoordinateLevel("1");
        geoPoint.setAccuracy("1");
        civicAddressValidationResult.setGeoPoint(geoPoint);

        civicAddressValidationResult.setCensusMatchLevel("0");
        civicAddressValidationResult.setPostalDirectoryMatchLevel("0");
        civicAddressValidationResult.setDeliveryConfirmationCode("Y");
        civicAddressValidationResult.setCountry("DOUGLAS");
        civicAddressValidationResult.setMetropolitanStatisticalArea("DENVER CO");
//For testing purpose mock response ends

        civicAddressValidationResponse.setCivicAddressValidationResult(civicAddressValidationResult);
        addressResponse.setContent(civicAddressValidationResponse);
        //Address fieldStatus=generateFieldStatus(address,civicAddressResponse);
        Address fieldStatus = generateFieldStatus(address, civicAddress);
        addressResponse.setFieldValue(fieldStatus);


       /* if (fieldStatus.getAddressLine().equals("1") && fieldStatus.getCity().equals("1") && fieldStatus.getState().equals("1") && fieldStatus.getZipCode().equals("1")) {
            addressResponse.setStatus(StatusCode.U200);
        } else {
            addressResponse.setStatus(StatusCode.E400);
        }*/
        if (fieldStatus.getAddressLine().equals("1")){
            addressResponse.setStatus(StatusCode.U200);
        } else {
            addressResponse.setStatus(StatusCode.E400);
        }

      /* } catch (CamelException e) {
            e.printStackTrace();
        }*/

        return addressResponse;


    }

    //address req and res field validation
    public Address generateFieldStatus(Address addressRequest, CivicAddress addressResponse) {

        Address fieldStatus = new Address("0", "0", "0", "0");
        log.info("addressRequest : " + addressRequest);
        log.info("addressResponse : " + addressResponse);
        if (addressRequest != null && addressResponse!= null) {
            fieldStatus.setAddressLine(getAddressFieldStatus(addressRequest.getAddressLine(), addressResponse.getStreetAddress()));
            fieldStatus.setCity(getAddressFieldStatus(addressRequest.getCity(), addressResponse.getLocality()));
            fieldStatus.setState(getAddressFieldStatus(addressRequest.getState(), addressResponse.getStateOrProvince()));
            fieldStatus.setZipCode(getAddressFieldStatus(addressRequest.getZipCode(), addressResponse.getPostCode()));
        }
        return fieldStatus;
    }

    public String getAddressFieldStatus(String addressRequest, String addressResponse) {
        if (addressRequest!= null && addressResponse!= null) {
            if (addressRequest.equalsIgnoreCase(addressResponse)) {
                return "1";
            }
        }
        return "0";
    }
}
