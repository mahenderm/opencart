package com.centurylink.pctl.mod.user.controllers.rest;

import com.centurylink.pctl.mod.core.camel.CamelException;
import com.centurylink.pctl.mod.core.camel.factory.CamelServiceFactory;
import com.centurylink.pctl.mod.core.camel.factory.MessageTypeEnum;
import com.centurylink.pctl.mod.core.camel.service.ICamelService;
import com.centurylink.pctl.mod.core.model.user.LoggedUser;
import com.centurylink.pctl.mod.core.security.jwt.extractor.TokenExtractor;
import com.centurylink.pctl.mod.core.security.jwt.token.JwtSettings;
import com.centurylink.pctl.mod.core.utils.Response;
import com.centurylink.pctl.mod.core.utils.StatusCode;
import com.centurylink.pctl.mod.user.domain.camel.GetAccountDetailByIdResponse;
import com.centurylink.pctl.mod.user.domain.camel.GetAccountsResponse;
import com.centurylink.pctl.mod.user.domain.camel.GetUserByUsernameResponse;
import com.centurylink.pctl.mod.user.domain.camel.GetUserEnterprisesResponse;
import com.centurylink.pctl.mod.user.domain.user.PctlUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Begin Samuel
 */

@Transactional
@RestController
@RequestMapping("/auth")
public class PctlApiUserRestController {

    @Autowired
    private PctlUserServices pctlUserServices;

    private static final Logger log = LoggerFactory
        .getLogger(com.centurylink.pctl.mod.user.controllers.rest.PctlApiUserRestController.class);

    @Autowired
    private PctlUserRepository userRepository;

    @Autowired
    private TokenExtractor tokenExtractor;

    @Autowired
    private JwtSettings jwtSettings;

    SecurityContext context = SecurityContextHolder.createEmptyContext();

   // user init while logging
    @RequestMapping(value = "/user/init", method = RequestMethod.POST)
    public Response initUser
        (@RequestBody LoggedUser loggedUser, HttpServletResponse responce){
        Response msg = new Response();
        if(loggedUser == null){
            msg.setStatus(StatusCode.U202);
            return msg;
        }
        LoggedUser user =  pctlUserServices.getUserByLogin(loggedUser);
        if(user == null){
            msg.setStatus(StatusCode.U202);
            return msg;
        }
        responce.setHeader(pctlUserServices.AUTHTOKEN,user.getToken());
        msg.setContent(user);
        msg.setStatus(StatusCode.U200);
        return msg;

    }


//used used to find user by AuthToken
    @RequestMapping(value = "/user/find", method = RequestMethod.GET)
    public LoggedUser getUserInfo(HttpServletRequest request) {
        if(request.getHeader(pctlUserServices.LOGIN)!=null) {
            LoggedUser user = pctlUserServices.getUserName(request.getHeader("login"));
            return user;
        }

        LoggedUser user = new  LoggedUser();
        return user;
    }

    //Method returns current user
    //@Cacheable(value = "user-me")
    @RequestMapping(value = "/user/me", method = RequestMethod.GET)
    public Response getUserMe(HttpServletRequest request) {
        Response msg = new Response();
        if(request.getHeader("authToken")!=null) {
            LoggedUser user = pctlUserServices.getUserByFirstName(request);
            if(user != null) {
                msg.setContent(user);
                msg.setStatus(StatusCode.U200);
                return msg;
            }
            else{
                msg.setStatus(StatusCode.U202);
                return msg;
            }
        }
        msg.setStatus(StatusCode.U202);
        return msg;
    }

/*    //return logged user for ActiveMq
    @RequestMapping(value = "/user/logged-user", method = RequestMethod.GET)
    public LoggedUser getAuthenticatedUserFrActiveMq(HttpServletRequest request) {
        LoggedUser user = pctlUserServices.getUserByFirstName(request);
        return user;
    }*/



