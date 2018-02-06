package com.centurylink.pctl.mod.product.config;

import com.centurylink.pctl.mod.core.security.endpoint.JwtAuthenticationEntryPoint;
import com.centurylink.pctl.mod.core.security.filter.*;
import com.centurylink.pctl.mod.core.security.jwt.extractor.TokenExtractor;
import com.centurylink.pctl.mod.product.constants.PctlModProductConst;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * WebSecurityConfig class  extends WebSecurityConfigurerAdapter
 * <br> configuration of urls to pass authentication
 * <br> urls to bypass the security check
 * @author begin.samuel.
 */
//@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
@Configuration
@EnableWebSecurity
public class PctlProductWebSecurityCfg extends WebSecurityConfigurerAdapter {


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



    /**
     * authenticationTokenFilterBean() method configuring paths to get skipped in web security
     * @return filter
     * @throws Exception
     */

    @Bean
    protected JwtTokenAuthenticationProcessingFilter authenticationTokenFilterBean() throws Exception {
        List<String> pathsToSkip = new ArrayList<String>();
        pathsToSkip.add(PctlModProductConst.JWT_TOKEN_USER_GET_TOKEN);
        pathsToSkip.add(PctlModProductConst.LOGIN_USER_ENTRY_POINT);
        pathsToSkip.add(PctlModProductConst.LOGGED_OUT_ENTRY_POINT);
        pathsToSkip.add(PctlModProductConst.GET_USER_ENTRY_POINT);
        pathsToSkip.add("/api/**");
        pathsToSkip.add("/applications/**");
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
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, PctlModProductConst.JWT_TOKEN_USER_ENTRY_POINT );
        JwtTokenAuthenticationProcessingFilter filter
            = new JwtTokenAuthenticationProcessingFilter(failureHandler, tokenExtractor, matcher);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    /**
     *buildAjaxLoginProcessingFilter() contains configuration of authenticationManager
     * @return  filter, AjaxLoginProcessingFilter
     * @throws Exception
     */
    @Bean
    protected AjaxLoginProcessingFilter buildAjaxLoginProcessingFilter() throws Exception {
        AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter(PctlModProductConst.JWT_TOKEN_USER_GET_TOKEN, successHandler, failureHandler, objectMapper);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    /**
     * authenticationManagerBean()
     *
     * @return AuthenticationManager
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * configure() provides the jwtAuthenticationProvider  and ajaxAuthenticationProvider
     * @param auth
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
     * configure() method is overridden
     * <br> configuring the urls to skip, filters complete information of websecurity
     * @param http
     * @throws Exception
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
            .antMatchers(PctlModProductConst.JWT_TOKEN_USER_GET_TOKEN).permitAll()
            .antMatchers(PctlModProductConst.LOGIN_USER_ENTRY_POINT).permitAll()
            .antMatchers(PctlModProductConst.LOGGED_OUT_ENTRY_POINT).permitAll()
            .antMatchers(PctlModProductConst.GET_USER_ENTRY_POINT).permitAll()
            .antMatchers("/api/**").permitAll()
            .antMatchers("/applications/**").permitAll()
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
            .and()
            .authorizeRequests()
            .antMatchers(PctlModProductConst.JWT_TOKEN_USER_ENTRY_POINT ).authenticated()

            .anyRequest().permitAll()

            .and()
            .addFilterBefore(buildAjaxLoginProcessingFilter(),
                UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(authenticationTokenFilterBean(),
                UsernamePasswordAuthenticationFilter.class);
    }
}
