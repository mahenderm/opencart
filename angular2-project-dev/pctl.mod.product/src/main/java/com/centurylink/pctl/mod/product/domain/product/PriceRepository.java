package com.centurylink.pctl.mod.product.domain.product;

import com.centurylink.pctl.mod.core.model.product.Price;
import com.centurylink.pctl.mod.core.model.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * PriceRepository interface extends  MongoRepository
 * PriceRepository interface has details regarding database ,it's table name and it's column
 * value matches with data available in database
 * configured data in  database is:  price
 * PriceRepository interface contains two methods
 * <br>    - findPriceByProductId
 * <br>    - findPriceByVarintId
 *
 * @author Begin Samuel
 */

@RepositoryRestResource(collectionResourceRel = "products_price", path = "price")
public interface PriceRepository extends MongoRepository<Price, String> {

    /**
     * findPriceByProductId() is configured with productId data available in database
     *
     * @param productId, available productid from database
     * @return Price List, returns respective List of Price for the given productId available in database
     */
    @Query(value = "{ 'productId' : ?0 }")
    public List<Price> findPriceByProductId(String productId);

    @RestResource(path = "findByProductId", rel = "findByProductId")
    public List<Price> findByProductId(@Param("productId") String productId);

    @RestResource(path = "findByVariantId", rel = "findByVariantId")
    public List<Price> findByVariantId(@Param("variantId") String variantId);


    /**
     * findPriceByVarintId() is configured with variantId data available in database
     *
     * @param variantId, available variantId from database
     * @return Price List, returns respective List of Price for the given variantId available in database
     */
    @Query(value = "{ 'variantId' : ?0 }")
    public List<Price> findPriceByVarintId(String variantId);

}
