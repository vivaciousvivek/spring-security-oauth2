//package com.example.security.util.security;
//
//import com.example.security.domain.User;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.io.Serializable;
//import java.util.Collections;
//import java.util.Date;
//import java.util.function.Function;
//
//import static com.example.security.util.AppConstant.ACCESS_TOKEN_VALIDITY_SECONDS;
//import static com.example.security.util.AppConstant.SECRET_KEY;
//
///**
// * Util class to generate the auth token as well as to extract username from the token.
// *
// * @author VIVEK KUMAR SINGH
// * @since (2018-04-30 12:53:33)
// */
//@Component
//public class JwtTokenProvider implements Serializable {
//  private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
//
//  /**
//   * To generate token for authorized User
//   *
//   * @param user
//   * @return
//   */
//  public String generateToken(User user) {
//    return doGenerateToken(user.getEmail());
//  }
//
//  /**
//   * Get user name from Token passed in header
//   *
//   * @param token
//   * @return
//   */
//  public String getUsernameFromToken(String token) {
//    return getClaimFromToken(token, Claims::getSubject);
//  }
//
//  public Date getExpirationDateFromToken(String token) {
//    return getClaimFromToken(token, Claims::getExpiration);
//  }
//
//  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//    final Claims claims = getAllClaimsFromToken(token);
//    return claimsResolver.apply(claims);
//  }
//
//  /**
//   * Get claim to validate the token comes from request by using our secret key
//   *
//   * @param token
//   * @return
//   */
//  private Claims getAllClaimsFromToken(String token) {
//    return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//  }
//
//  private Boolean isTokenExpired(String token) {
//    final Date expiration = getExpirationDateFromToken(token);
//    return expiration.before(new Date());
//  }
//
//  /**
//   * To generate token for subject(authorized username, email, id, etc)
//   *
//   * @param subject
//   * @return
//   */
//  private String doGenerateToken(String subject) {
//    /** In subject we pass the user name or id for which we create claim */
//    Claims claims = Jwts.claims().setSubject(subject);
//    claims.put("scopes", Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
//
//    return Jwts.builder()
//        .setClaims(claims)
//        //        .setIssuer("http://yourdomainname.com")
//        .setIssuedAt(new Date(System.currentTimeMillis()))
//        .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
//        .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
//        .compact();
//  }
//
//  /**
//   * Validate token whether it is correct or not expired
//   *
//   * @param token
//   * @param userDetails
//   * @return
//   */
//  public Boolean validateToken(String token, UserDetails userDetails) {
//    final String username = getUsernameFromToken(token);
//    logger.info("token: {}, username: {}", token, username);
//    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//  }
//}
