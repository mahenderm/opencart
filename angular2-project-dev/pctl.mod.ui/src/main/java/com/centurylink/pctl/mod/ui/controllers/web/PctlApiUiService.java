package com.centurylink.pctl.mod.ui.controllers.web;

import com.centurylink.pctl.mod.core.event.EventHeader;
import com.centurylink.pctl.mod.core.event.ShoppingCartEvent;
import com.centurylink.pctl.mod.core.model.cart.CartEvent;
import com.centurylink.pctl.mod.core.model.cart.CartEventType;
import com.centurylink.pctl.mod.core.model.order.OrderEvent;
import com.centurylink.pctl.mod.core.model.order.OrderEventType;
import com.centurylink.pctl.mod.core.model.user.*;
import com.centurylink.pctl.mod.core.security.jwt.token.JwtTokenFactory;
import com.centurylink.pctl.mod.core.security.jwt.token.UserTokenExtract;
import com.centurylink.pctl.mod.core.utils.Response;
import com.centurylink.pctl.mod.core.utils.StatusCode;
import com.centurylink.pctl.mod.ui.jms.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by sakthivel-s on 31-10-2016.
 */
@Service
public class PctlApiUiService {

  private RestTemplate restTemplate;

  @Autowired
  JwtTokenFactory jwtTokenFactory;

  @Autowired
  public PctlApiUiService(@LoadBalanced RestTemplate normalRestTemplate) {
    this.restTemplate = normalRestTemplate;
  }

  public Response getAuthenticatedUser(HttpServletRequest request,
                                       HttpServletResponse response) {
    Response msg = new Response();
    HttpSession session = request.getSession();
    SecurityContext context = (SecurityContext) session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
    if (context == null) {
      msg.setStatus(StatusCode.U403);
      return msg;
    } else {
      String login;
      msg.setStatus(StatusCode.U200);
      Authentication auth = context.getAuthentication();
      login = auth.getPrincipal().toString();
      HttpHeaders headers = new HttpHeaders();

      headers.add("authToken", jwtTokenFactory.createAccessJwtToken(login).getToken());
      HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
      ResponseEntity<Response> responseEntity = restTemplate.exchange("http://user-service/auth/user/me", HttpMethod.GET, entity, Response.class);
      if (responseEntity.getBody() == null) {
        msg.setStatus(StatusCode.U403);
        return msg;
      }
      msg.setContent(responseEntity.getBody().getContent());
      return msg;
    }
  }


