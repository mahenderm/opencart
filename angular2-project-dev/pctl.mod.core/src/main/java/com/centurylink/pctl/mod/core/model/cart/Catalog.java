package com.centurylink.pctl.mod.core.model.cart;

import java.io.Serializable;
import java.util.Set;

/**
 * Catalog Bean
 *
 * @author nagavenkatakirang
 */
public class Catalog implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     * id of Catalog
     */
    private String id;
    /**
     * products of Catalog
     */
    private Set<LineItem> products;
    /**
     * name of Catalog
     */
    private String name;
    //constructors
    public Catalog() {
    }

    public Catalog(String name) {
        this.name = name;
    }

    /**
     * getter for products
     * @return products
     */
    public Set<LineItem> getProducts() {
        return products;
    }
    /**
     * setter for products
     * @param products collection of products is passed as parameter
     */
    public void setProducts(Set<LineItem> products) {
        this.products = products;
    }
    /**
     * getter for id
     * @return id
     */
    public String getId() {
        return id;
    }
    /**
     * setter for id
     * @param id id of the catalog
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * getter for name
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * setter for name
     * @param name name of the catalog,if user wants to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * toString() method is overridden to return the state of the Catalog
     */
    @Override
    public String toString() {
        return "Catalog{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
