/*
package com.centurylink.pctl.mod.sfaint;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

*/
/**
 * Created by dakshinamoorthyd on 21-11-2016.
 *//*


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class PctlSfaintOPPTest {

    private static final Logger log = LoggerFactory.getLogger(PctlSfaintOPPTest.class);

    @Rule
    public JUnitRestDocumentation jUnitRestDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    private MockMvc mockMvc;

    @Autowired
    public WebApplicationContext context;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(documentationConfiguration(jUnitRestDocumentation))
            .alwaysDo(print())
            .build();
    }

     @Test
    public void createApplicationId() throws Exception{

      this.mockMvc.perform(
                get("/sfaint/newusersfacc"))
            .andExpect(status().isOk())
            .andDo(document("sfaint-acc-creation", preprocessRequest(
                prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("success").description("The sfaint success "),
                    fieldWithPath("message").description("The sfaint response message"),
                    fieldWithPath("accountId").description("The sfaint account id"),
                    fieldWithPath("uuid").description("The sfaint uuid")
                    )));

        log.info("sfaint test success...");
    }

}
*/
