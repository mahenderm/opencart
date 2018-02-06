



package com.centurylink.pctl.mod.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import java.nio.file.Files;
import java.nio.file.Paths;

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
@SpringBootTest(classes = PctlUserApplication.class )
@WebAppConfiguration
public class PctlUserTest {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;


    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private ObjectMapper objectMapper;
    private MockMvc mockmvc1;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(documentationConfiguration(this.restDocumentation))
            .alwaysDo(print())
            .build();
        this.mockmvc1 = MockMvcBuilders
            .webAppContextSetup(context).apply(documentationConfiguration(this.restDocumentation)).addFilters(springSecurityFilterChain)
            .defaultRequest(get("/auth/token/generate")).alwaysDo(print())
            .build();

    }

    @Test

    public void postToken() throws Exception {

        mockmvc1.perform(
            get("/auth/token/generate").header("username", "gmail@gmail.com"))
            .andExpect(status().isOk())
            .andDo(print())
            .andDo(document("Generated-Token",
                preprocessRequest(
                    prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("token").description("token"),
                    fieldWithPath("refreshToken").description("refreshToken")
                )));


    }

    @Test
    public void postsWithUser() throws Exception {
        String content = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/UserLoginRequest.json").toURI())));

        ResultActions auth = this.mockMvc.perform(
            post("/auth/user/init").content(content).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andDo(document("User-Init",
                preprocessRequest(
                    prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("content.id").description("Content ID"),
                    fieldWithPath("content.login").description("Login"),
                    fieldWithPath("content.firstName").description("First Name"),
                    fieldWithPath("content.lastName").description("Last Name"),

                    fieldWithPath("content.token").description("authToken"),
                    fieldWithPath("content.email").description("Email"),
                    fieldWithPath("content.activated").description("User Activation Detail"),
                    fieldWithPath("content.authorities[].name").description("Role"),
                    fieldWithPath("content.loggedIn").description("loggedIn"),
                    fieldWithPath("content.status").description("status"),

                    fieldWithPath("content.cartInfo.shoppingCartId").description("current shoppingCartId"),
                    //    fieldWithPath("content.cartInfo.cartState").description("current cartState"),

                    fieldWithPath("content.orderInfo.orderId").description("current orderId"),
                    fieldWithPath("content.orderInfo.cartId").description("current cartId"),
                    fieldWithPath("content.orderInfo.state").description("current state"),
                    fieldWithPath("content.orderInfo.orderDate").description("orderDate"),
                    fieldWithPath("content.orderInfo.productInfo[].product").description("product"),
                    fieldWithPath("content.orderInfo.productInfo[].locationSize").description("location Count"),




                    fieldWithPath("message").description("message"),
                    fieldWithPath("code").description("code")

                )));

    }


    public void postsWithUsererror() throws Exception {
        String content = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/UserLoginRequeste.json").toURI())));

        ResultActions auth = this.mockMvc.perform(
            post("/auth/user/init").content(content).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(200)) ;


    }



    @Test
    public void postsUserCart() throws Exception {

        String content = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/UserCartInit.json").toURI())));

        ResultActions auth = this.mockMvc.perform(
            post("/auth/user/cart/init").content(content).header("authToken",
                "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0c2h1a3VAZ21haWwuY29tIn0.OzyX9Qt872EfjOvC5W-J1D7uQEW6lMlSK85R-9F_esyrvFbYIrCLcmkJLqmwcBama0-g3fRy0g_X1XMQevymfg")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andDo(document("Cart-Init",
                preprocessRequest(
                    prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("content.shoppingCartId").description("Content ID"),
                    fieldWithPath("message").description("message"),
                    fieldWithPath("code").description("code")
                )));
    }

    @Test
    public void postsUserCartUsererror() throws Exception {

        String content = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/UserCartInit.json").toURI())));

        ResultActions auth = this.mockMvc.perform(
            post("/auth/user/cart/init").content(content).header("authToken",
                "")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(500));
    }


    @Test
    public void postsUserOrder() throws Exception {

        String content = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/UserOrderUpdate.json").toURI())));

        ResultActions auth = this.mockMvc.perform(
            post("/auth/user/order/update").content(content).header("authToken",
                "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0c2h1a3VAZ21haWwuY29tIn0.OzyX9Qt872EfjOvC5W-J1D7uQEW6lMlSK85R-9F_esyrvFbYIrCLcmkJLqmwcBama0-g3fRy0g_X1XMQevymfg")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andDo(document("Order-Update",
                preprocessRequest(
                    prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("content.orderId").description("Order ID"),
                    fieldWithPath("content.cartId").description("Cart ID"),
                    fieldWithPath("content.state").description("State"),
                    fieldWithPath("content.orderDate").description("Order Date"),
                    fieldWithPath("content.productInfo[].product").description("Product"),
                    fieldWithPath("content.productInfo[].locationSize").description("Location Count"),
                   /* fieldWithPath("content.orderInfo.orderId").description("current orderId"),
                    fieldWithPath("content.orderInfo.cartId").description("current cartId"),
                    fieldWithPath("content.orderInfo.state").description("current state"),
                    fieldWithPath("content.orderInfo.orderDate").description("orderDate"),
                    fieldWithPath("content.orderInfo.productInfo[].product").description("product"),
                    fieldWithPath("content.orderInfo.productInfo[].locationSize").description("location Count"),*/
                    fieldWithPath("message").description("message"),
                    fieldWithPath("code").description("code")
                )));
    }



    @Test
    public void postsUserOrderUserError() throws Exception {

        String content = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/UserOrderUpdate.json").toURI())));

        ResultActions auth = this.mockMvc.perform(
            post("/auth/user/order/update").content(content).header("authToken",
                "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSJ9.RE4jyy6dWhsmu7_Fx0-YvwsuzBMVFM-QIWuMLivMcMe2s2KASZK-lrLyVkEgjejRgtpCdlaFA_RtjzZf7yoFEA")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(500));
    }

    @Test
    public void postsUserOrderUserE() throws Exception {

        String content = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/UserOrderUpdate.json").toURI())));

        ResultActions auth = this.mockMvc.perform(
            post("/auth/user/order/update").content(content).header("authToken",
                "")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(500));
    }

    @Test
    public void getUser() throws Exception {
        ResultActions auth = this.mockMvc.perform(
            get("/auth/user/me").header("authToken",
                "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0c2h1a3VAZ21haWwuY29tIn0.OzyX9Qt872EfjOvC5W-J1D7uQEW6lMlSK85R-9F_esyrvFbYIrCLcmkJLqmwcBama0-g3fRy0g_X1XMQevymfg"))
            .andExpect(status().isOk()).andDo(document("User-Me",
                preprocessRequest(
                    prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("content.id").description("Content ID"),
                    fieldWithPath("content.login").description("Login"),
                    fieldWithPath("content.firstName").description("First Name"),
                    fieldWithPath("content.lastName").description("Last Name"),
                    fieldWithPath("content.token").description("authToken"),
                    fieldWithPath("content.email").description("Email"),
                    fieldWithPath("content.activated").description("User Activation Detail"),
                    fieldWithPath("content.authorities[].name").description("Role"),
                    fieldWithPath("content.loggedIn").description("loggedIn"),
                    fieldWithPath("content.cartInfo.shoppingCartId").description("current shoppingCartId"),
                    fieldWithPath("content.status").description("status"),
                    fieldWithPath("content.orderInfo.orderId").description("current orderId"),
                    fieldWithPath("content.orderInfo.cartId").description("current cartId"),

                    fieldWithPath("content.orderInfo.state").description("current state"),
                    fieldWithPath("content.orderInfo.orderDate").description("orderDate"),
                    fieldWithPath("content.orderInfo.productInfo[].product").description("product"),
                    fieldWithPath("content.orderInfo.productInfo[].locationSize").description("location Count"),
                    fieldWithPath("message").description("message"),
                    fieldWithPath("code").description("code")

                )));

    }

    public void getFind() throws Exception {
        ResultActions auth = this.mockMvc.perform(
            get("/auth/user/find").header("login",
                "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0c2h1a3VAZ21haWwuY29tIn0.OzyX9Qt872EfjOvC5W-J1D7uQEW6lMlSK85R-9F_esyrvFbYIrCLcmkJLqmwcBama0-g3fRy0g_X1XMQevymfg"))
            .andExpect(status().isOk()).andDo(document("Find-User",
                preprocessRequest(
                    prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("content.id").description("Content ID"),
                    fieldWithPath("content.login").description("Login"),
                    fieldWithPath("content.firstName").description("First Name"),
                    fieldWithPath("content.lastName").description("Last Name"),
                    fieldWithPath("content.token").description("authToken"),
                    fieldWithPath("content.email").description("Email"),
                    fieldWithPath("content.activated").description("User Activation Detail"),
                    fieldWithPath("content.authorities[].name").description("Role"),
                    fieldWithPath("content.loggedIn").description("loggedIn"),
                    fieldWithPath("content.cartInfo.shoppingCartId").description("current shoppingCartId"),
                    fieldWithPath("content.orderInfo.orderId").description("current orderId"),
                    fieldWithPath("content.orderInfo.cartId").description("current cartId"),
                    fieldWithPath("content.orderInfo.state").description("current state"),
                    fieldWithPath("content.orderInfo.orderDate").description("orderDate"),
                    fieldWithPath("content.orderInfo.productInfo[].product").description("product"),
                    fieldWithPath("content.orderInfo.productInfo[].locationSize").description("location Count"),
                    fieldWithPath("content.status").description("status"),
                    fieldWithPath("message").description("message"),
                    fieldWithPath("code").description("code")

                )));

    }



    @Test
    public void getLoggedUser() throws Exception {
        ResultActions auth = this.mockMvc.perform(
            get("/auth/user/logged-user").header("authToken",
                "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0c2h1a3VAZ21haWwuY29tIn0.OzyX9Qt872EfjOvC5W-J1D7uQEW6lMlSK85R-9F_esyrvFbYIrCLcmkJLqmwcBama0-g3fRy0g_X1XMQevymfg"))
            .andExpect(status().isOk()).andDo(document("Logged-User",
                preprocessRequest(
                    prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("id").description("Content ID"),
                    fieldWithPath("login").description("Login"),
                    fieldWithPath("firstName").description("First Name"),
                    fieldWithPath("lastName").description("Last Name"),
                    fieldWithPath("token").description("authToken"),
                    fieldWithPath("email").description("Email"),
                    fieldWithPath("activated").description("User Activation Detail"),
                    fieldWithPath("authorities[].name").description("Role"),
                    fieldWithPath("loggedIn").description("loggedIn"),
                    fieldWithPath("cartInfo.shoppingCartId").description("current shoppingCartId"),
                    fieldWithPath("orderInfo.orderId").description("current orderId"),
                    fieldWithPath("orderInfo.cartId").description("current cartId"),
                    fieldWithPath("orderInfo.state").description("current state"),
                    fieldWithPath("orderInfo.orderDate").description("orderDate"),
                    fieldWithPath("orderInfo.productInfo[].product").description("product"),
                    fieldWithPath("orderInfo.productInfo[].locationSize").description("location Count"),
                    fieldWithPath("status").description("status")

                )));
    }


}





