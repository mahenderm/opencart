package com.centurylink.pctl.mod.ui.jms;


import com.centurylink.pctl.mod.core.event.ShoppingCartEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

//import org.springframework.jms.core.JmsTemplate;

@Service
public class MessageService {


    @Autowired
    private JmsTemplate jmsTemplate;


    public void send(ShoppingCartEvent cartItemEvent) {
         this.jmsTemplate.send(new MessageCreator() {
          @Override
          public Message createMessage(Session session) throws JMSException {

            Message message = session.createObjectMessage(cartItemEvent);

            return message;
          }
        });



    }

    /**
     * Simplify the send by using convertAndSend
     * @param text
     */
    public void sendText(final String text) {
        this.jmsTemplate.convertAndSend(text);
    }

    /**
     * Send text message to a specified destination
     * @param text
     */
    public void send(final Destination dest,final String text) {

        this.jmsTemplate.send(dest,new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createTextMessage(text);
                return message;
            }
        });
    }
}