  public Response getLoginFromUser(HttpServletRequest request,
                                   HttpServletResponse response) {
    Response msg = new Response();
    ObjectMapper mapper = new ObjectMapper();
    HttpHeaders headers = new HttpHeaders();
    if (request.getHeader("login") == null && request.getHeader("fingerPrint") == null) {
      msg.setStatus(StatusCode.U401);
      return msg;
    }

    if (request.getHeader("login") != null) {
      headers.add("login", request.getHeader("login"));
    }

    if (request.getHeader("fingerPrint") != null) {
      headers.add("fingerPrint", request.getHeader("fingerPrint"));
    }

    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
    ResponseEntity<LoggedUser> responseEntity = restTemplate.exchange("http://user-service/auth/user/find", HttpMethod.GET, entity, LoggedUser.class);
    if (responseEntity.getBody() == null) {
      msg.setStatus(StatusCode.U402);
      return msg;
    }
    LoggedUser resp = responseEntity.getBody();
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    for (LoggedUserAuthority role : resp.getAuthorities()) {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
    }
    SecurityContext context = SecurityContextHolder.createEmptyContext();

    HttpSession session = request.getSession();

    Authentication authentication = new UsernamePasswordAuthenticationToken(resp.getLogin(),
      null, authorities);
    context.setAuthentication(authentication);
    session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);
    SecurityContextHolder.setContext(context);
    msg.setStatus(StatusCode.U200);
    msg.setContent("authToken " + resp.getToken());
    response.setHeader("authToken", resp.getToken());
    return msg;
  }


  public Response logoutPage(HttpServletRequest request, HttpServletResponse response) {
    Response msg = new Response();
    String name = "no user found";
    HttpSession session = request.getSession();
    SecurityContext context = SecurityContextHolder.getContext();
    context = (SecurityContext) session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
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


  public Response getUserSession(LoggedUser loggedUser, HttpServletRequest request,
                                 HttpServletResponse response) {
    Response msg = new Response();

    if (loggedUser.getLogin() == null || loggedUser.getLogin().isEmpty()) {
      if (loggedUser.getFingerPrint() == null || loggedUser.getFingerPrint().isEmpty()) {
        msg.setStatus(StatusCode.U202);
        return msg;
      }

    }
    HttpSession session = request.getSession();
    SecurityContext context = SecurityContextHolder.createEmptyContext();
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    for (LoggedUserAuthority role : loggedUser.getAuthorities()) {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
    }
    Authentication authentication = new UsernamePasswordAuthenticationToken(loggedUser.getLogin(),
      null, authorities);
    context.setAuthentication(authentication);
    session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);
    SecurityContextHolder.setContext(context);
    msg.setStatus(StatusCode.U200);
    return msg;
  }


  @Autowired
  MessageService messageService;
  @Autowired
  UserTokenExtract userTokenExtract;

  public Response<String> cartInit(CartModel cartModel, HttpServletRequest request, HttpServletResponse response) {
    String login;
    HttpSession session = request.getSession();
    LoggedUser loggedUser = new LoggedUser();
    Response<String> msg = new Response<String>();
    if (cartModel.getCartId() == null || cartModel.getCartId().isEmpty()) {
      msg.setStatus(StatusCode.E701);
      return msg;
    }
    if (cartModel.getLogin() == null || cartModel.getLogin().isEmpty()) {
      if (cartModel.getFingerPrint() == null || cartModel.getFingerPrint().isEmpty()) {
        msg.setStatus(StatusCode.U202);
        return msg;
      }

    }


    SecurityContext context = (SecurityContext) session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
    if (context == null) {
      msg.setStatus(StatusCode.U403);
      return msg;
    }

    //  if (context == null) {

  /*    if (request.getHeader("authToken") == null && request.getHeader("authToken") == "") {
        msg.setStatus(StatusCode.U401);
        return msg;
      }

      login = userTokenExtract.getLoginFromToken(request.getHeader("authToken"));
      HttpHeaders headers = new HttpHeaders();
      headers.add("authToken", jwtTokenFactory.createAccessJwtToken(login).getToken());
      HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
      loggedUser.setLogin(login);
      loggedUser.setStatus("N");
      session.setAttribute("status", "N");

      List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
      for (LoggedUserAuthority role : loggedUser.getAuthorities()) {
        authorities.add(new SimpleGrantedAuthority(role.getName()));
      }
      Authentication authentication = new UsernamePasswordAuthenticationToken(loggedUser.getLogin(),
        null, authorities);
      context.setAuthentication(authentication);
      session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);
      SecurityContextHolder.setContext(context);
*/

    // }else{
    Authentication auth = context.getAuthentication();
    login = auth.getPrincipal().toString();
    loggedUser.setLogin(login);
    loggedUser.setStatus("Y");
    session.setAttribute("status", "Y");
    //   }

    EventHeader eventHeader = new EventHeader();
    Date dNow = new Date();
    DateTime dateTime = new DateTime(dNow);
    if (cartModel != null) {
      msg.setStatus(StatusCode.U200);
    }
    try {
      eventHeader.setEventId(Base64.getEncoder().encodeToString(dateTime.toString().getBytes("utf-8")));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    eventHeader.setName("CART-INIT");
    CartInfo cartInfo = new CartInfo(cartModel.getCartId(), 0);
    loggedUser.setCartInfo(cartInfo);
    CartEvent cartEvent = null;
    ShoppingCartEvent<CartEvent> cartItemEvent =
      new ShoppingCartEvent<CartEvent>(cartModel.getCartId(), eventHeader, loggedUser, cartEvent);
    messageService.send(cartItemEvent);
    return msg;
  }


  //method to save cart events
  public Response<String> cartEvents(CartEvent cartEvent,
                                     String cartId,
                                     String cartEventType,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
    HttpSession session = request.getSession();
    Response<String> msg = new Response<String>();
    EventHeader eventHeader = new EventHeader();
    Date dNow = new Date();
    DateTime dateTime = new DateTime(dNow);

    CartEventType cartEventTypes = CartEventType.getValueOf(cartEventType.toString());

    if (cartEventTypes == null) {
      msg.setStatus(StatusCode.E702);
      return msg;
    }
    if (cartEvent.getCartEventType() == null) {
      msg.setStatus(StatusCode.E702);
      return msg;
    }

    cartEvent.setCartEventType(cartEventTypes);
    cartEvent.setQuantity(1);

    if (cartId != null && (cartEvent.getCartId() != null && cartEvent.getCartId() != "")) {
      cartEvent.setCartId(cartId);
    } else {
      msg.setStatus(StatusCode.E701);
      return msg;
    }

    //--Jms Call Start
    LoggedUser loggedUser = new LoggedUser();
    SecurityContext context = (SecurityContext) session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
    if (context == null) {
      msg.setStatus(StatusCode.U403);
      return msg;
    }
    CartInfo cartInfo = new CartInfo(cartId, 0);
    Authentication auth = context.getAuthentication();
    String login = auth.getPrincipal().toString();

    loggedUser.setLogin(login);
    loggedUser.setLoggedIn(true);
    loggedUser.setStatus((String) session.getAttribute("status"));
    loggedUser.setCartInfo(cartInfo);

    try {
      eventHeader.setEventId(Base64.getEncoder().encodeToString(dateTime.toString().getBytes("utf-8")));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    eventHeader.setName("CART_EVENTS");
    eventHeader.setTimestamp(dateTime);

    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    String orderDate = format.format(new Date());

    ShoppingCartEvent<CartEvent> cartItemEvent = new ShoppingCartEvent<CartEvent>(cartId, eventHeader, loggedUser, cartEvent);
    if (cartEventType.toString().equalsIgnoreCase("CHECKOUT")) {
      String orderId = UUID.randomUUID().toString();
      msg.setContent(orderId);
      OrderInfo orderInfo = new OrderInfo(orderId, cartId, 0, orderDate, null);
      cartItemEvent.getUserInfo().setOrderInfo(orderInfo);
    }
    messageService.send(cartItemEvent);
    msg.setStatus(StatusCode.U200);
    return msg;
  }


  public Response<String> orderEvents(OrderEvent orderEvent, HttpServletRequest request,
                                      HttpServletResponse response) {
    Response<String> msg = new Response<String>();
    if (orderEvent.getOrderId() == null || orderEvent.getOrderId() == "") {
      msg.setStatus(StatusCode.U204);
      return msg;
    }

    if (orderEvent.getOrderEventType() == null) {
      msg.setStatus(StatusCode.E802);
      return msg;
    }

    OrderEventType orderEventType = OrderEvent.getValueOf(orderEvent.getOrderEventType().toString());


    if (orderEventType == null) {
      msg.setStatus(StatusCode.E802);
      return msg;
    }
    if (orderEvent.getCartId() == null || orderEvent.getCartId() == "") {
      msg.setStatus(StatusCode.U203);
      return msg;
    }

    HttpSession session = request.getSession();
    SecurityContext context = (SecurityContext) session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
    if (context == null) {
      msg.setStatus(StatusCode.U403);
      return msg;
    }
    EventHeader eventHeader = new EventHeader();
  /*  if (orderEvent.getOrderEventType().name().equalsIgnoreCase("ORDERED")) {
      eventHeader.setName("SFAINT");

    } else {*/
      eventHeader.setName("ORDER_EVENTS");
 //   }

    Date dNow = new Date();
    DateTime dateTime = new DateTime(dNow);
    try {
      eventHeader.setEventId(Base64.getEncoder().encodeToString(dateTime.toString().getBytes("utf-8")));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    LoggedUser loggedUser = new LoggedUser();
    Authentication auth = context.getAuthentication();
    String login = auth.getPrincipal().toString();

    loggedUser.setStatus((String) session.getAttribute("status"));
    if (login != null) {
      loggedUser.setLogin(login);
      ShoppingCartEvent<OrderEvent> orderEvents = new ShoppingCartEvent<OrderEvent>(orderEvent.getOrderId(), eventHeader, loggedUser, orderEvent);
      messageService.send(orderEvents);
      msg.setStatus(StatusCode.U200);
      return msg;
    } else {
      msg.setStatus(StatusCode.U403);
    }

    return msg;
  }


/*  public LoggedUser getAuthenticatedUserFrActiveMq(HttpServletRequest request,
                                                   HttpServletResponse response) {

    HttpSession session = request.getSession();
    SecurityContext context = (SecurityContext) session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
    String login;
    Authentication auth = context.getAuthentication();
    login = auth.getPrincipal().toString();
    HttpHeaders headers = new HttpHeaders();
    headers.add("authToken", jwtTokenFactory.createAccessJwtToken(login).getToken());
    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
    ResponseEntity<LoggedUser> responseEntity = restTemplate.exchange("http://user-service/auth/user/logged-user", HttpMethod.GET, entity, LoggedUser.class);
    return responseEntity.getBody();

  }*/


}
