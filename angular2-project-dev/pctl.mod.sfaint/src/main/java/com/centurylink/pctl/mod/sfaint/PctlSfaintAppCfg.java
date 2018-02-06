package com.centurylink.pctl.mod.sfaint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.client.RestTemplate;

/**
 * @author Dinesh
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"com.centurylink.pctl.mod.core", "com.centurylink.pctl.mod.sfaint"})
@ImportResource({"classpath:applicationContext.xml"})
public class PctlSfaintAppCfg {
    public static void main(String[] args) {
        SpringApplication.run(PctlSfaintAppCfg.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
