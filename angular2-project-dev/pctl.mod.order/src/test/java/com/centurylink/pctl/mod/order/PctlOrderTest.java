package com.centurylink.pctl.mod.order;

import com.centurylink.pctl.mod.core.model.cart.ShoppingCart;
import com.centurylink.pctl.mod.core.model.order.OrderEvent;
import com.centurylink.pctl.mod.core.model.user.CartInfo;
import com.centurylink.pctl.mod.core.utils.Response;
import com.centurylink.pctl.mod.core.utils.StatusCode;
import com.centurylink.pctl.mod.order.domain.order.Order;
import com.centurylink.pctl.mod.order.domain.order.PctlOrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by veeraragavan.k on 17-11-2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class PctlOrderTest  {

    private static final Logger log = LoggerFactory.getLogger(PctlOrderTest.class);

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private PctlOrderService pctlOrderService;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${jwt.defaultTestUser}")
    private String defaultUser;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Before
    public void setup() {
        this.mockMvc =   MockMvcBuilders
            .webAppContextSetup(context)
            .apply(documentationConfiguration(this.restDocumentation))
            .alwaysDo(print())
            .build();
    }

    @Test
    public void orderInitForExistingCartIdTest() throws Exception {
        CartInfo cartInfo = new CartInfo("cartId1",1);
        MvcResult result = mockMvc.perform(post("/order/cart-checkout")
            .content(new ObjectMapper().writeValueAsString(cartInfo))
            .contentType(MediaType.APPLICATION_JSON))
            .andReturn();
        ObjectMapper responseObject = new ObjectMapper();
        Response response = responseObject.readValue(result.getResponse().getContentAsString(), Response.class);
        assertThat(StatusCode.E800.getCode().equals(response.getCode()));
        assertThat(StatusCode.E800.getMessage().equals(response.getMessage()));
        Assert.assertNotNull(response.getContent());
    }

    @Test
    public void orderInitForNewCartIdTest() throws Exception {
        String cartId = UUID.randomUUID().toString();
        CartInfo cartInfo = new CartInfo(cartId,1);
        MvcResult result = mockMvc.perform(post("/order/cart-checkout")
            .content(new ObjectMapper().writeValueAsString(cartInfo))
            .contentType(MediaType.APPLICATION_JSON))
            .andReturn();
        ObjectMapper responseObject = new ObjectMapper();
        Response response = responseObject.readValue(result.getResponse().getContentAsString(), Response.class);
        assertThat(StatusCode.E800.getCode().equals(response.getCode()));
        assertThat(StatusCode.E800.getMessage().equals(response.getMessage()));
        Assert.assertNotNull(response.getContent());
    }


    @Test
    public void orderInitEmptyTest() throws Exception {
        CartInfo cartInfo = new CartInfo("",1);;
        MvcResult result = mockMvc.perform(post("/order/cart-checkout")
            .content(new ObjectMapper().writeValueAsString(cartInfo))
            .contentType(MediaType.APPLICATION_JSON))
            .andReturn();
        ObjectMapper responseObject = new ObjectMapper();
        Response response = responseObject.readValue(result.getResponse().getContentAsString(), Response.class);
        assertThat(StatusCode.U400.getCode().equals(response.getCode()));
        assertThat(StatusCode.U400.getMessage().equals(response.getMessage()));
        Assert.assertNull(response.getContent());
    }

    @Test
    public void orderEventTest() throws Exception{
        String content = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/orderEventRequest.json").toURI())));
        this.mockMvc.perform(
            post("/order/events").content(content).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void orderEventNullTest() throws Exception {
        Response<String> response = pctlOrderService.orderEvent(null);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getCode());
        Assert.assertNotNull(response.getMessage());
        Assert.assertNotNull(response.getHttpStatus());
        Assert.assertNull(response.getContent());
        Assert.assertEquals(StatusCode.E400.getCode(), response.getCode());
    }

    @Test
    public void orderIdNullTest() throws Exception {
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setOrderId(null);
        Response<String> response = pctlOrderService.orderEvent(orderEvent);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getCode());
        Assert.assertNotNull(response.getMessage());
        Assert.assertNotNull(response.getHttpStatus());
        Assert.assertNull(response.getContent());
        Assert.assertEquals(StatusCode.E400.getCode(), response.getCode());
    }

    @Test
    public void getOrderSummaryTest() throws Exception {

        MvcResult mvcResult = this.mockMvc.perform(
            get("/order/summary/101112356"))
            .andExpect(status().isOk())
            .andReturn() ;
        ObjectMapper objectMapper = new ObjectMapper();
        Response<Order> orderResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Response.class);
        assertThat(StatusCode.U200.getCode().equals(orderResponse.getCode()));
        assertThat(StatusCode.U200.getMessage().equals(orderResponse.getMessage()));
    }
    // Added Test cases
    @Test
    public void inValidOrderIdTest() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
            get("/order/summary/101112356AA77bb"))
            .andReturn() ;
        ObjectMapper objectMapper = new ObjectMapper();
        Response<Order> orderResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Response.class);
        assertThat(StatusCode.E801.getCode().equals(orderResponse.getCode()));
        assertThat(StatusCode.E801.getMessage().equals(orderResponse.getMessage()));
    }

    @Test
    public void emptyOrderIdTest() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
            get("/order/summary/ "))
            .andReturn() ;
        ObjectMapper objectMapper = new ObjectMapper();
        Response<Order> orderResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Response.class);
        assertThat(StatusCode.U400.getCode().equals(orderResponse.getCode()));
        assertThat(StatusCode.U400.getMessage().equals(orderResponse.getMessage()));
    }

    @Test
     public void cartCheckoutNullTest() throws Exception {
        ShoppingCart shoppingCart = null;
        String orderId = null;
        Response<String> response = pctlOrderService.cartCheckout(shoppingCart, orderId);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getCode());
        Assert.assertNotNull(response.getMessage());
        Assert.assertNotNull(response.getHttpStatus());
        Assert.assertEquals(StatusCode.E400.getCode(), response.getCode());
    }

    @Test
    public void cartCheckoutEmptyTest() throws Exception {
        ShoppingCart shoppingCart = new ShoppingCart();
        String orderId = "";
        Response<String> response = pctlOrderService.cartCheckout(shoppingCart, orderId);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getCode());
        Assert.assertNotNull(response.getMessage());
        Assert.assertNotNull(response.getHttpStatus());
        Assert.assertEquals(StatusCode.E400.getCode(), response.getCode());
    }
}
