package com.centurylink.pctl.mod.product.constants;

/**
 * Created by dakshinamoorthyd on 22-11-2016.
 */
public class PctlModProductConst {

    public static final String TEST_MONGEEZ_FILE_PATH = "/config/mongeez/master.xml";
    public static final String DEV_MONGEEZ_FILE_PATH = "/config/mongeez/master.xml";
    public static final String PROD_MONGEEZ_FILE_PATH = "/config/mongeez/master.xml";

    /**
     * constants for WebSecurityConfig class
     */

    public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";
    public static final String JWT_TOKEN_USER_ENTRY_POINT = "/products/**" ;    // "/auth/**";
    public static final String JWT_TOKEN_USER_GET_TOKEN = "/auth/token/generate";
    public static final String LOGIN_USER_ENTRY_POINT = "/auth/user/login";
    public static final String LOGGED_OUT_ENTRY_POINT ="/auth/user/logoutSuccess";
    public static final String GET_USER_ENTRY_POINT ="/auth/user/me";

}
