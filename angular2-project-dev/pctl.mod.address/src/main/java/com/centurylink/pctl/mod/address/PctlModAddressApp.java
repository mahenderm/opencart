package com.centurylink.pctl.mod.address;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * PctlAddressApplication class has the main method configured in SpringBootApplication
 *
 * @author Haribabu.ka
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"com.centurylink.pctl.mod.address", "com.centurylink.pctl.mod.core"})
public class PctlModAddressApp {

    public static void main(String[] args) {
        SpringApplication.run(PctlModAddressApp.class, args);
    }


}
