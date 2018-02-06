package com.centurylink.pctl.mod.address.constants;

/**
 * Created by dakshinamoorthyd on 22-11-2016.
 */
public class PctlModAddressConst {

    /**
     * constants for WebSecurityConfig class
     */

    public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";
    public static final String JWT_TOKEN_USER_ENTRY_POINT = "/address/**";    // "/auth/**";
    public static final String JWT_TOKEN_USER_GET_TOKEN = "/auth/token/generate";
    public static final String LOGIN_USER_ENTRY_POINT = "/auth/user/login";
    public static final String LOGGED_OUT_ENTRY_POINT = "/auth/user/logoutSuccess";
    public static final String GET_USER_ENTRY_POINT = "/auth/user/me";

}
