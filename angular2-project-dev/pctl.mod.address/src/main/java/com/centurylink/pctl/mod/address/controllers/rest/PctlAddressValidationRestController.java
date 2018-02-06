
package com.centurylink.pctl.mod.address.controllers.rest;

import com.centurylink.pctl.mod.address.domain.address.AddressType;
import com.centurylink.pctl.mod.address.domain.address.PctlAddressService;
import com.centurylink.pctl.mod.core.model.address.Address;
import com.centurylink.pctl.mod.core.utils.Response;
import com.centurylink.pctl.mod.core.utils.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This class PctlAddressValidationController is a RestController using springMVC
 * <br>
 * This class PctlAddressValidationController contains one methods
 * <br> - validateAddress()
 *
 * @author Haribabu.ka
 */

@RequestMapping("/address")
@Controller
public class PctlAddressValidationRestController {

    // private static final Logger log = LoggerFactory.getLogger(PctlAddressValidationController.class);

    /**
     * {@link com.centurylink.pctl.mod.address.domain.address.PctlAddressService}.
     */
    @Autowired
    public PctlAddressService pctlAddressService;

    private static final Logger log = LoggerFactory
        .getLogger(com.centurylink.pctl.mod.address.controllers.rest.PctlAddressValidationRestController.class);

    /**
     * Post method which does the Address validation calling the Soap end point.
     *
     * @param address {@link  com.centurylink.pctl.mod.core.model.address.Address}
     * @param type    {@link com.centurylink.pctl.mod.address.domain.address.AddressType}.
     * @return Address.
     */
    @RequestMapping(method = RequestMethod.POST, value = "/validation/{type}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Response> validateAddress(@RequestBody Address address, @PathVariable String type) {
        Response response = new Response();
        AddressType addressType = AddressType.getValueOf(type);
        if (addressType == null || address == null) {
            response.setStatus(StatusCode.E400);
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        } else {
            response = pctlAddressService.validate(address, addressType);
            return new ResponseEntity<Response>(response, response.getHttpStatus());
        }
    }


}


