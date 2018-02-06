package com.centurylink.pctl.mod.sfaint.controllers.rest;

import com.centurylink.pctl.mod.core.utils.Response;
import com.centurylink.pctl.mod.core.utils.StatusCode;
import com.centurylink.pctl.mod.sfaint.domain.service.PctlSfaintService;
import com.centurylink.pctl.mod.sfaint.modal.SfaintEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Dinesh
 */

@RestController
@RequestMapping("/sfaint")
public class PctlSfaintRestController {
    private static final Logger log = LoggerFactory.getLogger(PctlSfaintRestController.class);
    @Autowired
    private PctlSfaintService sfaintService;

    @RequestMapping(value = "/service", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response sfaintService(@RequestBody SfaintEvent sfaintEvent, HttpServletRequest request,
                              HttpServletResponse response) {
        Response sfaintResponse = null;
        if(request == null || sfaintEvent == null){

            sfaintResponse.setStatus(StatusCode.E400);
        }

        sfaintService.sfaintevent(sfaintEvent);
        sfaintResponse.setContent(sfaintService);
        sfaintResponse.setStatus(StatusCode.U200);

        return sfaintResponse;

    }



}
