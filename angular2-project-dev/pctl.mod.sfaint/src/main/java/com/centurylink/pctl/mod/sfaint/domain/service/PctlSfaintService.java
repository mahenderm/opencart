package com.centurylink.pctl.mod.sfaint.domain.service;


import com.centurylink.pctl.mod.sfaint.modal.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


//import static org.hamcrest.core.Is.is;

/**
 * The {@link SfaintService} implements business logic for
 * Sales force integeration
 *
 */

/**
 * @author Dinesh
 */
@Service
/*@Configuration
@ConfigurationProperties(prefix = "sfaint")*/
public class PctlSfaintService {

    private final Log log = LogFactory.getLog(PctlSfaintService.class);

    private RestTemplate restTemplate;

    private String accounturl;

   /* private CreateOppurtunityResponse createOppurtunityResponse = null;
    private CreateAccountResponse createAccountResponse = null;
    private CreateAccountRequest createAccountRequest = null;
    private CreateOppurtunityRequest createOppurtunityRequest = null;
    private CreditCheckRequest creditCheckRequest = null;
    private CreditCheckResponse creditCheckResponse = null;
    private CreateLeadRequest createLeadRequest = null;
    private CreateLeadResponse createLeadResponse = null;
    private SfaintEvent sfaintEvent = null;*/

    @Autowired
    private CreateOppurtunityResponse createOppurtunityResponse;
    @Autowired
    private CreateAccountResponse createAccountResponse;
    @Autowired
    private CreateAccountRequest createAccountRequest ;
    @Autowired
    private CreateOppurtunityRequest createOppurtunityRequest;
    @Autowired
    private CreditCheckRequest creditCheckRequest ;
    @Autowired
    private CreditCheckResponse creditCheckResponse;
    @Autowired
    private CreateLeadRequest createLeadRequest ;
    @Autowired
    private CreateLeadResponse createLeadResponse ;
    @Autowired
    private SfaintEvent sfaintEvent ;
    @Autowired
    public PctlSfaintService(@LoadBalanced RestTemplate normalRestTemplate) {
        this.restTemplate = normalRestTemplate;
    }

    public String getAccounturl() {
        return accounturl;
    }

    public void setAccounturl(String accounturl) {
        this.accounturl = accounturl;
    }


    public CreateAccountResponse createSFAccount(SfaintEvent sfaintEvent) {
        restTemplate = new RestTemplate();
        log.info("createSFAccount Entered : " + PctlSfaintConstant.CREATE_SF_ACCOUNT_URL + " restTemplate " + restTemplate);

        try {
            // if(sfaintEvent.getCreateAccountRequest() != null)
            if(sfaintEvent.getOrderEvent() != null && sfaintEvent.getOrderEvent().getOrderId() != null)
            {
                // call to new SF account creation
                //  this.createAccountResponse = restTemplate.postForObject(PctlSfaintConstant.CREATE_SF_ACCOUNT_URL, createAccountRequest, CreateAccountResponse.class);

                // temporarily response

                this.createAccountResponse = new CreateAccountResponse();
                this.createAccountResponse.setUUID(PctlSfaintResponseConstant.CREATE_ACCOUNT_UUID);
                this.createAccountResponse.setAccountId(PctlSfaintResponseConstant.CREATE_ACCOUNT_ID);
                this.createAccountResponse.setMessage(PctlSfaintResponseConstant.CREATE_ACCOUNT_MESSAGE);
                this.createAccountResponse.setSuccess(PctlSfaintResponseConstant.TRUE);

            }

        } catch (Exception ex) {
            this.sfaintEvent.setStatus(SfaintStatusType.SFAINT_ACCOUNT_CREATION_FAILED);
            log.info("CreateSFAccount Status : " + SfaintStatusType.SFAINT_ACCOUNT_CREATION_FAILED);
            log.info("createSFAccountRequest : "+this.createAccountRequest);
            log.info("Account creation failed :" + this.createAccountResponse.toString());
            log.info("createSFAccount Exception : " + ex.toString());
        }

        return this.createAccountResponse;
    }

