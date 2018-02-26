/**
 * 
 */
package com.mysecurity.browser.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysecurity.core.properties.LoginResponseType;
import com.mysecurity.core.properties.SecurityProperties;

/**
 * @author Liziyue
 * ��½�ɹ�������
 */
@Component 
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	//������    ���Խ�authenticationת��Ϊ�ַ�����ʽ��json
	@Autowired
	private ObjectMapper objectMapper;
	
	//ע��securityproperties
	@Autowired
	private SecurityProperties securityProperties;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	//��¼�ɹ��ᱻ���� Authentication�ӿ�Ϊ��֤��Ϣ  ֮��ͨ��objectMapper���Խ���¼��Ȩ�� �û��� sessionid�ȵȱ��json
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		logger.info("��½�ɹ�");
		
		//�жϷ���ʲô��ʽ
		if(LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(authentication));
		} else {
			//ϵͳĬ�ϴ�����ʽ
			super.onAuthenticationSuccess(request, response, authentication);
		}		
	}
}