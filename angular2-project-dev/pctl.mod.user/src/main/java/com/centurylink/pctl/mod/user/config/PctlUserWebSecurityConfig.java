package com.centurylink.pctl.mod.user.config;

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
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

//import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * WebSecurityConfig class  extends WebSecurityConfigurerAdapter
 * <br> configuration of urls to pass authentication
 * <br> urls to bypass the security check
 * @author begin.samuel.
 */
//@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
@Configuration
@EnableWebSecurity
public class PctlUserWebSecurityConfig extends WebSecurityConfigurerAdapter  {
    /**
     * constants for WebSecurityConfig class
     */
    public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";
    public static final String JWT_TOKEN_USER_ENTRY_POINT = "/auth/**" ;    // "/auth/**";
    public static final String JWT_TOKEN_USER_GET_TOKEN = "/auth/token/generate";
    public static final String LOGIN_USER_ENTRY_POINT = "/auth/user/login";
    public static final String LOGGED_OUT_ENTRY_POINT ="/auth/user/logoutSuccess";
    public static final String GET_USER_ENTRY_POINT ="/auth/user/me";

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
    @Autowired private TokenExtractor tokenExtractor;

    /**
     * autowired authenticationManager
     */
    @Autowired private AuthenticationManager authenticationManager;
    /**
     * autowired objectMapper
     */
    @Autowired private ObjectMapper objectMapper;
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
     * @return filter
     * @throws Exception xxx
     */



    @Bean
    protected JwtTokenAuthenticationProcessingFilter authenticationTokenFilterBean() throws Exception {
        List<String> pathsToSkip = new ArrayList<String>();
        pathsToSkip.add(JWT_TOKEN_USER_GET_TOKEN);
        pathsToSkip.add(LOGIN_USER_ENTRY_POINT);
        pathsToSkip.add(LOGGED_OUT_ENTRY_POINT);
        pathsToSkip.add(GET_USER_ENTRY_POINT);
        pathsToSkip.add("/user/create-ein");
        pathsToSkip.add("/auth/user/logged-user");
        pathsToSkip.add("/auth/user/order/update");
        pathsToSkip.add("/auth/user/me1");
        pathsToSkip.add("/auth/user/find");
        pathsToSkip.add("/auth/user/postcart");
        pathsToSkip.add("/auth/user/init");
        pathsToSkip.add("/auth/user/cart/init");
        pathsToSkip.add("/actuator");
        pathsToSkip.add("/autoconfig");
        pathsToSkip.add("/beans");
        pathsToSkip.add("/configprops");
        pathsToSkip.add("/dump");
        pathsToSkip.add("/env");
        pathsToSkip.add("/flyway");
        pathsToSkip.add("/health");
        pathsToSkip.add("/info");
        pathsToSkip.add("/liquibase");
        pathsToSkip.add("/metrics");
        pathsToSkip.add("/mappings");
        pathsToSkip.add("/trace");
        pathsToSkip.add("/docs");
        pathsToSkip.add("/heapdump");
        pathsToSkip.add("/jolokia");
        pathsToSkip.add("/logfile");
        pathsToSkip.add("/auth/user/checkuser");
        pathsToSkip.add("/user/createenterprise");
        pathsToSkip.add("/user/assignaccountstouser");



        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip,JWT_TOKEN_USER_ENTRY_POINT );
        JwtTokenAuthenticationProcessingFilter filter
            = new JwtTokenAuthenticationProcessingFilter(failureHandler, tokenExtractor, matcher);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    /**
     *buildAjaxLoginProcessingFilter() contains configuration of authenticationManager
     * @return  filter, AjaxLoginProcessingFilter
     * @throws Exception xxx
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
     * @throws Exception xxx
     */

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    /**
     * configure() provides the jwtAuthenticationProvider  and ajaxAuthenticationProvider
     * @param auth xx
     */
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(jwtAuthenticationProvider);
        auth.authenticationProvider(ajaxAuthenticationProvider);
    }

    /**
     * passwordEncoder()
     * @return BCryptPasswordEncoder
     */
    @Bean
    protected BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * configure() method gives resourceid
     * @param resources, ResourceServerSecurityConfigurer resourceid
     * @throws Exception  configure
     */

 /*   @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("pctl.mod.user");
    }*/

    /**
     * configure() method is overridden
     * <br> configuring the urls to skip, filters complete information of websecurity
     * @param http xx
     * @throws Exception xxx
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
            .antMatchers("/user/create-ein").permitAll()
            .antMatchers("/auth/user/logged-user").permitAll()
            .antMatchers("/auth/user/order/update").permitAll()
            .antMatchers("/auth/user/postcart").permitAll()
            .antMatchers("/auth/user/find").permitAll()
            .antMatchers("/auth/user/cart/init").permitAll()
            .antMatchers("/auth/user/init").permitAll()
            .antMatchers("/auth/user/me1").permitAll()
            .antMatchers("/actuator").permitAll()
            .antMatchers("/autoconfig").permitAll()
            .antMatchers("/beans").permitAll()
            .antMatchers("/configprops").permitAll()
            .antMatchers("/dump").permitAll()
            .antMatchers("/env").permitAll()
            .antMatchers("/flyway").permitAll()
            .antMatchers("/health").permitAll()
            .antMatchers("/info").permitAll()
            .antMatchers("/liquibase").permitAll()
            .antMatchers("/metrics").permitAll()
            .antMatchers("/mappings").permitAll()
            .antMatchers("/shutdown").denyAll()
            .antMatchers("/trace").permitAll()
            .antMatchers("/docs").permitAll()
            .antMatchers("/heapdump").permitAll()
            .antMatchers("/jolokia").permitAll()
            .antMatchers("/logfile").permitAll()
            .antMatchers("/auth/user/checkuser").permitAll()
            .antMatchers("/user/createenterprise").permitAll()
            .antMatchers(" /user/assignaccountstouser").permitAll()

            .and()
            .authorizeRequests()
            .antMatchers(JWT_TOKEN_USER_ENTRY_POINT).authenticated()
            //  .antMatchers(LOGIN_USER_ENTRY_POINT).authenticated()
            .and()
            .addFilterBefore(buildAjaxLoginProcessingFilter(),
                UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(authenticationTokenFilterBean(),
                UsernamePasswordAuthenticationFilter.class);
    }
}
