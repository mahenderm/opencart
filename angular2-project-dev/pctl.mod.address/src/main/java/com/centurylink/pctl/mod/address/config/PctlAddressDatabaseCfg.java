package com.centurylink.pctl.mod.address.config;


import com.centurylink.pctl.mod.core.config.AbstractDatabaseConfiguration;
import org.mongeez.Mongeez;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * PctlCartDatabaseCfg class contains configuration for Database of product modules
 * <br> configured with mongeez and reads from master.xml file as its input
 *
 * @author nagavenkatakirang
 */
@Configuration
@EnableMongoRepositories("com.centurylink.pctl.mod.address")
@Import(value = MongoAutoConfiguration.class)
//@EnableMongoAuditing(auditorAwareRef = "springSecurityAuditorAware")
public class PctlAddressDatabaseCfg extends AbstractDatabaseConfiguration {

    private final Logger log = LoggerFactory.getLogger(PctlAddressDatabaseCfg.class);

    /**
     * mongeez() method contains database configurations
     *
     * @return mongeez information needed for databases
     */
    @Bean
    @Profile("test")
    public Mongeez mongeez() {
        log.debug("Configuring Mongeez");
        Mongeez mongeez = new Mongeez();
        mongeez.setFile(new ClassPathResource("/mongeez/master.xml"));
        mongeez.setMongo(mongo);
        mongeez.setDbName(getMongoClientURI().getDatabase());
        mongeez.process();
        return mongeez;
    }

}
