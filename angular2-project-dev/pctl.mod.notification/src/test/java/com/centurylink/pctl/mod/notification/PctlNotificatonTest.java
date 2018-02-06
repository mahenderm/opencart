
package com.centurylink.pctl.mod.notification;


import com.centurylink.pctl.mod.core.utils.Response;
import com.centurylink.pctl.mod.core.utils.StatusCode;
import com.centurylink.pctl.mod.notification.domain.EmailService;
import com.centurylink.pctl.mod.notification.domain.PctlNotificationRepository;
import com.centurylink.pctl.mod.notification.domain.PctlNotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author srinivas
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class PctlNotificatonTest {
    private static final Logger log = LoggerFactory.getLogger(PctlNotificatonTest.class);
    @Autowired
    private PctlNotificationService pctlApiNotificationService;
    @Autowired
    private PctlNotificationRepository notificationRepository;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
    @Autowired
    private EmailService mailClient;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(documentationConfiguration(this.restDocumentation))
//            .alwaysDo(print())
                //.alwaysDo(restHandler)
            .build();
    }
    @Test
    public void postsWithUser() throws Exception {

        this.mockMvc.perform(
            get("/notification"))
            .andExpect(status().isOk())
            .andDo(document("list-notification",
                preprocessRequest(
                    prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("_embedded.notification[0].subject").description("The Notifiacation Subject Details "),
                    fieldWithPath("_embedded.notification[0].message").description("The Notifiacation Message Details "),
                    fieldWithPath("_embedded.notification[0].email").description("The Notifiacation Email Details "),
                    fieldWithPath("_embedded.notification[0].fromAddress").description("The Notifiacation From Address Details "),
                    fieldWithPath("_embedded.notification[0].deliveryType").description("The Notifiacation DeliveryType Details "),
                    fieldWithPath("_embedded.notification[0].deliveryDate").description("The Notifiacation DeliveryDate Details "),
                    fieldWithPath("_embedded.notification[0].entityId").description("The Notifiacation EntityID Details "),
                    fieldWithPath("_embedded.notification[0].entityType").description("The Notifiacation EntityType Details "),
                    fieldWithPath("_embedded.notification[0].toAddress").description("The Notifiacation ToAddress Details "),

                    fieldWithPath("_links").description("The Notification Links details "),

                    fieldWithPath("_embedded.notification[0]_links").description("The Notification Links details "),
                    fieldWithPath("page").description("The Notification Page  details ")

                )));
    }


    @Test
    public void postsWithUserEmail() throws Exception {

        this.mockMvc.perform(
            get("/notification/email"))
            .andExpect(status().isOk())
            .andDo(document("list-emailnotification",
                preprocessRequest(
                    prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("content.response[0].subject").description("The Notifiacation Subject Details "),
                    fieldWithPath("content.response[0]._id").description("The Notifiacation _id "),
                    fieldWithPath("content.response[0].message").description("The Notifiacation message Details "),
                    fieldWithPath("content.response[0].email").description("The Notifiacation email Details "),
                    fieldWithPath("content.response[0].fromAddress").description("The Notifiacation fromAddress Details "),
                    fieldWithPath("content.response[0].toAddress").description("The Notifiacation toAddress Details "),
                    fieldWithPath("content.response[0].deliveryType").description("The Notifiacation deliveryType Details "),
                    fieldWithPath("content.response[0].deliveryDate").description("The Notifiacation deliveryDate Details "),
                    fieldWithPath("content.response[0].entityId").description("The Notifiacation entityId Details "),
                    fieldWithPath("content.response[0].entityType").description("The Notifiacation entityType Details "),
                    fieldWithPath("message").description("The Notifiacation message Details "),
                    fieldWithPath("content").description("The Notifiacation Subject Details "),

                    fieldWithPath("code").description("The Notifiacation Success Code Details ")

                )));
    }

    @Test
    public void postsWithUserMessage() throws Exception {

        this.mockMvc.perform(
            get("/notification/message"))
            .andExpect(status().isOk())
            .andDo(document("list-messagenotification",
                preprocessRequest(
                    prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("content").description("The Notifiacation Subject Details "),

                    fieldWithPath("content.response[0].subject").description("The Notifiacation Subject Details "),
                    fieldWithPath("content.response[0]._id").description("The Notifiacation _id "),
                    fieldWithPath("content.response[0].message").description("The Notifiacation message Details "),
                    fieldWithPath("content.response[0].email").description("The Notifiacation email Details "),
                    fieldWithPath("content.response[0].fromAddress").description("The Notifiacation fromAddress Details "),
                    fieldWithPath("content.response[0].toAddress").description("The Notifiacation toAddress Details "),
                    fieldWithPath("content.response[0].deliveryType").description("The Notifiacation deliveryType Details "),
                    fieldWithPath("content.response[0].deliveryDate").description("The Notifiacation deliveryDate Details "),
                    fieldWithPath("content.response[0].entityId").description("The Notifiacation entityId Details "),
                    fieldWithPath("content.response[0].entityType").description("The Notifiacation entityType Details "),
                    fieldWithPath("message").description("The Notifiacation message Details "),
                    fieldWithPath("code").description("The Notifiacation Success Code Details ")

                )));
    }


    @Test
    public void postsWithUserInvalid() throws Exception {

        this.mockMvc.perform(
            get("/notification/messagesazd"))
            .andExpect(status().isBadRequest())
            .andDo(document("list-notificationinvalid",
                preprocessRequest(
                    prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("message").description("The Notifiacation message Details "),
                    fieldWithPath("code").description("The Notifiacation Success Code Details ")

                )));
    }


  /* @Test
    @WithMockUser(username = "jbeginsamuel@gmail.com", roles = {"USER"})
    public void postsWithUser() throws Exception {
          MvcResult result = this.mockMvc.perform(
            get("/notification/"))
            .andExpect(status().isOk())
            .andReturn();

        log.info("Printing Response ..{}", result.getResponse().getContentAsString());
    }*/




    @Test
    @WithMockUser(username = "jbeginsamuel@gmail.com", roles = {"USER"})
    public void notificationEmailCheck() throws Exception {
        MvcResult result = this.mockMvc.perform(
            get("/notification/email"))
            .andReturn();
        ObjectMapper responseObject = new ObjectMapper();
        Response response = responseObject.readValue(result.getResponse().getContentAsString(), Response.class);
        log.info("Result :.." + response.getContent().toString());

//        assertThat(response.getCode()).isEqualTo(StatusCode.E200.getCode());
        assertThat(StatusCode.U200.getCode().equals(response.getCode()));
        assertThat(response.getMessage()).isEqualTo("Success");
        Assert.assertNotNull(response.getContent());
    }

    @Test
    @WithMockUser(username = "jbeginsamuel@gmail.com", roles = {"USER"})
    public void notificationEmailCheckInvalid() throws Exception {
        MvcResult result = this.mockMvc.perform(
            get("/notification/FGHFHGF"))
            .andReturn();
        ObjectMapper responseObject = new ObjectMapper();
        Response response = responseObject.readValue(result.getResponse().getContentAsString(), Response.class);
        assertThat(StatusCode.N401.getCode().equals(response.getCode()));
    }

    @Test
    @WithMockUser(username = "jbeginsamuel@gmail.com", roles = {"USER"})
    public void notificationEmailCheckInvalidByRandomNumbers() throws Exception {
        MvcResult result = this.mockMvc.perform(
            get("/notification/" + UUID.randomUUID()) )
            .andReturn();
        ObjectMapper responseObject = new ObjectMapper();
        Response response = responseObject.readValue(result.getResponse().getContentAsString(), Response.class);
        assertThat(StatusCode.N401.getCode().equals(response.getCode()));
    }
    @Test
    @WithMockUser(username = "jbeginsamuel@gmail.com", roles = {"USER"})
    public void notificationMessageCheckInvalidByRandomNumbers() throws Exception {
        MvcResult result = this.mockMvc.perform(
            get("/notification/" + UUID.randomUUID()) )
            .andReturn();
        ObjectMapper responseObject = new ObjectMapper();
        Response response = responseObject.readValue(result.getResponse().getContentAsString(), Response.class);
        assertThat(StatusCode.N401.getCode().equals(response.getCode()));
    }

    @Test
    @WithMockUser(username = "jbeginsamuel@gmail.com", roles = {"USER"})
    public void notificationMessageCheckVaild() throws Exception {
        MvcResult result = this.mockMvc.perform(
            get("/notification/message") )
            .andReturn();
        ObjectMapper responseObject = new ObjectMapper();
        Response response = responseObject.readValue(result.getResponse().getContentAsString(), Response.class);
//        assertThat(response.getCode()).isEqualTo(StatusCode.E200.getCode());
        assertThat(StatusCode.U200.getCode().equals(response.getCode()));
        assertThat(response.getMessage()).isEqualTo("Success");
        Assert.assertNotNull(response.getContent());

    }






 /*   @Test
    @WithMockUser(username = "jbeginsamuel@gmail.com", roles = {"USER"})
    public void notificationEmailSend() throws Exception {
        MvcResult result = this.mockMvc.perform(
            get("/notification/send"))
            .andReturn();
        ObjectMapper responseObject = new ObjectMapper();
        Response response = responseObject.readValue(result.getResponse().getContentAsString(), Response.class);
        log.info("Result :.." + response.getContent().toString());

//        assertThat(response.getCode()).isEqualTo(StatusCode.E200.getCode());
        assertThat(StatusCode.U200.getCode().equals(response.getCode()));
        assertThat(response.getMessage()).isEqualTo("SUCCESS");
        Assert.assertNotNull(response.getContent());
    }*/
