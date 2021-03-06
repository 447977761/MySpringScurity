/**
 * 
 */
package com.mysecurity.browser.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysecurity.browser.support.SimpleResponse;
import com.mysecurity.core.properties.LoginResponseType;
import com.mysecurity.core.properties.SecurityProperties;


/**
 * @author Liziyue
 * 登录失败处理器
 */
@Component("MyAuthenticationFailHandler")
public class MyAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {
	
	private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	//注入securityproperties
	@Autowired
	private SecurityProperties securityProperties;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	//exception表示错误信息
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		logger.info("登录失败");	
		
		
		//判断返回什么格式
		if(LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
			//登录失败，将状态码设为500
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setContentType("application/json;charset=UTF-8");
			//只返回错误信息
			response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
		} else {
			//系统默认处理方式
			super.onAuthenticationFailure(request, response, exception);
		}
		
	}

}
