package com.centurylink.pctl.mod.product.controllers.rest;


import com.centurylink.pctl.mod.core.model.product.Price;
import com.centurylink.pctl.mod.core.model.product.Product;
import com.centurylink.pctl.mod.product.domain.product.PctlProductService;
import com.centurylink.pctl.mod.product.domain.product.TnC;
import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class PctlProductRestController is a RestController using springMVC
 * <br>
 * This class PctlProductRestController contains three methods
 * <br> - getProducts()
 * <br> - getAllProductPrice()
 * <br> - getPriceByVariantId()
 *
 * @author Begin Samuel
 */

@Transactional
@RestController
@RequestMapping("/products")
public class PctlProductRestController {

    private static final Logger log = LoggerFactory.getLogger(PctlProductRestController.class);
    /**
     * PctlApiProductService is autowired for PctlProductRestController to make service call
     * to PriceRepository
     */
    @Autowired
    private PctlProductService pctlProductService;

    /**
     * getProducts() method returns one single Product details if it's available in database
     *
     * @return Product, if given product is available in database
     */

    //@HystrixCommand(fallbackMethod = "cachingServersAreDown")
    @Cacheable(value = "product-list")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Product getProducts() {
        log.info("Getting Products");
        return pctlProductService.findProductByProductId("sdwan1000");
    }

    /**
     * This getAllProductPrice() method uses pctlApiProductService finaAll() internally and fetches the price list of all products
     * available in database.
     *
     * @return Price List, All available Product's price list will be returned
     */
    @RequestMapping(value = "/price/", method = RequestMethod.GET)
    public List<Price> getAllProductPrice() {
        log.info("Getting Price");
        log.info(" Price count {} ", pctlProductService.findAllPrice().size());
        return Lists.newArrayList(pctlProductService.findAllPrice());
    }


    public String cachingServersAreDown() {
        return "Caching Server is down";
    }


    /**
     * <p>
     * This getPriceByVariantId() gets parameter as variantId, which is  one among different products
     * This method intenally calls PctlApiProductService findAllPriceByVariantId() method
     * and returns list of price for that product
     * </p>
     *
     * @param variantId, one of the available product's variant id
     * @return Price List, List of product price for the given product's variant id
     */
    @RequestMapping(value = "/price/{variantId}", method = RequestMethod.GET)
    public List<Price> getPriceByVariantId(@PathVariable("variantId") String variantId) {
        log.info("Getting Price");
        log.info(" Price count {} ", pctlProductService.findAllPriceByVariantId(variantId));
        return Lists.newArrayList(pctlProductService.findAllPriceByVariantId(variantId));
    }



    /**
     * <p>
     * This getTncsByLatest() gets all Tncs
     * This method intenally calls PctlApiProductService findAllTncByLatest() method
     * and returns list of price for that product
     * </p>
     *
     * @return TnC List, List of tnc
     */
    @RequestMapping(value = "/tnc", method = RequestMethod.GET)
    public List<TnC> getTncsByLatest() {
        log.info("Getting Tnc");
        return Lists.newArrayList(pctlProductService.findAllTncByLatest());
    }

}
