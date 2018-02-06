package com.centurylink.pctl.mod.notification.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * PctlNotificationRepository interface configured with notification table in database
 * @author  pulapakas
 */
@RepositoryRestResource(collectionResourceRel = "notification", path = "notification")
public interface PctlNotificationRepository extends MongoRepository<Notification, String> {

    /**
     * configured with "entityId" data in database
     * @param entityId is input parameter
     * @return Notification list
     */
    @Query(value = "{ 'entityId' : ?0 }")
    public List<Notification> findNotificationByEntityId(String entityId);

    /**
     * configured with "deliveryType" data in database
     * @param deliveryType is input parameter
     * @return Notification list
     */
    @Query(value = "{ 'deliveryType' : ?0 }")
    public List<Notification> findNotificationByDeliveryType(String deliveryType);


    /**
     * configured with "deliveryType" and "deliveryStatus" data in database
     * @param deliveryType is input parameter
     * @param deliveryStatus is input parameter
     * @return Notification list
     */
    @Query(value = "{ 'deliveryType' : ?0 , 'deliveryStatus' : ?0 }")
    public List<Notification> findNotificationByDeliveryTypeAndDeliveryStatus(String deliveryType,String deliveryStatus);



    /**
     * configured with "id" data in database
     * @param id is input parameter
     * @return Notification
     */
    @Query(value = "{ '_id' : ?0 }")
    public Notification findNotificationById(String id);

}


