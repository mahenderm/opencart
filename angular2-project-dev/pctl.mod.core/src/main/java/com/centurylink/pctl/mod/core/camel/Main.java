/*
package com.centurylink.pctl.mod.core.camel;


import com.centurylink.pctl.mod.core.camel.factory.CamelServiceFactory;
import com.centurylink.pctl.mod.core.camel.service.ICamelService;

import com.centurylink.pctl.mod.core.camel.utils.PingResponse;
import com.centurylink.pctl.mod.core.model.address.Address;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
@ComponentScan
public class Main {

//    @Autowired
//    CamelServiceFactory factory;

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);
        CamelServiceFactory factory = ctx.getBean(CamelServiceFactory.class);
        System.out.println("Executed At.1 :" + new Date());
        Address addres = new Address();
        addres.setStreet("90");
        addres.setZipCode("60301");
        addres.setCountry("Spain");
        ICamelService service = CamelServiceFactory.getService("SOAP");
        PingResponse acc = (PingResponse) service.process("pingResponse", addres);
        System.out.println("acc------------------------------");
        System.out.println("PingResponse--> :" + acc);
    }

}

*/