    public CreateOppurtunityResponse createOppurtunity(SfaintEvent sfaintEvent) {
        log.info("create oppurtunity status : "+SfaintStatusType.SFAINT_CREATE_OPPURTUNITY_INIT);
        restTemplate = new RestTemplate();

        try {
            if(this.createOppurtunityRequest != null && this.createOppurtunityRequest.getAccountUUID() != null) {

                // call to do oppurtunity creation
                //   createOppurtunityResponse = restTemplate.postForObject(PctlSfaintConstant.CREATE_OPPURTUNITY_URL, createOppurtunityRequest, CreateOppurtunityResponse.class);

                //    temporary response
                this.createOppurtunityResponse = new CreateOppurtunityResponse();
                this.createOppurtunityResponse.setErrorCode(PctlSfaintResponseConstant.ERROR_CODE);
                this.createOppurtunityResponse.setErrorMessage(PctlSfaintResponseConstant.FAILURE);
                this.createOppurtunityResponse.setOpportunityId(PctlSfaintResponseConstant.CREATE_OPPURTUNITY_ID);
                this.createOppurtunityResponse.setStatus(PctlSfaintResponseConstant.SUCCESS);
            }


        } catch (Exception ex) {
            this.sfaintEvent.setStatus(SfaintStatusType.SFAINT_CREATE_OPPURTUNITY_FAILED);
            log.info("createOppurtunity Status : " + SfaintStatusType.SFAINT_CREATE_OPPURTUNITY_FAILED);
            log.info("createOppurtunityRequest : "+this.createOppurtunityRequest);
            log.info("createOppurtunity failed :" + this.createOppurtunityResponse.toString());
            log.info("createOppurtunity Exception :  " + ex.toString());
        }

        return this.createOppurtunityResponse;

    }


    public CreditCheckResponse docreditCheck(SfaintEvent sfaintEvent) {
        log.info("docreditCheck status : "+SfaintStatusType.SFAINT_CREDIT_CHECK_INIT);
        restTemplate = new RestTemplate();
        log.info("docreditCheck Entered : " + PctlSfaintConstant.CREATE_CREDIT_CHECK_URL + " restTemplate " + restTemplate +" creditcheck "+ creditCheckRequest);

        try {
            if(this.creditCheckRequest != null && this.creditCheckRequest.getOppotunityID() != null) {
                this.creditCheckRequest.setOppotunityID(this.createOppurtunityResponse.getOpportunityId());

                //        this.creditCheckResponse = restTemplate.postForObject(PctlSfaintConstant.CREATE_CREDIT_CHECK_URL, creditCheckRequest, CreditCheckResponse.class);

                // temporary response
                this.creditCheckResponse = new CreditCheckResponse();
                this.creditCheckResponse.setStatus(PctlSfaintResponseConstant.SUCCESS);
                this.creditCheckResponse.setErrorMessage(PctlSfaintResponseConstant.EMPTY);
                this.creditCheckResponse.setErrorCode(PctlSfaintResponseConstant.EMPTY);
                this.creditCheckResponse.setCreditStatus(PctlSfaintResponseConstant.FAILURE);
            }

        } catch (Exception ex) {
            this.sfaintEvent.setStatus(SfaintStatusType.SFAINT_CREDIT_CHECK_FAILED);
            log.info("CreateSFAccount Status : " + SfaintStatusType.SFAINT_CREDIT_CHECK_FAILED);
            log.info("CreditCheckRequest : " +this.creditCheckRequest);
            log.info("Credit Check Failed : " + this.creditCheckResponse.toString());
            log.info("Credit Check Exception : " + ex.toString());
        }

        return this.creditCheckResponse;
    }

