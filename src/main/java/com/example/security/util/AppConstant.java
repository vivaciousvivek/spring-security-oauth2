package com.example.security.util;
/**
 * Constants for this application
 *
 * @author VIVEK KUMAR SINGH
 * @since (2018-04-30 12:52:56)
 */
public class AppConstant {
  public static final String CLIEN_ID = "vivek-client";
//  public static final String CLIENT_SECRET = "vivek-secret";
  /**
   * For spring boot version 2 we need to hash the password
   */
  public static final String CLIENT_SECRET = "$2a$04$e3EsYdSOQ50Hd1fyrx6PmOME5yU8ijwRtN8SEGqYH6vnL0DVNJFWq";
  public static final String GRANT_TYPE = "password";
  public static final String AUTHORIZATION_CODE = "authorization_code";
  public static final String REFRESH_TOKEN = "refresh_token";
  public static final String IMPLICIT = "implicit";
  public static final String SCOPE_READ = "read";
  public static final String SCOPE_WRITE = "write";
  public static final String TRUST = "trust";
  public static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60;
  public static final int REFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;

  public static final String RESOURCE_ID = "resource_id";

//  public static final String SECRET_KEY = "vivek";
//  public static final String TOKEN_PREFIX = "Bearer ";
//  public static final String HEADER_STRING = "Authorization";
}
