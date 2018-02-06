package com.centurylink.pctl.mod.cart;

import com.centurylink.pctl.mod.cart.controllers.rest.PctlCartRestController;
import com.centurylink.pctl.mod.cart.domain.ShoppingCart.PctlCartService;
import com.centurylink.pctl.mod.core.model.cart.CartEvent;
import com.centurylink.pctl.mod.core.model.cart.CartEventType;
import com.centurylink.pctl.mod.core.model.cart.ShoppingCart;
import com.centurylink.pctl.mod.core.utils.Response;
import com.centurylink.pctl.mod.core.utils.StatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class PctlCartTest {

    @Rule
    public JUnitRestDocumentation  restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${jwt.defaultTestUser}")
    private String defaultUser;

    @Value("${jwt.header}")
    private String tokenHeader;

   @Autowired
    private PctlCartService pctlCartService;


    @Before
    public void setup() {
        this.mockMvc =   MockMvcBuilders
            .webAppContextSetup(context)
            .apply(documentationConfiguration(this.restDocumentation))
            .alwaysDo(print())
            .build();
    }

    @Test
    public void postsCartEventTest() throws Exception
    {
       String content = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/cartEventRequest.json").toURI())));
       this.mockMvc.perform(
            post("/cart/events?cartId=cart1234567&cartEventType=ADD_ITEM").content(content).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
   }


    @Test
    public void getCartForNullCartId() throws Exception {
        CartEvent cartEvent = new CartEvent();
        cartEvent.setCartEventType(CartEventType.ADD_LOCATION);
        //boolean response = pctlCartService.getShoppingCart(null);
        assertEquals(null, pctlCartService.getShoppingCart(null));
    }

    @Test
    public void postShoppingCartList() throws Exception {

        this.mockMvc.perform(
            get("/cart/shopping-cart/cart1234567"))
            .andExpect(status().isOk())
            .andDo(document("shopping-cart",
                preprocessRequest(
                    prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("content.cartId").description("The CartId Id"),
                    fieldWithPath("content.lineItems[0].productId").description("The Product Id"),
                    fieldWithPath("content.lineItems[0].quantity").description("The Product Quantity"),
                    fieldWithPath("content.lineItems[0].productName").description("The Product Name"),
                    fieldWithPath("content.lineItems[0].productTemplateName").description("The Product TemplateName"),
                    fieldWithPath("content.lineItems[0].productTemplateId").description("The Product TemplateId"),
                    fieldWithPath("content.lineItems[0].unitPrice").description("The UnitPrice"),
                    fieldWithPath("content.lineItems[0].term").description("Term"),
                    fieldWithPath("content.lineItems[0].locations[0].serviceAddress.locationName").description("The ServiceAddress's LocationName"),
                    fieldWithPath("content.lineItems[0].locations[0].serviceAddress.street").description("The ServiceAddress's street"),
                    fieldWithPath("content.lineItems[0].locations[0].serviceAddress.city").description("The ServiceAddress's city"),
                    fieldWithPath("content.lineItems[0].locations[0].serviceAddress.addressLine").description("The ServiceAddress's Address Line 1"),
                    fieldWithPath("content.lineItems[0].locations[0].serviceAddress.country").description("The ServiceAddress's country"),
                    fieldWithPath("content.lineItems[0].locations[0].serviceAddress.state").description("The ServiceAddress's state"),
                    fieldWithPath("content.lineItems[0].locations[0].serviceAddress.zipCode").description("The ServiceAddress's zipCode"),
                    fieldWithPath("content.lineItems[0].locations[0].shippingAddress.locationName").description("The ShippingAddress's LocationName"),
                    fieldWithPath("content.lineItems[0].locations[0].shippingAddress.street").description("The ShippingAddress's street"),
                    fieldWithPath("content.lineItems[0].locations[0].shippingAddress.city").description("The ShippingAddress's city"),
                    fieldWithPath("content.lineItems[0].locations[0].shippingAddress.country").description("The ShippingAddress's country"),
                    fieldWithPath("content.lineItems[0].locations[0].shippingAddress.addressLine").description("The ShippingAddress's Address Line 1"),
                    fieldWithPath("content.lineItems[0].locations[0].shippingAddress.state").description("The ShippingAddress's state"),
                    fieldWithPath("content.lineItems[0].locations[0].shippingAddress.zipCode").description("The ShippingAddress's zipCode"),
                    fieldWithPath("content.lineItems[0].locations[0].serviceContact.email").description("The Contact Email"),
                    fieldWithPath("content.lineItems[0].locations[0].serviceContact.firstName").description("The Contact firstName"),
                    fieldWithPath("content.lineItems[0].locations[0].serviceContact.lastName").description("The Contact lastName"),
                    fieldWithPath("content.lineItems[0].locations[0].serviceContact.phoneNumber").description("The Contact phoneNumber")
                )));
    }

    @Test
    public void postInvalidCartEventTypeTest() throws Exception
    {
        String content = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/InvalidCartEventTypeRequest.json").toURI())));
        MvcResult result =  this.mockMvc.perform(
            post("/cart/events?cartId=cart1234567&cartEventType=ADD_ITEMs").content(content).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();
        ObjectMapper responseObject = new ObjectMapper();
        Response response = responseObject.readValue(result.getResponse().getContentAsString(), Response.class);
        assertThat(StatusCode.E704.getCode().equals(response.getCode()));
        assertThat(StatusCode.E704.getMessage().equals(response.getMessage()));
    }

    @Test
    public void getCartEmptyTest() throws Exception
    {
        //String content = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/InvalidCartEventTypeRequest.json").toURI())));
        MvcResult result =this.mockMvc.perform(
            get("/cart/shopping-cart/cart1234567")).andReturn();
        ObjectMapper responseObject = new ObjectMapper();
        Response response = responseObject.readValue(result.getResponse().getContentAsString(), Response.class);
        assertThat(StatusCode.E705.getCode().equals(response.getCode()));
        assertThat(StatusCode.E705.getMessage().equals(response.getMessage()));
    }

    @Test
    public void inValidCartIdTest() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
            get("/cart/shopping-cart/cart12345678 "))
            .andReturn() ;
        ObjectMapper objectMapper = new ObjectMapper();
        Response orderResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Response.class);
        assertThat(StatusCode.E705.getCode().equals(orderResponse.getCode()));
        assertThat(StatusCode.E705.getMessage().equals(orderResponse.getMessage()));
    }

    @Test
    public void getCheckOutCartTestInvalidId() throws Exception {
        PctlCartService cartService=new PctlCartService();
        ShoppingCart response = new PctlCartRestController(cartService).getCheckOutCart(null);
        Assert.assertNull(response);
    }


}









