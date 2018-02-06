/**
 *
 */
package com.centurylink.pctl.mod.core.camel.transformers;

import org.apache.log4j.Logger;

/**
 * @author s-arunkumar
 */
public class PrintTransformer {

    private static final Logger logger = Logger
        .getLogger(PrintTransformer.class);

    public String transform(String list) {
        logger.info("PrintTransformer: " + list);
        return list;
    }
}