    public CreateLeadResponse createLead(SfaintEvent sfaintEvent) {
        restTemplate = new RestTemplate();
        log.info("createLead status : "+SfaintStatusType.SFAINT_CREATE_LEAD_INIT);
        log.info("leadGeneration........" + PctlSfaintConstant.CREATE_LEAD_URL + " restTemplate " + restTemplate +" lead generation "+ createLeadRequest);

        try {

            if(this.createLeadRequest != null && this.creditCheckResponse.getCreditStatus().equalsIgnoreCase("Fail") /*&& this.createLeadRequest.getEmail() != null && this.createLeadRequest.getPhone() != null*/) {

                //   createLeadResponse = restTemplate.postForObject(PctlSfaintConstant.CREATE_LEAD_URL, createLeadRequest, CreateLeadResponse.class);
                this.createLeadResponse = new CreateLeadResponse();
                this.createLeadResponse.setLeadId(PctlSfaintResponseConstant.CREATE_LEAD_ID);
                this.createLeadResponse.setSuccess(PctlSfaintResponseConstant.TRUE);
                this.createLeadResponse.setMessage(PctlSfaintResponseConstant.CREATE_LEAD_MESSAGE);
            }

        } catch (Exception ex) {
            this.sfaintEvent.setStatus(SfaintStatusType.SFAINT_CREATE_LEAD_FAILED);
            log.info("createLead Status : " + SfaintStatusType.SFAINT_CREATE_LEAD_FAILED);
            log.info("createLeadRequest : " + this.creditCheckRequest);

            log.info("createLead Failed: " + this.createLeadResponse.toString());
            log.info("createLead Exception: " + ex.toString());
        }

        return this.createLeadResponse;
    }

    public void sfaintevent(SfaintEvent sfaintEvent){



        createSfaintRequest(sfaintEvent);

        switch(sfaintEvent.getSfaintEventType())
        {
            case CREATE_ACCOUNT:
            {
                this.createAccountResponse = createSFAccount(sfaintEvent);

                if(this.createAccountResponse != null && this.createAccountResponse.isSuccess() && this.createAccountResponse.getMessage().equalsIgnoreCase("CreatedAccount"))
                {
                    // success message
                    this.sfaintEvent.setStatus(SfaintStatusType.SFAINT_ACCOUNT_CREATION_COMPLETED);
                    log.info("create sfaint account status : " + SfaintStatusType.SFAINT_ACCOUNT_CREATION_COMPLETED);

                    // create oppurtunity call
                    this.createOppurtunityRequest.setAccountUUID(this.createAccountResponse.getUUID());
                    this.sfaintEvent.setSfaintEventType(SfaintEventType.CREATE_OPPURTUNITY);
                    sfaintevent(this.sfaintEvent);
                    break;

                }
                else
                {
                    // throw error message here
                    this.sfaintEvent.setStatus(SfaintStatusType.SFAINT_ACCOUNT_CREATION_FAILED);
                    log.info("CreateSFAccount Status : " + SfaintStatusType.SFAINT_ACCOUNT_CREATION_FAILED);
                    log.info("createSFAccountRequest : "+this.createAccountRequest);
                    log.info("Account creation failed :" + this.createAccountResponse.toString());

                    // again it has to hit CREATE_ACCOUNT , till it gets success
                    break;

                }


            }

            case CREATE_OPPURTUNITY :
            {


                this.createOppurtunityResponse = createOppurtunity(sfaintEvent);

                if(this.createOppurtunityResponse != null && this.createOppurtunityResponse.getOpportunityId() != null)
                {
                    this.sfaintEvent.setStatus(SfaintStatusType.SFAINT_CREATE_OPPURTUNITY_COMPLETED);
                    log.info("create oppurtunity status : "+SfaintStatusType.SFAINT_CREATE_OPPURTUNITY_COMPLETED);
                    this.creditCheckRequest.setOppotunityID(this.createOppurtunityResponse.getOpportunityId());
                    this.sfaintEvent.setSfaintEventType(SfaintEventType.CREDIT_CHECK);
                    // call to do credit check
                    sfaintevent(this.sfaintEvent);
                    break;
                }
                else {
                    this.sfaintEvent.setStatus(SfaintStatusType.SFAINT_CREATE_OPPURTUNITY_FAILED);
                    log.info("createOppurtunity Status : " + SfaintStatusType.SFAINT_CREATE_OPPURTUNITY_FAILED);
                    log.info("createOppurtunityRequest : "+this.createOppurtunityRequest);
                    log.info("createOppurtunity :" + this.createOppurtunityResponse.toString());
                    // again it has to hit CREATEOPPUNITY , till it gets success
                    break;
                }

            }

            case CREDIT_CHECK:
            {

                this.creditCheckResponse = docreditCheck(sfaintEvent);

                if(this.creditCheckResponse != null && this.creditCheckResponse.getCreditStatus().equalsIgnoreCase(PctlSfaintResponseConstant.SUCCESS))
                {
                    this.sfaintEvent.setStatus(SfaintStatusType.SFAINT_CREDIT_CHECK_COMPLETED);
                    log.info("DoCreditCheck status : "+SfaintStatusType.SFAINT_CREDIT_CHECK_COMPLETED);
                    break;
                }
                else
                {
                    this.sfaintEvent.setStatus(SfaintStatusType.SFAINT_CREDIT_CHECK_COMPLETED);
                    log.info("DoCreditCheck status : "+SfaintStatusType.SFAINT_CREDIT_CHECK_COMPLETED);
                    this.sfaintEvent.setSfaintEventType(SfaintEventType.CREATE_LEAD);
                    // redirect to lead
                    sfaintevent(this.sfaintEvent);
                    break;
                }

            }
            case CREATE_LEAD:
            {
                // call to do lead creation
                this.createLeadResponse =  createLead(sfaintEvent);

                if(this.createLeadResponse != null) {
                    this.sfaintEvent.setStatus(SfaintStatusType.SFAINT_CREATE_LEAD_COMPLETED);
                    log.info("Create Lead status : "+SfaintStatusType.SFAINT_CREATE_LEAD_COMPLETED);
                    break;
                }
                else {
                    // again it has to hit lead creation , till it gets success
                    break;
                }


            }

        }
        //existing user
    }


