package com.airshiplay.play.main.security;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.airshiplay.play.repo.domain.Result;

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
	private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);
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
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("exception", e);
//		mv.setViewName("unauthorized");
//		ResponseEntity<Result> response = new ResponseEntity<Result>(
//				Result.accessDenide(), HttpStatus.OK);
		return Result.accessDenide().addProperties("exception", e.getMessage());
	}

}
