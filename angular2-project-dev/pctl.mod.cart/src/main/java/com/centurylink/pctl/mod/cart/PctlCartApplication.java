package com.centurylink.pctl.mod.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Begin Samuel
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration
@ComponentScan("com.centurylink.pctl.mod")
@ImportResource({"classpath:applicationContext.xml"})
public class PctlCartApplication {
    public static void main(String[] args) {
        SpringApplication.run(PctlCartApplication.class, args);
    }

    @LoadBalanced
    @Bean(name = "restTemplate")
    public RestTemplate loadBalancedRestTemplate() {
        return new RestTemplate();
    }
}
