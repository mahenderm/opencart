package com.centurylink.pctl.mod.user.controllers.rest;

import com.centurylink.pctl.mod.core.event.ShoppingCartEvent;
import com.centurylink.pctl.mod.core.model.address.Address;
import com.centurylink.pctl.mod.core.model.cart.LineItem;
import com.centurylink.pctl.mod.core.model.cart.ShoppingCart;
import com.centurylink.pctl.mod.core.model.order.OrderEvent;
import com.centurylink.pctl.mod.core.model.user.*;
import com.centurylink.pctl.mod.core.security.jwt.token.JwtTokenFactory;
import com.centurylink.pctl.mod.core.security.jwt.token.UserTokenExtract;
import com.centurylink.pctl.mod.user.domain.user.PctlUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sakthivel-s on 01-11-2016.
 */
@Service
public class PctlUserServices {
    @Autowired
    PctlUserServices pctlUserServices;

    @Autowired
    JwtTokenFactory jwtTokenFactory;

    @Autowired
    UserTokenExtract userTokenExtract;

    private RestTemplate restTemplate;

    public String ANONYMOUS = "Anonymous";
    public String AUTHTOKEN = "authToken";
    public String LOGIN =  "login";
    public String Y = "Y";



    @Autowired
    private PctlUserRepository userRepository;

    @Autowired
    public PctlUserServices(@LoadBalanced RestTemplate normalRestTemplate) {
        this.restTemplate = normalRestTemplate;
    }

    private static final Logger log = LoggerFactory.getLogger(PctlUserServices.class);

    public LoggedUser getUserByLogin(LoggedUser request) {

        LoggedUser user = null;
        if(request.getLogin()!= null  && request.getLogin()!="") {
            user = userRepository.findOneByLogin(request.getLogin());
            if(user!=null) {

                user.setLoggedIn(true);
                user.setToken(jwtTokenFactory.createAccessJwtToken(request.getLogin()).getToken());
                userRepository.save(user);
            }
            return user;
        }
        else if(request.getFingerPrint() != null && request.getFingerPrint()!="") {
            user = userRepository.findOneByFingerPrint(request.getFingerPrint());
            if(user == null){
                user = createAnonymousUser(request);
                return user;
            }
        }
        return  user;
    }

  //  @Cacheable("user")
    public LoggedUser getUserByFirstName(HttpServletRequest request) {
        LoggedUser user = userRepository.findOneByLogin(userTokenExtract.getLoginFromToken(request.getHeader(AUTHTOKEN)));
        if(user != null) {
            user.setToken(jwtTokenFactory.createAccessJwtToken(user.getLogin()).getToken());
            userRepository.save(user);
        }
        return  user;
    }



 public LoggedUser getUserName(String login){
     LoggedUser user = userRepository.findOneByLogin(login);
     user.setToken(jwtTokenFactory.createAccessJwtToken(user.getLogin()).getToken());
     return user;
   }

    public LoggedUser createAnonymousUser(LoggedUser request){
        LoggedUser AnonymousUser = new LoggedUser();
        AnonymousUser.setLogin(request.getFingerPrint());
        AnonymousUser.setFingerPrint(request.getFingerPrint());
        AnonymousUser.setFirstName(ANONYMOUS);
        AnonymousUser.setEmail(request.getFingerPrint());
        AnonymousUser.setLoggedIn(true);

        AnonymousUser.setLastName(ANONYMOUS);
        AnonymousUser.setToken(jwtTokenFactory.createAccessJwtToken(request.getFingerPrint()).getToken());

        LoggedUserAuthority LoggedUserAuthority = new LoggedUserAuthority(ANONYMOUS);
        LoggedUserAuthority LoggedUserAuthority1 = new LoggedUserAuthority(ANONYMOUS);
        Set<LoggedUserAuthority> authorities = new HashSet<LoggedUserAuthority>();
        authorities.add(LoggedUserAuthority);
        authorities.add(LoggedUserAuthority1);
        AnonymousUser.setAuthorities(authorities);


        userRepository.save(AnonymousUser);
        log.info("Anonymous User saved in Data base");
        return AnonymousUser;
    }



