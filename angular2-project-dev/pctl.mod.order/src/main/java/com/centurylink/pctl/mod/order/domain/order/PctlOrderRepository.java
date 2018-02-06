package com.centurylink.pctl.mod.order.domain.order;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * PctlOrderRepository interface extends  MongoRepository
 * PctlOrderRepository interface has details regarding database ,it's table name and it's column
 * value matches with data available in database
 * configured data in  database is:  order
 * PctlOrderRepository interface contains one methods
 * <br>    - findOneByOrderId
 *
 * @author veeraragavan.k
 */
@RepositoryRestResource(collectionResourceRel = "order", path   ="order")
public interface PctlOrderRepository extends MongoRepository<Order, String>{

    /**
     * findOneByOrderId() is configured with orderId data available in database
     *
     * @param orderId, available orderId from database
     * @return Order , available Order for the respective orderId
     */
    Order findOneByOrderId(String orderId);

}
