

package com.centurylink.pctl.mod.address;


import com.centurylink.pctl.mod.address.domain.address.AddressType;
import com.centurylink.pctl.mod.address.domain.address.PctlAddressService;
import com.centurylink.pctl.mod.address.domain.address.PctlAddressServiceImpl;
import com.centurylink.pctl.mod.address.domain.camel.CivicAddress;
import com.centurylink.pctl.mod.core.model.address.Address;
import com.centurylink.pctl.mod.core.utils.Response;
import com.centurylink.pctl.mod.core.utils.StatusCode;
import com.centurylink.pctl.mod.core.utils.ValidateUtils;
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
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This class PctlApiAddressTest is for test cases
 *
 * @author Haribabu.ka
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class PctlAddressTest {

    private static final Logger log = LoggerFactory.getLogger(PctlAddressTest.class);
    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
    @Autowired
    private PctlAddressService pctlAddressService;
    @Autowired
    private PctlAddressServiceImpl pctlAddressServiceImpl;
    private ValidateUtils validateUtils;
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Test
    public void validateEmptyObject() {
        Response<Address> respo = pctlAddressService.validate(new Address(), AddressType.CIVIC);
        Assert.assertNotNull(respo);
        Assert.assertNotNull(respo.getContent());
        Assert.assertNotNull(respo.getCode());
        Assert.assertNotNull(respo.getMessage());
        Assert.assertNotNull(respo.getHttpStatus());
        Assert.assertEquals(StatusCode.E400.getCode(), respo.getCode());
    }

    @Test
    public void validateTypeNull() {
        Address address = new Address();
        Response<Address> respo = pctlAddressService.validate(address, null);
        Assert.assertNotNull(respo);
        Assert.assertNotNull(respo.getCode());
        Assert.assertNotNull(respo.getMessage());
        Assert.assertNotNull(respo.getHttpStatus());
        Assert.assertEquals(StatusCode.E400.getCode(), respo.getCode());
    }

    @Test
    public void validateNullAddress() {
        Response<Address> respo = pctlAddressService.validate(null, AddressType.CIVIC);
        Assert.assertNotNull(respo);
        Assert.assertNotNull(respo.getCode());
        Assert.assertNotNull(respo.getMessage());
        Assert.assertNotNull(respo.getHttpStatus());
        Assert.assertEquals(StatusCode.E400.getCode(), respo.getCode());
    }

    @Test
    public void validateNull() {

        Response<Address> respo = pctlAddressService.validate(null, null);
        Assert.assertNotNull(respo);
        Assert.assertNotNull(respo.getCode());
        Assert.assertNotNull(respo.getMessage());
        Assert.assertNotNull(respo.getHttpStatus());
        Assert.assertEquals(StatusCode.E400.getCode(), respo.getCode());
    }

    @Test
    public void validateCorrectAdd() {
        Address address = new Address();
        Response<Address> respo = pctlAddressService.validate(address, AddressType.CIVIC);
        Assert.assertNotNull(respo);
        Assert.assertNotNull(respo.getContent());
        Assert.assertNotNull(respo.getCode());
        Assert.assertNotNull(respo.getMessage());
        Assert.assertNotNull(respo.getHttpStatus());
        Assert.assertEquals(StatusCode.E400.getCode(), respo.getCode());
    }


    @Test
    public void generateFieldStatusNullResponse() throws Exception {
        Address address = new Address();
        address.setAddressLine("abc");
        address.setCity("abc");
        address.setState("abc");
        address.setZipCode("80126");

        CivicAddress addressResponse = new CivicAddress();
        addressResponse.setStreetAddress("abc");
        addressResponse.setLocality("abc");
        addressResponse.setStateOrProvince("abc");
        addressResponse.setPostCode("80126");

        Address fieldStatus = pctlAddressServiceImpl.generateFieldStatus(address, null);
        Assert.assertNotNull(fieldStatus);
    }

    @Test
    public void generateFieldStatusAddressNull() throws Exception {

        CivicAddress addressResponse = new CivicAddress();
        addressResponse.setStreetAddress("abc");
        addressResponse.setLocality("abc");
        addressResponse.setStateOrProvince("abc");
        addressResponse.setPostCode("80126");

        Address fieldStatus = pctlAddressServiceImpl.generateFieldStatus(null, addressResponse);
        Assert.assertNotNull(fieldStatus);
    }

    @Test
    public void generateFieldStatus() throws Exception {
        Address address = new Address();
        address.setAddressLine("abc");
        address.setCity("abc");
        address.setState("abc");
        address.setZipCode("80126");

        CivicAddress addressResponse = new CivicAddress();
        addressResponse.setStreetAddress("abc");
        addressResponse.setLocality("abc");
        addressResponse.setStateOrProvince("abc");
        addressResponse.setPostCode("80126");

        Address fieldStatus = pctlAddressServiceImpl.generateFieldStatus(address, addressResponse);
        Assert.assertNotNull(fieldStatus);
    }

    @Test
    public void generateFieldStatusNull() throws Exception {
        String fieldStatus = null;
        fieldStatus = pctlAddressServiceImpl.getAddressFieldStatus(null, null);
        Assert.assertNotNull(fieldStatus);

    }

    @Test
    public void generateFieldStatusNullRes() throws Exception {
        String fieldStatus = null;
        fieldStatus = pctlAddressServiceImpl.getAddressFieldStatus("abc", null);
        Assert.assertNotNull(fieldStatus);

    }

    @Test
    public void generateFieldStatusNullReq() throws Exception {
        String fieldStatus = null;
        fieldStatus = pctlAddressServiceImpl.getAddressFieldStatus(null, "abd");
        Assert.assertNotNull(fieldStatus);

    }

    @Test
    public void generateFieldStatusNotNull() throws Exception {
        String fieldStatus = null;
        fieldStatus = pctlAddressServiceImpl.getAddressFieldStatus("abc", "abd");
        Assert.assertNotNull(fieldStatus);

    }

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(documentationConfiguration(this.restDocumentation))
            .build();
    }


    @Test
    @WithMockUser(username = "jbeginsamuel@gmail.com", roles = {"USER"})
    public void civicAddress() throws Exception {
        String content = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/correctAddressRequest.json").toURI())));
        MvcResult result = this.mockMvc.perform(
            post("/address/validation/civic").content(content).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();
        ObjectMapper responseObject = new ObjectMapper();
        Response<Address> response = responseObject.readValue(result.getResponse().getContentAsString(), Response.class);
        assertThat(StatusCode.U200.getCode().equals(response.getCode()));
        assertThat(StatusCode.U200.getMessage().equals(response.getMessage()));
    }

    @Test
    @WithMockUser(username = "jbeginsamuel@gmail.com", roles = {"USER"})
    public void postalAddress() throws Exception {
        String content = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/correctAddressRequest.json").toURI())));
        MvcResult result = this.mockMvc.perform(
            post("/address/validation/postal").content(content).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();
        ObjectMapper responseObject = new ObjectMapper();
        Response<Address> response = responseObject.readValue(result.getResponse().getContentAsString(), Response.class);
        assertThat(StatusCode.U200.getCode().equals(response.getCode()));
        assertThat(StatusCode.U200.getMessage().equals(response.getMessage()));
    }

    @Test
    @WithMockUser(username = "jbeginsamuel@gmail.com", roles = {"USER"})
    public void validateTypeParamNotProvided() throws Exception {
        String content = new String("{}");
        MvcResult result = this.mockMvc.perform(
            post("/address/validation/null").content(content).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andReturn();
        ObjectMapper responseObject = new ObjectMapper();
        Response<Address> response = responseObject.readValue(result.getResponse().getContentAsString(), Response.class);
        assertThat(StatusCode.E400.getCode().equals(response.getCode()));
        assertThat(StatusCode.E400.getMessage().equals(response.getMessage()));
    }
    /*@Test
    @WithMockUser(username = "jbeginsamuel@gmail.com", roles = {"USER"})
    public void invalidCivicAddress() throws Exception {
        String content = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/invalidAddressRequest.json").toURI())));
        MvcResult result = this.mockMvc.perform(
            post("/address/validation/civic").content(content).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andReturn();
        ObjectMapper responseObject = new ObjectMapper();
        Response<Address> response = responseObject.readValue(result.getResponse().getContentAsString(), Response.class);
        assertThat(StatusCode.C401.getCode().equals(response.getCode()));
        assertThat(StatusCode.C401.getMessage().equals(response.getMessage()));
    }

    @Test
    @WithMockUser(username = "jbeginsamuel@gmail.com", roles = {"USER"})
    public void invalidPostalAddress() throws Exception {
        String content = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/invalidAddressRequest.json").toURI())));
        MvcResult result = this.mockMvc.perform(
            post("/address/validation/postal").content(content).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andDo(document("invalidPostalAddress",
                preprocessRequest(
                    prettyPrint()),
                responseFields(
                    fieldWithPath("message").description("Postal address validation API Service Status Message"),
                    fieldWithPath("code").description("Postal address validation API Service Status Code")
                )))
            .andReturn();
        ObjectMapper responseObject = new ObjectMapper();
        Response<Address> response = responseObject.readValue(result.getResponse().getContentAsString(), Response.class);
        assertThat(StatusCode.C402.getCode().equals(response.getCode()));
        assertThat(StatusCode.C402.getMessage().equals(response.getMessage()));
    }

    @Test
    @WithMockUser(username = "jbeginsamuel@gmail.com", roles = {"USER"})
    public void invalidRequestUrl() throws Exception {
        String content = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/correctAddressRequest.json").toURI())));
        MvcResult result = this.mockMvc.perform(
            post("/address/validation/post").content(content).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andDo(document("invalidRequestedUrl",
                preprocessRequest(
                    prettyPrint()),
                responseFields(
                    fieldWithPath("message").description("invalid Requested url for address validation API Service Status Message"),
                    fieldWithPath("code").description("invalid Requested url for address validation API Service Status Code")
                )))
            .andReturn();
        ObjectMapper responseObject = new ObjectMapper();
        Response<Address> response = responseObject.readValue(result.getResponse().getContentAsString(), Response.class);
        assertThat(StatusCode.E400.getCode().equals(response.getCode()));
        assertThat(StatusCode.E400.getMessage().equals(response.getMessage()));
    }
*/


}




