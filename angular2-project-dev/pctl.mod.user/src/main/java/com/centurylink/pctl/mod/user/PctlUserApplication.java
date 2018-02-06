package com.centurylink.pctl.mod.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.client.RestTemplate;

/**
 * PctlUserApplication class has the main method configured using SpringBootApplication
 * using @ComponentScan for com.centurylink.pctl.mod.user and com.centurylink.pctl.mod.core
 * <br>   EnableResourceServer used
 * <br>   EnableDiscoveryClient used
 *
 * @author Begin Samuel
 */
@SpringBootApplication
@EnableDiscoveryClient
//@EnableCaching
@ComponentScan({"com.centurylink.pctl.mod.core", "com.centurylink.pctl.mod.user"})
@ImportResource({"classpath:applicationContext.xml"})
public class PctlUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(PctlUserApplication.class, args);
    }

    @LoadBalanced
    @Bean(name = "normalRestTemplate")
    public RestTemplate loadBalancedRestTemplate() {
        return new RestTemplate();
    }


    /*@Bean
    @Primary
    RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory rcf) {

        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(rcf);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new JsonRedisSerializer());

        return template;
    }

    static class JsonRedisSerializer implements RedisSerializer<Object> {

        private final ObjectMapper om;

        public JsonRedisSerializer() {
            this.om = new ObjectMapper().enableDefaultTyping(DefaultTyping.NON_FINAL, As.PROPERTY);
        }

        @Override
        public byte[] serialize(Object t) throws SerializationException {
            try {
                return om.writeValueAsBytes(t);
            } catch (JsonProcessingException e) {
                throw new SerializationException(e.getMessage(), e);
            }
        }

        @Override
        public Object deserialize(byte[] bytes) throws SerializationException {

            if(bytes == null){
                return null;
            }

            try {
                return om.readValue(bytes, Object.class);
            } catch (Exception e) {
                throw new SerializationException(e.getMessage(), e);
            }
        }
    }*/


}
