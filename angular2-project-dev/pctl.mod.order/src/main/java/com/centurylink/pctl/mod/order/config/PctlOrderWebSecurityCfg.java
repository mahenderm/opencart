package com.centurylink.pctl.mod.order.config;

import com.centurylink.pctl.mod.core.security.endpoint.JwtAuthenticationEntryPoint;
import com.centurylink.pctl.mod.core.security.filter.*;
import com.centurylink.pctl.mod.core.security.jwt.extractor.TokenExtractor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

//import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * WebSecurityConfig class  extends WebSecurityConfigurerAdapter
 * <br> configuration of urls to pass authentication
 * <br> urls to bypass the security check
 *
 * @author begin.samuel.
 */
//@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
@Configuration
@EnableWebSecurity
public class PctlOrderWebSecurityCfg extends WebSecurityConfigurerAdapter {
    /**
     * constants for WebSecurityConfig class
     */
    public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";
    public static final String JWT_TOKEN_USER_ENTRY_POINT = "/**";    // "/auth/**";
    public static final String JWT_TOKEN_USER_GET_TOKEN = "/auth/token/generate";
    public static final String LOGIN_USER_ENTRY_POINT = "/auth/user/login";
    public static final String LOGGED_OUT_ENTRY_POINT = "/auth/user/logoutSuccess";
    public static final String GET_USER_ENTRY_POINT = "/auth/user/me";

    /**
     * autowired and qualifier added for authenticationEntryPoint
     */
    @Autowired
    @Qualifier("jwtAuthenticationEntryPoint")
    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    /**
     * autowired and qualifier added for ajaxAwareAuthenticationSuccessHandler
     */
    @Autowired
    @Qualifier("ajaxAwareAuthenticationSuccessHandler")
    private AuthenticationSuccessHandler successHandler;
    /**
     * autowired and qualifier added for ajaxAwareAuthenticationFailureHandler
     */
    @Autowired
    @Qualifier("ajaxAwareAuthenticationFailureHandler")
    private AuthenticationFailureHandler failureHandler;
    /**
     * autowired and qualifier added for jwtAuthenticationProvider
     */
    @Autowired
    @Qualifier("jwtAuthenticationProvider")
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    /**
     * autowired and qualifier added for ajaxAuthenticationProvider
     */
    @Autowired
    @Qualifier("ajaxAuthenticationProvider")
    private AjaxAuthenticationProvider ajaxAuthenticationProvider;
    /**
     * autowired tokenExtractor
     */
    @Autowired
    private TokenExtractor tokenExtractor;
    /**
     * autowired authenticationManager
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    /**
     * autowired objectMapper
     */
    @Autowired
    private ObjectMapper objectMapper;
    /**
     * autowired unauthorizedHandler
     */
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

 /*   @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
            .userDetailsService(this.userDetailsService)
            .passwordEncoder(passwordEncoder());
    }
*/

    /**
     * authenticationTokenFilterBean() method configuring paths to get skipped in web security
     *
     * @return filter
     * @throws Exception for authenticationTokenFilterBean() if it is any error or failure in the operation
     */


    @Bean
    protected JwtTokenAuthenticationProcessingFilter authenticationTokenFilterBean() throws Exception {
        List<String> pathsToSkip = Arrays.asList(JWT_TOKEN_USER_GET_TOKEN, LOGIN_USER_ENTRY_POINT, LOGGED_OUT_ENTRY_POINT, GET_USER_ENTRY_POINT);
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, JWT_TOKEN_USER_ENTRY_POINT);
        JwtTokenAuthenticationProcessingFilter filter
            = new JwtTokenAuthenticationProcessingFilter(failureHandler, tokenExtractor, matcher);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    /**
     * buildAjaxLoginProcessingFilter() contains configuration of authenticationManager
     *
     * @return filter, AjaxLoginProcessingFilter
     * @throws Exception for buildAjaxLoginProcessingFilter() if it is any error or failure in the operation
     */

    @Bean
    protected AjaxLoginProcessingFilter buildAjaxLoginProcessingFilter() throws Exception {
        AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter(JWT_TOKEN_USER_GET_TOKEN, successHandler, failureHandler, objectMapper);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    /**
     * authenticationManagerBean()
     *
     * @return AuthenticationManager
     * @throws Exception for authenticationManagerBean() if it is any error or failure in the operation
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * configure() provides the jwtAuthenticationProvider  and ajaxAuthenticationProvider
     *
     * @param auth, AuthenticationManagerBuilder auth
     */
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(jwtAuthenticationProvider);
        auth.authenticationProvider(ajaxAuthenticationProvider);
    }

    /**
     * passwordEncoder()
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    protected BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * configure() method is overridden
     * <br> configuring the urls to skip, filters complete information of websecurity
     *
     * @param http, HttpSecurity http
     * @throws Exception for configure() if it is any error or failure in the operation
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        //.authorizeRequests()

        http
            .csrf().disable() // We don't need CSRF for JWT based authentication
            .exceptionHandling()
            .authenticationEntryPoint(unauthorizedHandler)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .anonymous()
            .and()
            .authorizeRequests()
            .antMatchers(JWT_TOKEN_USER_GET_TOKEN).permitAll()
            .antMatchers(LOGIN_USER_ENTRY_POINT).permitAll()
            .antMatchers(LOGGED_OUT_ENTRY_POINT).permitAll()
            .antMatchers(GET_USER_ENTRY_POINT).permitAll()
            .and()
            .authorizeRequests()
            .antMatchers(JWT_TOKEN_USER_ENTRY_POINT).authenticated()
            .and()
            .addFilterBefore(buildAjaxLoginProcessingFilter(),
                UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(authenticationTokenFilterBean(),
                UsernamePasswordAuthenticationFilter.class);
    }
}
