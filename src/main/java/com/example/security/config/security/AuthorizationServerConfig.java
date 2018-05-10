package com.example.security.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import static com.example.security.util.AppConstant.*;

/**
 * Authorization Server is responsible for generating tokens specific to a client.
 *
 * <p>Suppose, if a user wants to login to our website[example.com] via facebook then facebook auth
 * server will be generating tokens for our website.
 *
 * <p>In this case, our website becomes the client which will be requesting for authorization code
 * on behalf of user from facebook, google - the authorization server.
 *
 * <p>Here is a similar implementation that facebook, google will be using.
 *
 * @author VIVEK KUMAR SINGH
 * @since (2018-05-03 14:07:44)
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
  @Autowired private TokenStore tokenStore;
  @Autowired private AuthenticationManager authenticationManager;

  /**
   * Here, we are using in-memory credentials with client_id as vivek-client and CLIENT_SECRET as
   * vivek-secret.But you are free to use JDBC implementation too.
   *
   * @param clients
   * @throws Exception
   */
  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients
        .inMemory()
        .withClient(CLIEN_ID)
        .secret(CLIENT_SECRET)
        .authorizedGrantTypes(GRANT_TYPE, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT)
        .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
        .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
        .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager);
  }
}
