package com.centurylink.pctl.mod.product.config;


import com.centurylink.pctl.mod.core.config.AbstractDatabaseConfiguration;
import com.centurylink.pctl.mod.product.constants.PctlModProductConst;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.mongeez.Mongeez;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Constants;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import java.util.ArrayList;
import java.util.List;

/**
 * DatabaseConfiguration class contains configuration for Database of product modules
 * <br> configured with mongeez and reads from master.xml file as its input
 * @author Begin
 */
@Configuration
@EnableMongoRepositories("com.centurylink.pctl.mod.product")
@Import(value = MongoAutoConfiguration.class)
//@EnableMongoAuditing(auditorAwareRef = "springSecurityAuditorAware")
public class PctlProductDatabseCfg extends AbstractDatabaseConfiguration {

    private final Logger log = LoggerFactory.getLogger(PctlProductDatabseCfg.class);

    /**
     * mongeez() method contains database configurations
     * @return mongeez information needed for databases
     */
    @Bean
    @Profile("test")
    public Mongeez mongeez() {
        log.debug("Configuring Mongeez");
        Mongeez mongeez = new Mongeez();
        mongeez.setFile(new ClassPathResource(PctlModProductConst.TEST_MONGEEZ_FILE_PATH));
        mongeez.setMongo(mongo);
        mongeez.setDbName(getMongoClientURI().getDatabase());
        mongeez.process();
        return mongeez;
    }

    /**
     * mongeez() method contains database configurations
     * @return mongeez information needed for databases
     */
    @Bean
    @Profile("dev")
    public Mongeez loadProductsMasterData() {
        log.debug("Load Products and Price data");
        Mongeez mongeez = new Mongeez();
        mongeez.setFile(new ClassPathResource(PctlModProductConst.DEV_MONGEEZ_FILE_PATH));
        mongeez.setMongo(mongo);
        mongeez.setDbName(getMongoClientURI().getDatabase());
        mongeez.process();
        return mongeez;
    }


    /**
     * mongeez() method contains database configurations
     * @return mongeez information needed for databases
     */
    @Bean
    @Profile("prod")
    public Mongeez loadProductsMasterDataToProd() {
        log.debug("Load Products and Price data");
        Mongeez mongeez = new Mongeez();
        mongeez.setFile(new ClassPathResource(PctlModProductConst.PROD_MONGEEZ_FILE_PATH));
        mongeez.setMongo(mongo);
        mongeez.setDbName(getMongoClientURI().getDatabase());
        mongeez.process();
        return mongeez;
    }


}
