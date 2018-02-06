package com.centurylink.pctl.mod.ui.jms;

import com.centurylink.pctl.mod.core.event.ShoppingCartEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.Serializable;


/**
 * Created by sakthivel-s on 15-11-2016.
 */
public class  UserMessageConverter implements MessageConverter {
  private static final Logger log = LoggerFactory.getLogger(UserMessageConverter.class);
    @Override
    public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
       log.info("Inside the method UserMessageConverter.toMessage");
        Message message = null;
        if (o instanceof ShoppingCartEvent) {
            ShoppingCartEvent event = (ShoppingCartEvent) o;
            message = session.createObjectMessage(event);
        }
        else
        {
            message = session.createObjectMessage((Serializable) o);
        }
        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        return null;
    }
}
