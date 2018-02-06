package com.centurylink.pctl.mod.order.domain.order;

import com.centurylink.pctl.mod.core.model.order.OrderEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.stream.Stream;

/**
 * PctlOrderEventRepository interface extends  MongoRepository
 * PctlOrderEventRepository interface has details regarding database ,it's table name and it's column
 * value matches with data available in database
 * configured data in  database is:  order_event
 * PctlOrderEventRepository interface contains one methods
 * <br>    - findOrderEventByOrderId
 *
 * @author veeraragavan.k
 */

@RepositoryRestResource(collectionResourceRel = "order_event", path = "order")
public interface PctlOrderEventRepository extends MongoRepository<OrderEvent, String>{

    /**
     * findOrderEventByOrderId() is configured with orderId data available in database
     *
     * @param orderId, available orderId from database
     * @return Stream , all available OrderEvent for the respective orderId
     */
    @Query(value = "{ 'orderId' : ?0 }")
    Stream<OrderEvent> findOrderEventByOrderId(String orderId);

    @Query(value = "{ 'cartId' : ?0, 'orderEventType' : ?1 }")
    OrderEvent findOrderEventByCartId(String cartId, String orderEventType);
}
