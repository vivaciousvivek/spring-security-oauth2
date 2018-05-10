package com.example.security.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

import static com.example.security.util.AppConstant.RESOURCE_ID;

/**
 * Resource Server hosts the resources [our REST API] the client is interested in.
 *
 * <p>Whenever an user tries to access these resources, the user will be asked to provide his
 * authenticity and once the user is authorized then he will be allowed to access these protected
 * resources.
 *
 * <p>Validate tokens.
 *
 * @author VIVEK KUMAR SINGH
 * @since (2018-05-03 14:06:26)
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.resourceId(RESOURCE_ID).stateless(false);
  }

  /**
   * Give access to the resources here
   *
   * @param http
   * @throws Exception
   */
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.anonymous()
        .disable()
        .authorizeRequests()
        .antMatchers("/api/user/**")
        .authenticated()
        .and()
        .exceptionHandling()
        .accessDeniedHandler(new OAuth2AccessDeniedHandler());
  }
}
