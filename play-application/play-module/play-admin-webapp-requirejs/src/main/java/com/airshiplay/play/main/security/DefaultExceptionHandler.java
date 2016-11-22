package com.airshiplay.play.main.security;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.airshiplay.play.main.service.LogEntityService;
import com.airshiplay.play.repo.domain.Result;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-2-12
 * <p>
 * Version: 1.0
 */
@ControllerAdvice
public class DefaultExceptionHandler {
	private static final Logger logger = LoggerFactory
			.getLogger(DefaultExceptionHandler.class);

	@Autowired
	LogEntityService logEntityService;
	 
	@Autowired
	private ObjectFactory<ObjectMapper> objectMapper;
	/**
	 * 没有权限 异常
	 * <p/>
	 * 后续根据不同的需求定制即可
	 */
	@ExceptionHandler({ UnauthorizedException.class })
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public Result processUnauthenticatedException(NativeWebRequest request,
			UnauthorizedException e) {
		logger.error("main", e);
		 
		logEntityService.save(e);
		// ModelAndView mv = new ModelAndView();
		// mv.addObject("exception", e);
		// mv.setViewName("unauthorized");
		// ResponseEntity<Result> response = new ResponseEntity<Result>(
		// Result.accessDenide(), HttpStatus.OK);
		return Result.accessDenide().addProperties("exception", e.getMessage());
	}

	@ExceptionHandler({ Exception.class})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ModelAndView processException(HttpServletRequest request,
			HttpServletResponse response, Exception e,HandlerMethod handlerMethod) throws HttpMessageNotWritableException, IOException{
		logger.error("main", e);

		ModelAndView mv = new ModelAndView();		
		logEntityService.save(e);

		ResponseBody responseBodyAnn = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), ResponseBody.class);  
		if (responseBodyAnn!=null) {
			Result result = Result.exception().addProperties("exception",
					e.getMessage());
			HttpInputMessage inputMessage = new ServletServerHttpRequest(request);  
		    List<MediaType> acceptedMediaTypes = inputMessage.getHeaders().getAccept();  
		    if (acceptedMediaTypes.isEmpty()) {
		       acceptedMediaTypes = Collections.singletonList(MediaType.ALL);  
		    }
		    acceptedMediaTypes.add( MediaType.APPLICATION_JSON_UTF8);
		    MediaType.sortByQualityValue(acceptedMediaTypes);
		    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
		    MappingJackson2HttpMessageConverter httpMessageConverter=  new MappingJackson2HttpMessageConverter(objectMapper.getObject());
		    Class<?> returnValueType = Result.class;  
		      for(MediaType acceptedMediaType:acceptedMediaTypes){
		    	  if(httpMessageConverter.canRead(returnValueType, acceptedMediaType)){
		    		  httpMessageConverter.write(result, acceptedMediaType, outputMessage); 
		    		  outputMessage.getBody().close();
		    		  return mv;  
		    	  }
		      }
		} else {
			mv.addObject("errorMessage", e.getMessage());
			mv.setViewName("exception");
		}
		return mv;
	}
}
