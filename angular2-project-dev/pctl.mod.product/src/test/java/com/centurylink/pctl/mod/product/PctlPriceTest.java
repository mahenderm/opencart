package com.centurylink.pctl.mod.product;

import com.centurylink.pctl.mod.product.domain.product.PctlProductService;
import com.centurylink.pctl.mod.product.domain.product.PriceRepository;
import com.centurylink.pctl.mod.product.domain.product.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
*
 * Created by pulapakas on 02-11-2016.
*/


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class PctlPriceTest {
    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Autowired
    private PctlProductService pctlProductService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PriceRepository priceRepository;


    @Autowired
    private WebApplicationContext context;

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
    public void postsWithUser() throws Exception {

        this.mockMvc.perform(
            get("/products/price/"))
            .andExpect(status().isOk())
            .andDo(document("list-price",
                preprocessRequest(
                    prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("[0].securityPkg").description("The Price Security Package details "),
                    fieldWithPath("[0].serviceVendor").description("The price Service Vendor  name"),
                   fieldWithPath("[0].tier1Support").description("The Price Tier1Support"),
                    fieldWithPath("[0].serviceTransport").description("The Price service transport"),
                    fieldWithPath("[0].variantId").description("The Price variant details"),

                    fieldWithPath("[0].sku").description("The Price sku"),
                    fieldWithPath("[0].productId").description("The ProductId price details"),
                    fieldWithPath("[0].displayPosition").description("The Price DisplayPositions"),
                    fieldWithPath("[0].haCpe").description("The Price haCpe details"),
                    fieldWithPath("[0].name").description("The Price name details"),
                    fieldWithPath("[0].servicePlan").description("The Price Service Plan details"),
                    fieldWithPath("[0].standardCpe").description("The Price standardCpe details"),
                    fieldWithPath("[0]._id").description("The price Unique ' ID"),
                    fieldWithPath("[0].currency").description("The Currency details"),
                    fieldWithPath("[0].priceList").description("The PriceList details"),

                    fieldWithPath("[0].descriptionHtml").description("The Price descriptionHtml details"),
                    fieldWithPath("[0].haServiceVendor").description("The Price haServiceVendor details"),
                    fieldWithPath("[0].haSecurityPkg").description("The Price haSecurityPackage details"),
                    fieldWithPath("[0].descriptionHtml").description("The Price descriptionHtml details"),
                    fieldWithPath("[0].haServicePlan").description("The Price haServicePlan details"),
                    fieldWithPath("[0].haServiceTransport").description("The Price haServiceTransport details"),
                    fieldWithPath("[0].haServicePlan").description("The Price haServicePlan details")






                    )));
    }
}
