package com.centurylink.pctl.mod.product.domain.product;

import com.centurylink.pctl.mod.core.model.product.Price;
import com.centurylink.pctl.mod.core.model.product.Product;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

/**
 * ProductPagedResource class implementing ResourceProcessor
 * <br> - process
 *
 * @author Begin Samuel
 */
@Component
public class PricePagedResource implements ResourceProcessor<PagedResources<Resource<Price>>> {

    /**
     * process() method
     *
     * @param priceResources, Product Resource is passed
     * @return productResources Product is returned
     */
    @Override
    public PagedResources<Resource<Price>> process(PagedResources<Resource<Price>> priceResources) {

      /* final Link priceLink = ControllerLinkBuilder.linkTo(
           methodOn(PctlProductRestController.class).  ).withRel("product-review");
       productResources.add(priceLink);*/
       return priceResources;
    }


}

