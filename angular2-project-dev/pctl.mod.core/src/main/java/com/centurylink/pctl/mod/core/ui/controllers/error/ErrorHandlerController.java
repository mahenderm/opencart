package com.centurylink.pctl.mod.core.ui.controllers.error;

import com.google.common.collect.Lists;
import com.centurylink.pctl.mod.core.dto.error.ServerErrorDTO;
import org.apache.catalina.connector.ClientAbortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ErrorHandlerController class
 * @author Begin Samuel
 */

@ControllerAdvice
public class ErrorHandlerController {

    private static final Logger log = LoggerFactory.getLogger(ErrorHandlerController.class);

    /**
     * procesHttpMediaTypeNotAcceptableException()
     * @param ex
     */
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public void procesHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex) {
        log.debug(ex.getMessage(), ex);
    }

    /**
     * processClientAbortException()
     * @param ex
     */

    @ExceptionHandler(ClientAbortException.class)
    public void processClientAbortException(ClientAbortException ex) {
        log.debug(ex.getMessage(), ex);
        log.debug("client disconected");
    }

    /**
     * processException()
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ServerErrorDTO processException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ServerErrorDTO(ex.getClass().getCanonicalName(), ex.getMessage(), Lists.newArrayList());
    }

}
