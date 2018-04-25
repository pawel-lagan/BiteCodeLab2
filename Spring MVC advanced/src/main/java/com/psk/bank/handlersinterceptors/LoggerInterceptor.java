package com.psk.bank.handlersinterceptors;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LogManager.getLogger(LoggerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

	      
	    logger.info("----------------");
	    
	    
		logger.info(request.getMethod() + " " + request.getRequestURI());

		
		   for (Enumeration<String> e = request.getHeaderNames(); e.hasMoreElements();) {
			   String headerName = e.nextElement();
			   
			   logger.info(headerName+"="+request.getHeader(headerName));		
		   }
	
		
		return true;
	}
	
	
	@Override
	public void postHandle(
	  HttpServletRequest request, 
	  HttpServletResponse response,
	  Object handler, 
	  ModelAndView modelAndView) throws Exception {
	     
	/*	if(modelAndView!=null) {
		logger.info(modelAndView.getViewName()+" "+modelAndView.getModel());
		}*/
		
	}
	
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		logger.info(response.getStatus());
		
		if (ex != null) {
			logger.info(ex.getLocalizedMessage());
		}
		logger.info("----------------");
	}
	
}