    private void createSfaintRequest(SfaintEvent sfaintEvent) {
        if (this.sfaintEvent == null && this.createAccountRequest == null && this.createOppurtunityRequest == null && this.createAccountRequest == null && this.creditCheckRequest == null) {
            this.sfaintEvent = sfaintEvent;
            // create account request formation
      //      CreateAccountRequest createaccount = new CreateAccountRequest();
      //      this.createAccountRequest = new CreateAccountRequest();
            this.createAccountRequest.setStreetaddline1(this.sfaintEvent.getOrderEvent().getBillingAddress().getAddressLine());
            this.createAccountRequest.setStreetaddline2(this.sfaintEvent.getOrderEvent().getBillingAddress().getStreet());
            this.createAccountRequest.setCity(this.sfaintEvent.getOrderEvent().getBillingAddress().getCity());
            this.createAccountRequest.setZip(this.sfaintEvent.getOrderEvent().getBillingAddress().getZipCode());
            this.createAccountRequest.setState(this.sfaintEvent.getOrderEvent().getBillingAddress().getState());
            this.createAccountRequest.setCountry(this.sfaintEvent.getOrderEvent().getBillingAddress().getCountry());

            // create oppurtunity request formation
            List<ProductInfo> productInfoList = new ArrayList<>();
            for (int i = 0; i < this.sfaintEvent.getOrderEvent().getShoppingCart().getLineItems().size(); i++) {

                ProductInfo productInfo = new ProductInfo();
                productInfo.setProductCode(this.sfaintEvent.getOrderEvent().getShoppingCart().getLineItems().get(i).getProductId());
                productInfo.setTerm(this.sfaintEvent.getOrderEvent().getShoppingCart().getLineItems().get(i).getTerm());

                productInfoList.add(productInfo);
            }

            this.createOppurtunityRequest = new CreateOppurtunityRequest();
            this.createOppurtunityRequest.setProductInfoList(productInfoList);

            // create lead request formation
            this.createLeadRequest = new CreateLeadRequest();
            this.createLeadRequest.setCompany(this.sfaintEvent.getOrderEvent().getCompanyName());

            // create credit check request formation
            this.creditCheckRequest = new CreditCheckRequest();
            this.creditCheckRequest.setPullback(false);

        }
    }
}
