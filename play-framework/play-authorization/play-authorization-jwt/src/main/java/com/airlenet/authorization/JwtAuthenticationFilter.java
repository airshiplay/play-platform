//package com.airlenet.authorization;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.jwt.Jwt;
//import org.springframework.security.jwt.JwtHelper;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
//    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
//        super(authenticationManager);
//    }
//
//    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
//        super(authenticationManager, authenticationEntryPoint);
//        Jwt jwt = JwtHelper.decode("");
////        jwt.verifySignature();
//    }
//}
