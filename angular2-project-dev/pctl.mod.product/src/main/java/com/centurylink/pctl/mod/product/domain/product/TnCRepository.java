package com.centurylink.pctl.mod.product.domain.product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * TnCRepository interface extends  MongoRepository
 * TnCRepository interface has details regarding database ,it's table name and it's column
 * value matches with data available in database
 * configured data in  database is:  tnc
 * TnCRepository interface contains two methods
 * <br>    - findPriceByProductId
 * <br>    - findPriceByVarintId
 *
 * @author Begin Samuel
 */


public interface TnCRepository extends MongoRepository<TnC, String> {

    @RestResource(path = "findByType", rel = "findByType")
    public List<TnC> findByType(@Param("type") String type);

    @RestResource(path = "findByVersion", rel = "findByVersion")
    public TnC findByVersion(@Param("version") String version);


    /**
     * findTnCByProductId() is configured with productId data available in database
     *
     * @param productId, available productId from database
     * @return TnC List, returns respective List of TnC  in database
     */
    @Query(value = "{ 'productId' : ?0 }")
    public List<TnC> findTnCByProductId(String productId);


    /**
     * findAllTnCs() is configured
     *
     * @return TnC List, returns respective List of TnC
     */
    @Query(value = "{ $orderby: {publishedDate:-1}, $limit:2 }")
    public List<TnC> findAllTnCs();


}