    @RequestMapping(value = "/user/getAccounts", method = RequestMethod.GET)
    public  void getAccounts(HttpServletRequest request) {
        Response<GetAccountsResponse> response = new Response<>();
        ICamelService service = CamelServiceFactory.getService(MessageTypeEnum.SOAP.name());
        try {
            log.info("getAccounts Process Started-->");
            GetAccountsResponse dataVO  = null;
            dataVO =(GetAccountsResponse) service.process("getAccounts", request);
            log.info("dataVO.toString()=====.??>" + dataVO.toString());
            response.setContent(dataVO);
            response.setStatus(StatusCode.U200);
            log.info("getAccounts Process End");
        } catch (CamelException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/user/getAccountDetailById", method = RequestMethod.GET)
    public  void getAccountDetailById(HttpServletRequest request) {
        Response<GetAccountDetailByIdResponse> response = new Response<>();
        ICamelService service = CamelServiceFactory.getService(MessageTypeEnum.SOAP.name());
        try {
            log.info("getAccountDetailById Process Started-->");
            GetAccountDetailByIdResponse dataVO = null;
            dataVO =(GetAccountDetailByIdResponse) service.process("getAccountDetailById", request);

            log.info("dataVO.toString()=====.??>" + dataVO.toString());
            response.setContent(dataVO);
            response.setStatus(StatusCode.U200);
            log.info("getAccountDetailById Process End");
        } catch (CamelException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/user/getUserEnterprises", method = RequestMethod.GET)
    public  void getUserEnterprises(HttpServletRequest request) {
        Response<GetUserEnterprisesResponse> response = new Response<>();
        ICamelService service = CamelServiceFactory.getService(MessageTypeEnum.SOAP.name());
        try {
            log.info("getUserEnterprises Process Started-->");
            GetUserEnterprisesResponse dataVO  = null;
            dataVO =(GetUserEnterprisesResponse) service.process("getUserEnterprises", request);
            log.info("dataVO.toString()=====.??>" + dataVO.toString());
            response.setContent(dataVO);
            response.setStatus(StatusCode.U200);
            log.info("getUserEnterprises Process End");
        } catch (CamelException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/user/getUserByUsername", method = RequestMethod.GET)
    public  void getUserByUsername(HttpServletRequest request) {
        Response<GetUserByUsernameResponse> response = new Response<>();
        ICamelService service = CamelServiceFactory.getService(MessageTypeEnum.SOAP.name());
        try {
            log.info("getUserByUsername Process Started-->");
            GetUserByUsernameResponse dataVO  = null;
            dataVO =(GetUserByUsernameResponse) service.process("getUserByUsername", request);
            log.info("dataVO.toString()=====.??>" + dataVO.toString());
            response.setContent(dataVO);
            response.setStatus(StatusCode.U200);
            log.info("getUserByUsername Process End");
        } catch (CamelException e) {
            e.printStackTrace();
        }
    }


/*
    //cart init when user add product to cart
    @RequestMapping(value = "/user/cart/init", method = RequestMethod.POST)
    public Response cartInit(@RequestBody PctlUserCartModel cartModel, HttpServletRequest request, HttpServletResponse response) {
        Response msg = new Response();
        if(cartModel.getCartId() == null || cartModel.getCartId().isEmpty()||cartModel.getCartId()==""){
            msg.setStatus(StatusCode.U203);
            return msg;
        }

        if(request.getHeader(pctlUserServices.AUTHTOKEN)!=null) {
            CartInfo cartInfo = pctlUserServices.postCart(cartModel, request);
            if(cartInfo.getShoppingCartId() != null
                &&cartInfo.getShoppingCartId()!= ""
                &&!cartInfo.getShoppingCartId().isEmpty()) {
                msg.setContent(cartInfo);
                msg.setStatus(StatusCode.U200);
                return msg;
            }
            else{
                msg.setStatus(StatusCode.U202);
                return msg;
            }
        }
        msg.setStatus(StatusCode.U202);
        return msg;
    }

    //user profile will be updated with order id
    @RequestMapping(value = "/user/order/update", method = RequestMethod.POST)
    public Response orderInit(@RequestBody PctlUserOrderModel orderModel, HttpServletRequest request, HttpServletResponse response) {
        Response msg = new Response();

        if(orderModel.getOrderId() == null||orderModel.getOrderId().isEmpty()||orderModel.getOrderId()==""){
            msg.setStatus(StatusCode.U204);
            return msg;
        }
        if(request.getHeader(pctlUserServices.AUTHTOKEN)!=null) {
            OrderInfo orderInfo = pctlUserServices.postOrder(orderModel, request);
            if(orderInfo.getOrderId() != null &&
                orderInfo.getOrderId() != "" &&
                !orderInfo.getOrderId().isEmpty()) {
                msg.setContent(orderInfo);
                msg.setStatus(StatusCode.U200);
                return msg;
            }
            else{
                msg.setStatus(StatusCode.U202);
                return msg;
            }
        }
        msg.setStatus(StatusCode.U202);
        return msg;
    }*/


    //For "checkuser" CamelIntegration
   /* @RequestMapping(value = "/user/checkuser", method = RequestMethod.GET)
    public  void checkForUser(HttpServletRequest request) {
        Response<CheckForUserResponse> checkUserResponse = new Response<>();
        ICamelService service = CamelServiceFactory.getService(MessageTypeEnum.SOAP.name());
        try {
             log.info("checkForUser Process Started");
//            log.info("Response at "+new Date()+") : " + service.process("checkForUser", request));
//            UserEnterpriseDataSO dataVO = (UserEnterpriseDataSO) service.process("checkForUser", request);
            CheckForUserResponse dataVO = (CheckForUserResponse) service.process("checkForUser", request);
            log.info("dataVO.toString()=====.??>" + dataVO.toString());
            checkUserResponse.setContent(dataVO);
            checkUserResponse.setStatus(StatusCode.U200);
            log.info("checkForUser Process End");
        } catch (CamelException e) {
            e.printStackTrace();
        }
//        return checkUserResponse;
    }

    //For "createenterprise" CamelIntegration
    @RequestMapping(value = "/user/createenterprise", method = RequestMethod.GET)
    public  void createEnterprise(HttpServletRequest request) {
        Response<CreateEnterpriseResponse> checkUserResponse = new Response<>();
        ICamelService service = CamelServiceFactory.getService(MessageTypeEnum.SOAP.name());
        try {
            log.info("createEnterprise Process Started");
            CreateEnterpriseResponse dataVO = (CreateEnterpriseResponse) service.process("createEnterprise", request);
            log.info("dataVO.toString()=====.??>" + dataVO.toString());
            checkUserResponse.setContent(dataVO);
            checkUserResponse.setStatus(StatusCode.U200);
            log.info("createEnterprise Process End");
        } catch (CamelException e) {
            e.printStackTrace();
        }
//        return checkUserResponse;
    }

    //For "assignaccountstouser" CamelIntegration
    @RequestMapping(value = "/user/assignaccountstouser", method = RequestMethod.GET)
    public  void assignAccountsToUser(HttpServletRequest request) {
        Response<AssignAccountsToUserResponse> assignAccountsToUserResponse = new Response<>();
        ICamelService service = CamelServiceFactory.getService(MessageTypeEnum.SOAP.name());
        try {
            log.info("assignAccountsToUser Process Started");
            AssignAccountsToUserResponse dataVO = (AssignAccountsToUserResponse) service.process("assignAccountsToUser", request);
            log.info("dataVO.toString()=====.??>" + dataVO.toString());
            assignAccountsToUserResponse.setContent(dataVO);
            assignAccountsToUserResponse.setStatus(StatusCode.U200);
            log.info("assignAccountsToUser Process End");
        } catch (CamelException e) {
            e.printStackTrace();
        }
//        return checkUserResponse;
    }*/



}