    public void postCart(ShoppingCartEvent shoppingCartEvent) {
        LoggedUser loggedUser = userRepository.findOneByLogin(shoppingCartEvent.getUserInfo().getLogin());
        CartInfo cartInfo = new CartInfo( shoppingCartEvent.getUserInfo().getCartInfo().getShoppingCartId(),0);
        Set<CartInfo> cartHist = new HashSet<CartInfo>();
        if (loggedUser.getCartHist().size()!=0) {
            cartHist.addAll(loggedUser.getCartHist());
        }
        if (loggedUser.getCartInfo() != null) {
            cartHist.add(loggedUser.getCartInfo());
        }
        loggedUser.setCartHist(cartHist);
        loggedUser.setCartInfo(cartInfo);
        log.info(" cart info {} ",cartInfo);
        log.info(" logged user {} ", loggedUser);
        loggedUser= userRepository.save(loggedUser);
         System.out.println(  loggedUser.toString()  );




        //public void postCart(PctlUserCartModel cartModel, HttpServletRequest request) {
       /* CartInfo cartInfo = new CartInfo(cartModel.getCartId(), 0);
        Set<CartInfo> cartHist = new HashSet<CartInfo>();
        LoggedUser user =  userRepository.findOneByLogin(userTokenExtract.getLoginFromToken(request.getHeader(AUTHTOKEN)));

        if(user != null) {

            if (user.getCartHist() != null) {
                cartHist.addAll(user.getCartHist());
            }
            if (user.getCartInfo() != null) {
                cartHist.add(user.getCartInfo());
            }
            user.setCartHist(cartHist);
            user.setCartInfo(cartInfo);
            userRepository.save(user);

           // return cartInfo;
        }

        return null;*/
    }


    public void postOrder(ShoppingCartEvent shoppingCartEvent) {

        LoggedUser loggedUser = userRepository.findOneByLogin(shoppingCartEvent.getUserInfo().getLogin());
        Set<OrderInfo> orderHist = new HashSet<OrderInfo>();
        //    if (shoppingCartEvent.getOrderInit()) {

        List<LineItem> lineItems = new ArrayList<>();
        ProductInfo productInfo = new ProductInfo();
        List<ProductInfo> productInfoList = new ArrayList<ProductInfo>();

        lineItems = ((ShoppingCart) shoppingCartEvent.getEventAttributes())
            .getLineItems();

        for (LineItem lineItemss : lineItems) {
            productInfo.setProduct(lineItemss.getProductName());
            productInfo.setLocationSize(lineItemss.getLocations().size());
            productInfo.setUnitPrice(lineItemss.getUnitPrice());
            productInfoList.add(productInfo);

        }

        OrderInfo orderInfo = new OrderInfo(shoppingCartEvent.getUserInfo().getOrderInfo().getOrderId(),
            shoppingCartEvent.getUserInfo().getCartInfo().getShoppingCartId(),
            0,
            shoppingCartEvent.getUserInfo().getOrderInfo().getOrderDate(),
            productInfoList);

        if (loggedUser.getOrderHist().size() != 0) {
            orderHist.addAll(loggedUser.getOrderHist());
        }
        if (loggedUser.getOrderInfo() != null) {
            orderHist.add(loggedUser.getOrderInfo());
        }
        loggedUser.setOrderHist(orderHist);
        loggedUser.setOrderInfo(orderInfo);

        Set<CartInfo> cartHist = new HashSet<CartInfo>();
        if (loggedUser.getCartHist().size()!=0) {
            cartHist.addAll(loggedUser.getCartHist());
        }
        if (loggedUser.getCartInfo() != null) {
            cartHist.add(loggedUser.getCartInfo());
        }
        loggedUser.setCartHist(cartHist);
        loggedUser.setCartInfo(null);

        userRepository.save(loggedUser);
    }

    public void orderUpdate (ShoppingCartEvent shoppingCartEvent) {

        LoggedUser loggedUser = userRepository.findOneByLogin(shoppingCartEvent.getUserInfo().getLogin());
        Set<Address> addressHist = new HashSet<Address>();
        if( !loggedUser.getBillingAddress().isEmpty()) {
            if (loggedUser.getBillingAddress().size() != 0) {
                addressHist.addAll(loggedUser.getBillingAddress());
            }
        }
        addressHist.add(((OrderEvent) shoppingCartEvent.getEventAttributes()).getHqAddress());
        loggedUser.setBillingAddress(addressHist);
        loggedUser.setBillingContactDetails(((OrderEvent) shoppingCartEvent.getEventAttributes()).getBillingContactDetails());
        userRepository.save(loggedUser);
    }


 //   public OrderInfo postOrder(PctlUserOrderModel orderModel, HttpServletRequest request) {

       /* LoggedUser user =  userRepository.findOneByLogin(userTokenExtract.getLoginFromToken(request.getHeader(AUTHTOKEN)));
        OrderInfo orderInfo = null;
        if(orderModel.getCartId()!=null && !orderModel.getCartId().isEmpty()) {
            orderInfo = new OrderInfo(orderModel.getOrderId(), orderModel.getCartId(), 0,orderModel.getOrderDate(),
                orderModel.getProductInfo());
        }else{
            //      orderInfo = new OrderInfo(orderModel.getOrderId(), user.getCartInfo().getShoppingCartId(), 0);
        }
        Set<OrderInfo> orderHist = new HashSet<OrderInfo>();


        if(user != null) {

            if (user.getOrderHist() != null) {
                orderHist.addAll(user.getOrderHist());
            }
            if (user.getOrderInfo() != null) {
                orderHist.add(user.getOrderInfo());
            }
            user.setOrderHist(orderHist);
            user.setOrderInfo(orderInfo);
            userRepository.save(user);

            return orderInfo;
        }

        return null;*/
  //  }


}
