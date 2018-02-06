package com.centurylink.pctl.mod.core.camel.config;

import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;
import org.apache.commons.digester3.annotations.rules.SetProperty;

/**
 * @author s-arunkumar
 */
@ObjectCreate(pattern = "config/soapEndPoints/soapEndPoint/namespace")
public class Namespace {

    @SetProperty(attributeName = "id", pattern = "config/soapEndPoints/soapEndPoint/namespace")
    private String id;

    @BeanPropertySetter(pattern = "config/soapEndPoints/soapEndPoint/namespace/prefix")
    private String prefix;

    @BeanPropertySetter(pattern = "config/soapEndPoints/soapEndPoint/namespace/uri")
    private String uri;

    public Namespace() {
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Namespace addNamespace() {
        return this;
    }

    @Override

    public String toString() {
        return "Namespace{" +
            "prefix='" + prefix + '\'' +
            ", uri='" + uri + '\'' +
            '}';
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
