package com.centurylink.pctl.mod.cart.domain.ShoppingCart;

import com.centurylink.pctl.mod.core.model.cart.CartEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.stream.Stream;

/**
 * PctlCartEventRepository is an interface which extends MongoRepository
 * It has details regarding database ,it's table name (cart_events) and path
 * This interface contains one method
 * <br>    - getCartEventStreamByCartId(...)
 * @author nagavenkatakirang
 */
@RepositoryRestResource(collectionResourceRel = "cart_events", path = "cart")
public interface PctlCartEventRepository extends MongoRepository<CartEvent, Long> {
    /**
     * getCartEventStreamByCartId()
     * @param id, id for corresponding CartEventStream
     * @return Stream, all events as a Stream
     */
    public Stream<CartEvent> getCartEventStreamByCartId(String id);
}
