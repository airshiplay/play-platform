package com.airlenet.integration.jersey;

import com.airlenet.repo.domain.PlayResult;
import com.airlenet.repo.domain.Result;
import com.airlenet.security.shiro.PlayAuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

/**
 * @author airlenet
 * @version 2017-10-05
 */
@Provider
public class ExceptionMapperSupport implements ExceptionMapper<Exception> {
   private static final Logger LOG = LoggerFactory.getLogger(ExceptionMapperSupport.class);

    @Override
    public Response toResponse(Exception e) {
        if (e instanceof PlayAuthenticationException) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(PlayResult.error("401",null)).build();
        }
        if(e instanceof NotFoundException){
            return Response.status(Response.Status.NOT_FOUND).entity(PlayResult.error("404",null)).build();
        }
        LOG.error("ExceptionMapperSupport", e);
        return Response.status(Response.Status.BAD_REQUEST).entity(PlayResult.error(e.getMessage(),null)).build();
    }
}