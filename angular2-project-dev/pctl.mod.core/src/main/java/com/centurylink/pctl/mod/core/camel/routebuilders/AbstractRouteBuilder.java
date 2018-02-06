package com.centurylink.pctl.mod.core.camel.routebuilders;

import com.centurylink.pctl.mod.core.camel.CamelException;
import com.centurylink.pctl.mod.core.camel.config.CamelConfig;
import com.centurylink.pctl.mod.core.camel.config.CamelDigesterBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by s-arunkumar on 25-10-2016.
 */
@Configuration
@ConfigurationProperties(prefix = "camel")
public abstract class AbstractRouteBuilder extends RouteBuilder {


    private static final Logger logger = Logger
        .getLogger(AbstractRouteBuilder.class);

    private String configFilePath;

    @Bean
    public CamelConfig bindingConfig() throws CamelException {
        logger.info("Camel-biding9-config path: " + configFilePath);
        CamelDigesterBuilder builder = new CamelDigesterBuilder(configFilePath);
        CamelConfig mqConfig = builder.build();
        return mqConfig;
    }

    public String getConfigFilePath() {
        return configFilePath;
    }

    public void setConfigFilePath(String configFilePath) {
        this.configFilePath = configFilePath;
    }
}