/*
    @Test
    @WithMockUser(username = "jbeginsamuel@gmail.com", roles = {"USER"})
    public void notificationMessageCheckValidForPost() throws Exception {
        MvcResult result = this.mockMvc.perform(
            post("/notification/post/message").contentType(MediaType.APPLICATION_JSON)
        )
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();
        ObjectMapper responseObject = new ObjectMapper();
        Response response = responseObject.readValue(result.getResponse().getContentAsString(), Response.class);
        System.out.print("response.getContent()" + response.getContent());
        Assert.assertNotNull(response.getContent());
//        assertThat(response.getCode()).isEqualTo(StatusCode.E200.getCode());
        assertThat(StatusCode.E200.getCode().equals(response.getCode()));
        assertThat(response.getMessage()).isEqualTo("Success");
    }



    @Test
    @WithMockUser(username = "jbeginsamuel@gmail.com", roles = {"USER"})
    public void notificationMessageCheckInValidForPost() throws Exception {
        MvcResult result = this.mockMvc.perform(
            post("/notification/post/messageasdf").contentType(MediaType.APPLICATION_JSON)
        )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andReturn();
        ObjectMapper responseObject = new ObjectMapper();
        Response response = responseObject.readValue(result.getResponse().getContentAsString(), Response.class);
        System.out.print("response.getContent()" + response.getContent());
        Assert.assertNull(response.getContent());
//        assertThat(response.getCode()).isEqualTo(StatusCode.E200.getCode());
        assertThat(StatusCode.N401.getCode().equals(response.getCode()));
        log.info("response.getMessage() "+response.getMessage());
//        assertThat(response.getMessage()).isEqualTo("Success");


    }*/

  /* // @Test
    @WithMockUser(username = "jbeginsamuel@gmail.com", roles = {"USER"})
    public void testAddress() throws Exception {
        MvcResult result = this.mockMvc.perform(
            //header("Authorization", base64ForTestUser).
            post("/address/validation/").contentType(MediaType.APPLICATION_JSON)
                .content(""))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andReturn();
        String content = result.getResponse().getContentAsString();
        System.out.print("contetn " + content);

    }*/


   /* private GreenMail smtpServer;


    @Test
    public void shouldSendMail() throws Exception {
        //given
        String templateName = "srinivasp475@gmail.com.com";
        String contentone = "Test message content";
        //when
        mailClient.sendHTMLEmail(templateName, contentone);
        //then
        assertReceivedMessageContains(contentone);
    }

    private void assertReceivedMessageContains(String expected) throws IOException, MessagingException {
        MimeMessage[] receivedMessages = smtpServer.getReceivedMessages();
        assertEquals(1, receivedMessages.length);

        String content = "<span>" + contentone + "</span>";
        assertTrue(content.contains(expected));
    }*/
}



