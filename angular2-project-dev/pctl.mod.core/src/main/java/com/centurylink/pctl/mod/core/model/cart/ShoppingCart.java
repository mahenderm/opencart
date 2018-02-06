package com.centurylink.pctl.mod.core.model.cart;
import com.fasterxml.jackson.annotation.JsonIgnore;
import reactor.core.publisher.Flux;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The {@link com.centurylink.pctl.mod.core.model.cart.ShoppingCart} object represents an aggregate of
 * {@link CartEvent} that represent actions
 * taken by a user to add/remove/clear/checkout products from their cart as well as
 * add/remove/update location details
 * @author nagavenkatakirang
 */

public class ShoppingCart implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * cartId of Cart
     */
    private String cartId;
    /**
     * productMap of Cart
     */
    private Map<String, Integer> productMap = new HashMap<>();
    /**
     * lineItems of Cart
     */
    private List<LineItem> lineItems = new ArrayList<>();
    /**
     * locations of Cart
     */
    private List<AddressLocation> locations = new ArrayList<>();
    /**
     * catalog of Cart
     */
    private Catalog catalog;
    /**
     * locationMap of Cart
     */
    @JsonIgnore
    private Map<String, AddressLocation> locationMap = new HashMap<>();
    //constructors
    public ShoppingCart() {
        this.catalog = new Catalog();
    }

    public ShoppingCart(Catalog catalog) {
        this.catalog = catalog;
    }
    /**
     * getter for cartId
     * @return cartId
     */
    public String getCartId() {
        return cartId;
    }
    /**
     * setter for cartId
     * @param cartId id of the cart
     */
    public void setCartId(String cartId) {
        this.cartId = cartId;
    }
    /**
     * getter for locations
     * @return locations
     */
    public List<AddressLocation> getLocations() {
        return locations;
    }
    /**
     * setter for locations
     * @param locations list of locations
     */
    public void setLocations(List<AddressLocation> locations) {
        this.locations = locations;
    }
    /**
     * getter for locationMap
     * @return locationMap
     */
    public Map<String, AddressLocation> getLocationMap() {
        return locationMap;
    }
    /**
     * setter for locationMap
     * @param locationMap location map
     */
    public void setLocationMap(Map<String, AddressLocation> locationMap) {
        this.locationMap = locationMap;
    }
    /**
     * getter for productMap
     * @return productMap
     */
    @JsonIgnore
    public Map<String, Integer> getProductMap() {
        return productMap;
    }
    /**
     * setter for productMap
     * @param productMap product Map
     */
    public void setProductMap(Map<String, Integer> productMap) {
        this.productMap = productMap;
    }
    /**
     * getter for catalog
     * @return catalog
     */
    @JsonIgnore
    public Catalog getCatalog() {
        return catalog;
    }
    /**
     * setter for lineItems
     * @param lineItems list of lineItems
     */
    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }
    /**
     * setter for catalog
     * @param catalog catalog
     */
    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    /**
     * This method is used to initialize the Catalog {@link Catalog}
     * Products will be added to the catalog as a set of line items.
     * @param cartEvent used to check whether CartEventType is ADD_ITEM
     */
    public void initiateCatalog(CartEvent cartEvent){

        if(cartEvent.getCartEventType().equals(CartEventType.ADD_ITEM)){
            Set<LineItem> products = new HashSet<>();
            if(this.catalog == null ){
                this.catalog = new Catalog();
            }
            else{
                if(this.catalog.getProducts() != null) {
                    products = this.catalog.getProducts();
                }
            }
            LineItem lineItem = new LineItem();
            lineItem.setTerm(cartEvent.getTerm());
            lineItem.setProductId(cartEvent.getProductId());
            lineItem.setProductName(cartEvent.getProductName());
            lineItem.setProductTemplateName(cartEvent.getProductTemplateName());
            lineItem.setProductTemplateId(cartEvent.getProductTemplateId());
            lineItem.setUnitPrice(cartEvent.getUnitPrice());
            lineItem.setQuantity(cartEvent.getQuantity());
            lineItem.set_id(cartEvent.getId());
            products.add(lineItem);
            this.catalog.setId(cartEvent.getProductId() + "_" + cartEvent.getProductTemplateId());
            this.catalog.setProducts(products);
        }
    }
    /**
     * Get the line items from the aggregate of add cart events
     *
     * @return a new list of {@link com.centurylink.pctl.mod.core.model.cart.LineItem} representing the state of the shopping cart
     */
    public List<LineItem> getLineItems()  {
        try {
            lineItems = productMap.entrySet()
                .stream()
                .map(item -> new LineItem(
                    catalog.getProducts().stream()
                        .filter(prd -> Objects.equals(prd.getProductTemplateId(), item.getKey()))
                        .findFirst().get().getTerm(),
                    catalog.getProducts().stream()
                        .filter(prd -> Objects.equals(prd.getProductTemplateId(), item.getKey()))
                        .findFirst().get().get_id(),
                    catalog.getProducts().stream()
                        .filter(prd -> Objects.equals(prd.getProductTemplateId(), item.getKey()))
                        .findFirst().get().getProductId(),
                    item.getValue(),
                    catalog.getProducts().stream()
                        .filter(prd -> Objects.equals(prd.getProductTemplateId(), item.getKey()))
                        .findFirst().get().getProductName(),
                    catalog.getProducts().stream()
                        .filter(prd -> Objects.equals(prd.getProductTemplateId(), item.getKey()))
                        .findFirst().get().getProductTemplateName(),
                    item.getKey(),
                    catalog.getProducts().stream()
                        .filter(prd -> Objects.equals(prd.getProductTemplateId(), item.getKey()))
                        .findFirst().get().getUnitPrice(),
                    locationMap.entrySet()
                        .stream()
                        .filter(address -> Objects.equals(address.getKey(), item.getKey() + address.getValue().getId()))
                        .filter(addressLocation -> addressLocation.getValue().getLocationCount() > 0)
                        .map(addressEntry -> addressEntry.getValue())
                        .collect(Collectors.toList())
                ))
                .filter(item -> item.getQuantity() > 0)
                .collect(Collectors.toList()).stream()
                .map(lineItem ->
                    new LineItem(
                        lineItem.getTerm(),
                        lineItem.get_id(),
                        lineItem.getProductId(),
                        (lineItem.getLocations().size() > 0) ? lineItem.getLocations().size() : 1,
                        lineItem.getProductName(),
                        lineItem.getProductTemplateName(),
                        lineItem.getProductTemplateId(),
                        lineItem.getUnitPrice(),
                        lineItem.getLocations()))
                .collect(Collectors.toList());
        }catch (Exception e){
        }
        return lineItems;
    }

    /**
     * Incorporates a new {@link CartEvent} and updates the shopping cart
     * @param cartEvent is the {@link CartEvent} that will alter the state of the cart
     * @return {@link com.centurylink.pctl.mod.core.model.cart.ShoppingCart }the state of the cart
     * after applying the new {@link CartEvent}
     */
    public ShoppingCart incorporateWithLocation(CartEvent cartEvent) {
        // Safety properties in microservices
        Flux<CartEventType> validCartLineItemEventTypes =
            Flux.fromStream(Stream.of(CartEventType.ADD_ITEM,
                CartEventType.REMOVE_ITEM));

        Flux<CartEventType> validCartLocationEventTypes =
            Flux.fromStream(Stream.of(CartEventType.ADD_LOCATION,
                CartEventType.UPDATE_LOCATION,CartEventType.REMOVE_LOCATION,CartEventType.CHECKOUT));

        // The CartEvent's type must be either ADD_ITEM or REMOVE_ITEM for productMap
        if (validCartLineItemEventTypes.exists(cartEventType ->
            cartEvent.getCartEventType().equals(cartEventType)).get()) {
                initiateCatalog(cartEvent);
                productMap.put(cartEvent.getProductTemplateId(),
                    productMap.getOrDefault(cartEvent.getProductTemplateId(), 0) +
                        (cartEvent.getQuantity() * (cartEvent.getCartEventType()
                            .equals(CartEventType.ADD_ITEM) ? 1 : -1)));
        }
        // The CartEvent's type must be either ADD_LOCATION or REMOVE_LOCATION or UPDATE_LOCATION for locationMap
        if (validCartLocationEventTypes.exists(cartEventType ->
            cartEvent.getCartEventType().equals(cartEventType)).get()) {
            // Update the aggregate view of each line item's quantity from the event type
            AddressLocation addressLocation = new AddressLocation();
            addressLocation.setId(cartEvent.getLocation().getId());
            addressLocation.setServiceAddress(cartEvent.getLocation().getServiceAddress());
            addressLocation.setShippingAddress(cartEvent.getLocation().getShippingAddress());
            addressLocation.setServiceContact(cartEvent.getLocation().getServiceContact());
            addressLocation.setId(cartEvent.getLocation().getId());
            addressLocation.setLocationCount(1);

            int previousCount = (locationMap.containsKey(cartEvent.getProductTemplateId()+cartEvent.getLocation().getId()))?
                locationMap.get(cartEvent.getProductTemplateId()+cartEvent.getLocation().getId()).getLocationCount() : 0 ;

            int count = previousCount    +  (cartEvent.getCartEventType().equals(CartEventType.ADD_LOCATION) ? 1 :
                (cartEvent.getCartEventType().equals(CartEventType.UPDATE_LOCATION) ? 0 : -1));

            addressLocation.setLocationCount(count);
            locationMap.put(cartEvent.getProductTemplateId()+cartEvent.getLocation().getId(),addressLocation);
        }
        // Return the updated state of the aggregate to the reactive stream's reduce method
        return this;
    }
    /**
     * Determines whether or not the {@link com.centurylink.pctl.mod.core.model.cart.CartEvent} is a terminal event, causing the
     * stream to end while generating an aggregate
     *
     * @param eventType is the {@link CartEventType} to evaluate
     * @return a flag indicating whether or not the event is terminal
     */
    public static Boolean isTerminal(CartEventType eventType) {
        return (eventType == CartEventType.CLEAR_CART || eventType == CartEventType.CHECKOUT);
    }
    /**
     * toString() method is overridden to return the state of the ShoppingCart
     */
    @Override
    public String toString() {
        return "ShoppingCart{" +
            "cartId='" + cartId + '\'' +
            ", productMap=" + productMap +
            ", locationMap=" + locationMap +
            ", lineItems=" + lineItems +
            ", locations=" + locations +
            ", catalog=" + catalog +
            '}';
    }
}
