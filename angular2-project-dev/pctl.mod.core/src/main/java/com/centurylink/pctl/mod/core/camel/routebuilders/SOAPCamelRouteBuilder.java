/**
 *
 */
package com.centurylink.pctl.mod.core.camel.routebuilders;

import com.centurylink.pctl.mod.core.camel.CamelException;
import com.centurylink.pctl.mod.core.camel.config.CamelConfig;
import com.centurylink.pctl.mod.core.camel.config.SoapEndPoint;
import com.centurylink.pctl.mod.core.camel.transformers.MissingFieldIgnoringXStream;
import com.thoughtworks.xstream.XStream;
import org.apache.camel.builder.xml.Namespaces;
import org.apache.camel.dataformat.xstream.XStreamDataFormat;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @author s-arunkumar
 */
@Component
public class SOAPCamelRouteBuilder extends AbstractRouteBuilder {

    private static final Logger logger = Logger
        .getLogger(SOAPCamelRouteBuilder.class);

    private static XStreamDataFormat xmlToClassBean(String element, String classPath) throws CamelException {
        XStream xstream = new MissingFieldIgnoringXStream();
        try {
            Class clas = Class.forName(classPath);
            xstream.processAnnotations(clas);
            xstream.alias(element, clas);
            XStreamDataFormat dataFormat = new XStreamDataFormat(xstream);
            return dataFormat;
        } catch (ClassNotFoundException e) {
            throw new CamelException("Class : " + classPath + ", is not valid");
        }
    }

    @Override
    public void configure() throws Exception {
        CamelConfig config = bindingConfig();


        if (config != null) {
            for (SoapEndPoint endPoint : config.getSoapEndPoints()) {
                logger.info("createRouteBuilder" + endPoint.getServiceName() + endPoint);
                Namespaces spaces = endPoint.getNamespace();
                from("direct:" + endPoint.getId())
                    .to("velocity:" + endPoint.getVelocityTemplate())
                    .to("log:com.centurylink.pctl.mod.core.camel.soapRequest." + endPoint.getServiceName() + "?level=INFO")
                    .to("spring-ws:" + endPoint.getServiceEndPoint() + "?soapAction=" + endPoint.getSoapAction())
                    .to("log:com.centurylink.pctl.mod.core.camel.soapResponse." + endPoint.getServiceName() + "?level=INFO")
                    .transform().method("PrintTransformer")
                    .transform()
                    .xpath(endPoint.getBaseXPath(), spaces)
                    .to("log:com.centurylink.pctl.mod.core.camel.XPATHProcess1." + endPoint.getServiceName() + "?level=INFO")
                    .unmarshal(xmlToClassBean(endPoint.getUnmarshalField(), endPoint.getUnmarshalClass()));
            }
        }
    }


}
