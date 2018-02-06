package com.centurylink.pctl.mod.cart.domain.jms;



import com.centurylink.pctl.mod.cart.domain.ShoppingCart.PctlCartService;
import com.centurylink.pctl.mod.core.event.ShoppingCartEvent;
import com.mongodb.DBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;
import org.springframework.data.mongodb.core.mapping.MongoPersistentProperty;

public class EventSaveHandler extends MappingMongoConverter   {

    @Autowired
    public PctlCartService shoppingCartService;
    private static final Logger log = LoggerFactory.getLogger(EventSaveHandler.class);

    public EventSaveHandler(
            MongoDbFactory mongoDbFactory,
            MappingContext<? extends MongoPersistentEntity<?>, MongoPersistentProperty> mappingContext) {
        super(new DefaultDbRefResolver(mongoDbFactory), mappingContext);
    }

    @Override
    public void write(Object source, DBObject target) {


        ShoppingCartEvent event = null;
        boolean status = false;
        String errorCode = null;
        long start = System.currentTimeMillis();
        try {
            event = (ShoppingCartEvent) source;
            log.info("action - converter start");
            /*if(event != null){
                if(event.getEventInfo()!= null){
                    shoppingCartService.addCartEvent(event);
                }
            }*/
            //super.write(source, target);
            super.write(event.getEventAttributes(),target);
            status = true;
        } catch (Exception e) {
            errorCode = e.getClass().getSimpleName();
            throw e;
        } finally {
            long end = System.currentTimeMillis();
            log.info("action - converter end");

        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <S> S read(Class<S> clazz, DBObject source) {
        return (S) ((source.get("id") + ", ")
                + source.get("name") + ", ");
    }



}
