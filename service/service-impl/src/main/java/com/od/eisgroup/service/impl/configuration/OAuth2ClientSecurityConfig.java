package com.od.eisgroup.service.impl.configuration;

import com.od.eisgroup.service.api.UserService;
import com.od.eisgroup.service.impl.GoogleUserInfoTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.OAuth2ClientConfiguration;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * Configures this web app as an OAuth2 client, meaning this web app will be a
 * calling some API services secured via OAuth2. In order to call these secured
 * API services, the user needs to authenticate with the authorization server,
 * and authorize the web app to access the API services in behalf of the user.
 * </p>
 * <p>
 * With the {@link EnableOAuth2Client} annotation, this configuration uses
 * {@link OAuth2ClientConfiguration} that is provided by Spring Security OAuth2.
 * </p>
 */
@Configuration
@EnableWebSecurity
@EnableOAuth2Client
@PropertySource("classpath:google-oauth2.properties")
public class OAuth2ClientSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * <p>
     * This bean is configured because of <code>@EnableOAuth2Client</code>.
     * and RequestContextListener.
     * </p>
     */
    @Autowired
    private OAuth2ClientContext oauth2ClientContext;

    @Autowired
    private UserService userService;

    /**
     * <p>
     * Handles a {@link UserRedirectRequiredException} that is thrown from
     * {@link OAuth2ClientAuthenticationProcessingFilter}.
     * </p>
     * <p>
     * This bean is configured because of <code>@EnableOAuth2Client</code>.
     * </p>
     */
    @Autowired
    private OAuth2ClientContextFilter oauth2ClientContextFilter;

    @Value("${oauth2.clientId}")
    private String clientId;

    @Value("${oauth2.clientSecret}")
    private String clientSecret;

    @Value("${oauth2.userAuthorizationUri}")
    private String userAuthorizationUri;

    @Value("${oauth2.accessTokenUri}")
    private String accessTokenUri;

    @Value("${oauth2.tokenName:authorization_code}")
    private String tokenName;

    @Value("${oauth2.scope}")
    private String scope;

    @Value("${oauth2.userInfoUri}")
    private String userInfoUri;

    @Value("${oauth2.filterCallbackPath}")
    private String oauth2FilterCallbackPath;

    private OAuth2ProtectedResourceDetails authorizationCodeResource() {
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setId("google-oauth-client");
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        details.setUserAuthorizationUri(userAuthorizationUri);
        details.setAccessTokenUri(accessTokenUri);
        details.setTokenName(tokenName);
        String commaSeparatedScopes = scope;
        details.setScope(parseScopes(commaSeparatedScopes));
        details.setAuthenticationScheme(AuthenticationScheme.query);
        details.setClientAuthenticationScheme(AuthenticationScheme.form);
        return details;
    }

    private List<String> parseScopes(String commaSeparatedScopes) {
        List<String> scopes = new LinkedList<>();
        Collections.addAll(scopes, commaSeparatedScopes.split(","));
        return scopes;
    }

    /**
     * @return an OAuth2 client authentication processing filter
     */
    @Bean
    @Description("Filter that checks for authorization code, "
            + "and if there's none, acquires it from authorization server")
    public OAuth2ClientAuthenticationProcessingFilter
    oauth2ClientAuthenticationProcessingFilter() {
        OAuth2RestOperations restTemplate = new OAuth2RestTemplate(
                authorizationCodeResource(),
                oauth2ClientContext);
        OAuth2ClientAuthenticationProcessingFilter filter =
                new OAuth2ClientAuthenticationProcessingFilter(oauth2FilterCallbackPath);
        filter.setRestTemplate(restTemplate);
        filter.setTokenServices(googleUserInfoTokenServices());
        return filter;
    }

    @Bean
    @Description("Google API UserInfo resource server")
    public GoogleUserInfoTokenServices googleUserInfoTokenServices() {
        return new GoogleUserInfoTokenServices(userInfoUri, clientId);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new LoginUrlAuthenticationEntryPoint(oauth2FilterCallbackPath);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/pages/login.xhtml",
                "/resources/img/**",
                "/resources/templates/**", "/resources/resources/css/**",
                "/javax.faces.resource/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                .addFilterAfter(
                        oauth2ClientContextFilter,
                        ExceptionTranslationFilter.class)
                .addFilterBefore(
                        oauth2ClientAuthenticationProcessingFilter(),
                        FilterSecurityInterceptor.class)
                .anonymous()
                .disable();
    }

    @Override
    protected AuthenticationManager authenticationManager() {
        return new NoopAuthenticationManager();
    }

    private static class NoopAuthenticationManager implements AuthenticationManager {
        @Override
        public Authentication authenticate(Authentication authentication) {
            throw new UnsupportedOperationException(
                    "No authentication should be done with this AuthenticationManager");
        }
    }

    @Bean
    @Description("Enables ${...} expressions in the @Value annotations"
            + " on fields of this configuration. Not needed if one is"
            + " already available.")
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
