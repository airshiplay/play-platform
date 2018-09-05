package com.airlenet.admin.resource;
//
//import com.airlenet.data.domain.RestResult;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.InternalAuthenticationServiceException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.WebAuthenticationDetails;
//import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
//import org.springframework.security.web.authentication.session.SessionAuthenticationException;
//import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Slf4j
//@RestController
//public class AuthController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired(required = false)
//    private SessionAuthenticationStrategy sessionStrategy = new NullAuthenticatedSessionStrategy();
//
//    @GetMapping("/auth")
//    public RestResult login(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            String username = "admin";
//            String password = "123456";
//            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
//            authRequest.setDetails(new WebAuthenticationDetails(request));
//            Authentication authenticate = authenticationManager.authenticate(authRequest);
//
//            authenticate.getPrincipal();
//            sessionStrategy.onAuthentication(authenticate, request, response);
//            return RestResult.success();
//        } catch (InternalAuthenticationServiceException e) {
//            log.error("An internal error occurred while trying to authenticate the user.", e);
//        } catch (SessionAuthenticationException e) {
//
//        } catch (BadCredentialsException e) {
//
//        } catch (AuthenticationException e) {
//
//        }
//        return null;
//    }
//
//}
