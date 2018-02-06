package com.centurylink.pctl.mod.order;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * PctlOrderAppCfg is a main class configured using @SpringBootApplication
 * Created by veeraragavan.k
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration
@ComponentScan({"com.centurylink.pctl.mod"})
@ImportResource({"classpath:applicationContext.xml"})
public class PctlOrderAppCfg {

    public static void main(String[] args) {
        SpringApplication.run(PctlOrderAppCfg.class, args);
    }
}
