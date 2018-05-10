package com.example.security.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Configuration about which resource to be protected and which not can be configured.
 *
 * <p>Following configuration basically bootstraps the authorization server and resource server.
 *
 * @author VIVEK KUMAR SINGH
 * @since (2018-04-30 12:44:32)
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) /*for method level security*/
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired private UserDetailsService userDetailsService;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * We can use InMemoryTokenStore, JdbcTokenStore, JwtTokenStore, RedisTokenStore
   *
   * @return
   */
  @Bean
  public TokenStore tokenStore() {
    return new InMemoryTokenStore();
  }
  /** Configured AuthenticationManager to authenticate a user in the login API. */
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    /**
     * If u want specific request to be not authorized, then use permitAll(), else use antMatchers()
     * and then authenticated(){will ask spring security for from where these url should
     * authenticated, then spring will send this to userdetailservice}.
     *
     * <p>If user is not authenticated then we will move it to the exceptionHandling() and add some
     * entry point(for redirecting the error messages)
     *
     * <p>After that we need to maintain a session using stateless session creation policy
     */
    //    http.cors().and().csrf().disable().anonymous().disable();
    http.csrf()
        .disable()
        .anonymous()
        .disable()
        .authorizeRequests()
        .antMatchers("/api-docs/**")
        .permitAll();

    /** We can also use cache control headers */
    http.headers().cacheControl();
  }

  /**
   * AuthenticationManagerBuilder is used to create an AuthenticationManager instance which is the
   * main Spring Security interface for authenticating a user.
   *
   * <p>You can use AuthenticationManagerBuilder to build in-memory authentication, LDAP
   * authentication, JDBC authentication, or add your custom authentication provider.
   *
   * <p>In our example, we’ve provided our CustomUserDetailsService and a passwordEncoder to build
   * the AuthenticationManager.
   *
   * @param auth
   * @throws Exception
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    /*auth.inMemoryAuthentication()
    .withUser("vivek")
    .password("{noop}vivek")
    .roles("USER")
    .and()
    .withUser("admin")
    .password("{noop}admin")
    .roles("ADMIN");*/
  }

  /**
   * Cors Configuration with spring security
   *
   * @return
   */
  /*@Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Collections.singletonList("*"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
    configuration.setAllowCredentials(true);
    configuration.setAllowedHeaders(
        Arrays.asList("Authorization", "Cache-Control", "Content-Type", "x-auth-token"));

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }*/

  public FilterRegistrationBean corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");
    source.registerCorsConfiguration("/**", config);
    FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
    bean.setOrder(0);
    return bean;
  }
}
