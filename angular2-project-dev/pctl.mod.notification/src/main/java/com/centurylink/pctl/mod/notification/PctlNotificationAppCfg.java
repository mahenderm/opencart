package com.centurylink.pctl.mod.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * PctlNotificationAppCfg class has the main method configured in SpringBootApplication
 * @author Begin Samuel
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"com.centurylink.pctl.mod.notification", "com.centurylink.pctl.mod.core"})
public class PctlNotificationAppCfg {

    public static void main(String[] args) {

        SpringApplication.run(PctlNotificationAppCfg.class, args);
    }
}
