package com.centurylink.pctl.mod.product;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;


/**
 * PctlProductApplication is a main class configured using @SpringBootApplication
 * Created by Begin Samuel
 */

@SpringBootApplication
@EnableDiscoveryClient
//@EnableCaching
@EnableCircuitBreaker
@ComponentScan({"com.centurylink.pctl.mod.product", "com.centurylink.pctl.mod.core"})
public class PctlModProductApp {



    public static void main(String[] args) {
        SpringApplication.run(PctlModProductApp.class, args);
    }


}
