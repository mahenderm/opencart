package com.centurylink.pctl.mod.ui.controllers.web;


import com.centurylink.pctl.mod.core.model.cart.CartEvent;
import com.centurylink.pctl.mod.core.model.order.OrderEvent;
import com.centurylink.pctl.mod.core.model.user.CartModel;
import com.centurylink.pctl.mod.core.model.user.LoggedUser;
import com.centurylink.pctl.mod.core.utils.Response;
import com.centurylink.pctl.mod.core.utils.StatusCode;
import com.centurylink.pctl.mod.ui.jms.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by sakthivel-s on 31-10-2016.
 */

@Transactional
@RestController
@RequestMapping("/ui-service")

public class PctlApiUiRestController {

  private PctlApiUiService pctlApiUiService;

  @Autowired
  public PctlApiUiRestController(PctlApiUiService pctlApiUserService) {
    this.pctlApiUiService = pctlApiUserService;
  }

  @RequestMapping(value = "/user/login", method = RequestMethod.GET)
  public Response getloggedUser(HttpServletRequest request,
                                HttpServletResponse response) {
    Response res = pctlApiUiService.getLoginFromUser(request, response);
    return res;
  }

  @RequestMapping(value = "/user/me", method = RequestMethod.GET)
  public Response getAuthenticatedUser(HttpServletRequest request,
                                       HttpServletResponse response) {
    Response user = pctlApiUiService.getAuthenticatedUser(request, response);
    return user;

  }

  @RequestMapping(value = "/user/logout", method = RequestMethod.GET)
  public Response logoutPage(HttpServletRequest request, HttpServletResponse response) {
    Response msg = new Response();
    String name = "no user found";
    HttpSession session = request.getSession();
    SecurityContext context = (SecurityContext) session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
    if (context != null) {

      Authentication auth = context.getAuthentication();
      name = auth.getPrincipal().toString();
      if (auth != null) {
        new SecurityContextLogoutHandler().logout(request, response, auth);
        msg.setStatus(StatusCode.U200);
        msg.setContent("User Logged Out Successfully ");
        return msg;
      }
    }
    msg.setStatus(StatusCode.U402);
    return msg;
  }


  @Autowired
  MessageService messageService;


  @RequestMapping(value = "/cart/init", method = RequestMethod.POST)
  public Response<String> cartInit(@RequestBody CartModel cartModel, HttpServletRequest request, HttpServletResponse response) {

    return pctlApiUiService.cartInit(cartModel, request, response);

  }

  @RequestMapping(value = "/session-init", method = RequestMethod.POST)
  public Response userSession(@RequestBody LoggedUser loggedUser, HttpServletRequest request,
                              HttpServletResponse response) {
    Response msg = null;
    try {
      msg = pctlApiUiService.getUserSession(loggedUser, request, response);

      //  return new Response(StatusCode.U200.getCode(),StatusCode.U200.getMessage(),StatusCode.U200.getHttpStatus());
      return msg;
    } catch (Exception e) {
      return new Response(StatusCode.U202.getCode(), StatusCode.U202.getMessage(), StatusCode.U202.getHttpStatus());
    }
  }

  @RequestMapping(value = "/cart/events", method = RequestMethod.POST)
  public Response<String> cartEvents(@RequestBody CartEvent cartEvent,
                                     @RequestParam String cartId,
                                     @RequestParam String cartEventType,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {

    return pctlApiUiService.cartEvents(cartEvent, cartId, cartEventType, request, response);

  }


  @RequestMapping(value = "/order/events", method = RequestMethod.POST)
  public Response<String> orderEvents(@RequestBody OrderEvent orderEvent, HttpServletRequest request,
                                      HttpServletResponse response) {

    return pctlApiUiService.orderEvents(orderEvent, request, response);
  }

}
